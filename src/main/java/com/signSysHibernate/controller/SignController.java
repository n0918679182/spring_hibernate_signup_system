package com.signSysHibernate.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.signSysHibernate.entity.SignUpEntity;
import com.signSysHibernate.service.SignUpService;

@Controller
@RequestMapping("/")
public class SignController {
	
	@Autowired
	private SignUpService signUpService;
	
	private SignUpEntity loginMember = null;
	
	
	// 測試API
	@RequestMapping(value="/entityList",method=RequestMethod.GET)
	@ResponseBody
	public List<SignUpEntity> entityList() {
		List<SignUpEntity> entitys = signUpService.queryAllStudents();
		return entitys;
	}
	// 登入頁
	@GetMapping("/signIn")
	public String signIn() {
		return "signin";
	}
	
	// 登入驗證並跳轉會員資訊頁
	@PostMapping("/signInValid")
	public String signInValid(Model model, @RequestParam(value="smail")String smail, 
										   @RequestParam(value="spwd")String spwd) {
		// 抓取驗證資訊
		String loginMsg="";
		try {
			loginMsg = signUpService.signInValid(smail, spwd);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		// 根據驗證資訊判斷輸入帳號密碼是否正確
		if(loginMsg.startsWith("error")) {
			model.addAttribute("loginMsg",loginMsg.substring(5));
			return "signin";
		}else {
			// 儲存登入的會員
			loginMember = signUpService.get(loginMsg);
			loginMember.setSsexStr(loginMember.getSsex()==1?"先生":"小姐");
			model.addAttribute("loginMember",loginMember);
			System.out.println("性別: "+loginMember.getSsexStr());
			return "member";
		}
		
	}

	
	// 會員資料顯示頁 (新增其他內頁之後會用到)
	@GetMapping("/member")
	public String member(Model model) {
		model.addAttribute("loginMember",loginMember);
		return "member";
	}
	
	// 註冊頁
	@GetMapping("/signup")
	public String signUp() {
		
		return "signup";
	}
	
	// 驗證註冊信箱是否重複
	@RequestMapping(value="signUpValid",method = RequestMethod.POST)
	@ResponseBody
	public int signUpValid(String smail) {
		return signUpService.signUpValid(smail);
	}
	
	// 接收註冊表單並建立會員資料然後跳轉會員資訊頁
	@PostMapping("/createMember")
	public String creatStud(Model model, @RequestParam(value="sname")String sname, 
										 @RequestParam(value="smail")String smail, 
										 @RequestParam(value="spwd")String spwd, 
										 @RequestParam(value="siden")String siden,
										 @RequestParam(value="ssex")int ssex,
										 @RequestParam(value="sbday")String sbday) {
		SignUpEntity m = new SignUpEntity();
		String sno = signUpService.setSnoByMemberLength();
		m.setSno(sno);
		m.setSname(sname);
		m.setSmail(smail);
		// MD5 加密密碼
		try {
			m.setSpwd(signUpService.generateMD5(spwd));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		m.setSiden(siden);
		m.setSsex(ssex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date bday=null;
		try {
			bday = sdf.parse(sbday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		m.setSbday(bday);
		signUpService.add(m);
		
		// 儲存目前註冊的會員
		loginMember = m;
		loginMember.setSsexStr(loginMember.getSsex()==1?"先生":"小姐");
		model.addAttribute("loginMember",loginMember);
		
		return "member";
	}
	
	// 忘記密碼頁
	@GetMapping("/forgetpwd")
	public String sendMailPage() {
		
		return "forgetpwd";
	}
	
	// 寄送新密碼到輸入的信箱
	@PostMapping("/sendMail")
	public String sendMail(@RequestParam(value="smail")String smail) {
		// 亂數產生新密碼
		String newPwd = signUpService.randomPWD();
		System.out.println("controller - newPwd: "+newPwd);
		
		// 發送新密碼的郵件
		signUpService.sendMail(smail, newPwd);
		System.out.println("controller - smail: "+smail);
		
		// 依據輸入的 smail 先將密碼修改為產生的亂數密碼 -> 疑問: 只要輸入信箱就可以隨意修改其他用戶密碼?
		SignUpEntity m = null;
		try {
			m = signUpService.getBySmail(smail);
			if(m!=null) {
				System.out.println("未出錯!");
				System.out.println(m);
				m.setSpwd(signUpService.generateMD5(newPwd));
				signUpService.update(m);
				System.out.println(m.getSpwd());
			}
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			System.out.println("查無信箱!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			System.out.println("加密失敗");
		}
		
		
		return "signin";
	}
	
	// 會員資料頁面 - 修改密碼
	@PostMapping("/updatepwd")
	public String updatepwd(SignUpEntity m) {
		String newPwd = m.getSpwd();
		System.out.println("newPwd: "+newPwd);
		try {
			m.setSpwd(signUpService.generateMD5(newPwd));
			m.setSsex(m.getSsexStr().equals("先生")?1:0);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		signUpService.update(m);
		System.out.println(m.getSpwd());
		return "signin";
	}
	
}
