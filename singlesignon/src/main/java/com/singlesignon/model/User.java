package com.singlesignon.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="systemuser")
public class User implements UserDetails{
	private static final long serialVersionUID = -5965314264252115679L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long userSeqNo;
	String userName;
	String password;
	String emailId;
	String mobileNo;
	
	public User() {}
	
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	
	public Long getUserSeqNo() {
		return userSeqNo;
	}
	public void setUserSeqNo(Long userSeqNo) {
		this.userSeqNo = userSeqNo;
	}
	public String getUsername() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "User [userSeqNo=" + userSeqNo + ", userName=" + userName + ", password=" + password + ", emailId="
				+ emailId + ", mobileNo=" + mobileNo + "]";
	}
	
}
