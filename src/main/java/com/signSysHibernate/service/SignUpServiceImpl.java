package com.signSysHibernate.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.hash.Hashing;
import com.signSysHibernate.dao.SignUpDao;
import com.signSysHibernate.entity.SignUpEntity;

@Service
public class SignUpServiceImpl implements SignUpService{
	
	@Autowired
	private SignUpDao signUpDao;

	// 查詢所有會員
	@Override
	@Transactional
	public List<SignUpEntity> queryAllStudents() {
		return signUpDao.queryAllStudents();
	}
	
	// 查詢單筆
	@Override
	@Transactional
	public SignUpEntity get(String sno) {
		return signUpDao.getStudent(sno);
	}

	// 新增會員
	@Override
	@Transactional
	public int add(SignUpEntity stud) {
		int success = 1;
		try {
			signUpDao.add(stud);
		} catch (Exception e) {
			e.printStackTrace();
			success = 0;
		}
		return success;
	}

	// 登入驗證
	@Override
	@Transactional
	public String signInValid(String sMail, String sPwd) throws UnsupportedEncodingException {
		List<SignUpEntity> members = queryAllStudents();
		String md5_pwd = generateMD5(sPwd);
		String msg = "error未驗證";
		for(SignUpEntity m : members) {
			if(m.getSmail().equals(sMail)) {
				if(m.getSpwd().equals(md5_pwd)) {
					System.out.println("登入驗證成功");
					msg = m.getSno();
					return msg;
				}else {
					msg = "error帳號或密碼輸入錯誤!";
					return msg;
				}
			}else {
				msg = "error帳號或密碼輸入錯誤!";
			}
		}
		return msg;
	}

	// 驗證信箱是否已註冊
	@Override
	@Transactional
	public int signUpValid(String sMail) {
		List<SignUpEntity> members = queryAllStudents();
		for(SignUpEntity m : members) {
			if(m.getSmail().equals(sMail)) {
				return 0;
			}
		}
		return 1;
	}
	
	// MD5
	@Override
	@SuppressWarnings("deprecation")
	public String generateMD5(String input) throws UnsupportedEncodingException {
        return Hashing.md5().hashBytes(input.getBytes("UTF-8")).toString();
    }

	// 根據資料表長度產生id
	@Override
	@Transactional
	public String setSnoByMemberLength() {
		List<SignUpEntity> members = queryAllStudents();
		return String.format("%05d", members.size()+1);
	}
	
	// 寄送新密碼
	@Override
	public void sendMail(String mailTo, String newPwd) {
		String host = "sandbox.smtp.mailtrap.io";
		int port = 2525;
		String user = "2090ba31c958f8";
		String pw = "282739add3e25b";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pw);
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			message.setSubject("新的密碼");
			message.setContent("用戶您好, 新的暫時密碼為: "+newPwd+" ,成功登入後請盡速修改密碼!","text/html;charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	     
	}
	
	// 修改會員資料
	@Override
	@Transactional
    public int update(SignUpEntity m) {
		int success = 1;
		try {
			signUpDao.update(m);
		} catch (Exception e) {
			e.printStackTrace();
			success = 0;
		}
	    return success;
    }
	
	// 隨機產生密碼
	@Override
	public String randomPWD() {
		String pwdChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_-";
		String pwdTemp="";
		int charIndexRandom=0;
		for(int i=0; i<5;i++) {
			charIndexRandom = new Random().nextInt(pwdChar.length());
			pwdTemp+=pwdChar.substring(charIndexRandom,charIndexRandom+1);
		}
		System.out.println("service - pwdTemp: "+pwdTemp);
		return pwdTemp;
	}

	// 依據信箱查詢單筆
	@Override
	@Transactional
	public SignUpEntity getBySmail(String smail) {
		return signUpDao.getStudentByMail(smail);
	}
	
	
	
	
}

	
	



