package com.test.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
public class TestConfiguration {

//	@Autowired
//    private DataSource dataSource; // Spring Boot 會自動注入 DataSource

	//要透過該物件來檢查用戶權限
//    @Bean
//    public UserDetailsService userDetailsService() {
//    	JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);//具體則是實作該物件
//
//        manager.setUsersByUsernameQuery("SELECT account, password, enabled FROM user WHERE account = ?");
//        //不是寫成
//        //manager.setUsersByUsernameQuery("SELECT account, pwd, active FROM user WHERE account = ? and pwd = ?");
//        //是因為密碼的驗證，由Spring Security內部處理
//
//        //manager.setAuthoritiesByUsernameQuery("SELECT id, role FROM authorites WHERE id= user_type的值");
//        //這要用join來實作
//        manager.setAuthoritiesByUsernameQuery(
//        	    "SELECT u.account, a.authority FROM user u JOIN authorities a ON u.user_type = a.id WHERE u.account = ?");
//
//        return manager;
//    }
	
	//必須註冊該物件，才能查找到userDetailService物件
	//負責查找到userDetailService物件，再轉交給Spring Security自動驗證
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();//密碼的驗證，由其內部處理
	}

    
    //允許明碼密碼
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); 
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	return http
    	.csrf(csrf -> csrf.disable())
    	//強制將用戶資料綁定到session，以便在不同請求之間維持用戶的登入狀態
    	.securityContext(securityContext -> securityContext
                .securityContextRepository(new HttpSessionSecurityContextRepository()))
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

    	.authorizeHttpRequests(auth -> auth
    	        .requestMatchers("/login","/testLogin").permitAll()
    	        .anyRequest().authenticated())
    	.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/testLogin")//設定當該action的表單提交後，Spring Security要攔截該表單來驗證用戶
                .usernameParameter("account")
                .passwordParameter("password")
                .defaultSuccessUrl("/index.html", true)// 用戶驗證成功後，以該行進入controller，這樣就會到templates去找該index.html
                .permitAll())
        .anonymous(anonymous -> anonymous.disable())
    	.build();
    }

}
