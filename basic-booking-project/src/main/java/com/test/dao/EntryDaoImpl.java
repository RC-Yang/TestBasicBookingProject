package com.test.dao;

import org.springframework.stereotype.Repository;

import com.test.vo.Authority;
import com.test.vo.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EntryDaoImpl implements EntryDao{

	//用以下註釋取代@Autowired，因為@Autowired是針對Spring bean設計的，@PersistenceContext是針對JPA設計的
	@PersistenceContext
	private EntityManager entityManager;

	
	public User findAuthorityByAccount(String account) {

		Object[] result = (Object[]) entityManager
				.createQuery("SELECT u.account,u.password,u.enabled,u.authority.id, u.authority.role FROM User u WHERE u.account = :account")
				.setParameter("account", account)
				.getSingleResult();
				//getSingleResult()表面上回傳的是 Object，但當查詢結果包含多個欄位時，它實際上是一個 Object[]，故須強制轉型成Object[]

		User user = new User();
		user.setAccount(result[0].toString());
		user.setPassword(result[1].toString());
		user.setEnabled((boolean)result[2]);
		
		Authority authority = new Authority();
		authority.setId(Integer.parseInt(result[3].toString()));
		authority.setRole(result[4].toString());
	
		user.setAuthority(authority);
		
		return user;
	}

}
