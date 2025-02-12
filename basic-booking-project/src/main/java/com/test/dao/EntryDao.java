package com.test.dao;

import com.test.vo.User;

public interface EntryDao {
	public User findAuthorityByAccount(String account);
}
