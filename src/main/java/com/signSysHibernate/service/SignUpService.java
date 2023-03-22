package com.signSysHibernate.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.signSysHibernate.entity.SignUpEntity;

public interface SignUpService {
	
	List<SignUpEntity> queryAllStudents();
	
	SignUpEntity get(String sno);
	
	SignUpEntity getBySmail(String smail);
	
	int add(SignUpEntity stud);
	
	String signInValid(String sMail, String sPwd) throws UnsupportedEncodingException;
	
	int signUpValid(String sMail);
	
	String generateMD5(String input) throws UnsupportedEncodingException;
	
	String setSnoByMemberLength();
	
	void sendMail(String mailTo,String newPwd);
	
	int update(SignUpEntity m);
	
	String randomPWD();
}
