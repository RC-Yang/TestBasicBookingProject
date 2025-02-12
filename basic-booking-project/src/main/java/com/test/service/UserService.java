package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.dao.EntryDao;
import com.test.vo.User;
import com.test.vo.UserDetail;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private EntryDao entryDao;

	//以下方法是固定要實作
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		
		User user = entryDao.findAuthorityByAccount(account);
		
		return new UserDetail(user);
	}
}
