package com.test.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user")//不可寫成@Table(name="web.user")，若schema名稱為web，最好是在application.properties指定
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int id;
	
	@Column
	private String account;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private boolean enabled;
	
	@ManyToOne
	@JoinColumn(name="user_type")
	private Authority authority;//之所以寫成物件形式，是因為要透過外鍵直接關聯整個對應表

	//JPA的VO在定義時，相較於對應表，可以少部分的屬性
	//例如表有ABCDE五個欄位，但VO只有ABC三個欄位
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Authority getAuthority() {
		return authority;
	}

	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
}
