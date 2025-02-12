package com.test.vo;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetail implements UserDetails {

	User user;

	public UserDetail(User user) {
		super();
		this.user = user;
	}

	//以下三個方法是固定要實作
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton((GrantedAuthority) () ->user.getAuthority().getRole());
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getAccount();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
