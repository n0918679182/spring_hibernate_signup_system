package com.signSysHibernate.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="STUDENT",
		uniqueConstraints = {@UniqueConstraint(columnNames={"SNO"})})
public class SignUpEntity {
	@Id
	@Column(name="SNO")
	private String sno;
	
	@Column(name="SNAME")
	private String sname;
	
	@Column(name="SBDAY")
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date sbday;
	
	@Column(name="SSEX")
	private Integer ssex;
	
	@Column(name="SMAIL")
	private String smail;
	
	@Column(name="SPWD")
	private String spwd;
	
	@Column(name="SIDEN")
	private String siden;
	
	@Transient
	private String ssexStr;
	
	public SignUpEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignUpEntity(String sno, String sname, Date sbday, Integer ssex, String smail, String spwd, String siden,
			String ssexStr) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.sbday = sbday;
		this.ssex = ssex;
		this.smail = smail;
		this.spwd = spwd;
		this.siden = siden;
		this.ssexStr = ssexStr;
	}

	@Override
	public String toString() {
		return "SignUpEntity [sno=" + sno + ", sname=" + sname + ", sbday=" + sbday + ", ssex=" + ssex + ", smail="
				+ smail + ", spwd=" + spwd + ", siden=" + siden + ", ssexStr=" + ssexStr + "]";
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getSbday() {
		return sbday;
	}

	public void setSbday(Date sbday) {
		this.sbday = sbday;
	}

	public Integer getSsex() {
		return ssex;
	}

	public void setSsex(Integer ssex) {
		this.ssex = ssex;
	}

	public String getSmail() {
		return smail;
	}

	public void setSmail(String smail) {
		this.smail = smail;
	}

	public String getSpwd() {
		return spwd;
	}

	public void setSpwd(String spwd) {
		this.spwd = spwd;
	}

	public String getSiden() {
		return siden;
	}

	public void setSiden(String siden) {
		this.siden = siden;
	}

	public String getSsexStr() {
		return ssexStr;
	}

	public void setSsexStr(String ssexStr) {
		this.ssexStr = ssexStr;
	}


	

	
	
}
