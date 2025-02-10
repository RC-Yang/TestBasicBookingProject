package com.test.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class EntryController {

	//@GetMapping("/index.html")//若瀏覽器url直接寫index.html，Spring Boot會直接去static找index.html，然後這註釋跟以下方法就不執行
	@GetMapping("/index")//瀏覽器url要寫成這樣
	//但如果一定前端url一定要是「index.html」的話，則寫法是要添加以下redirectToIndex方法
	public String goToIndex() {
		
		//redirect不適用於thymeleaf這樣的動態頁面，因為會把剛從伺服器取出之資料放棄掉
		return "index";
		//redirect的寫法：redirect:/contextPath/index.html；因為瀏覽器只會用8080結尾的字串去跟其串接
	}
	
	@GetMapping("/index.html")//在這裡這樣寫可以的原因，是Spring Boot會直接執行這個註釋
    public String redirectToIndex() {
        return "redirect:/index";//這樣不會去找靜態資源，而是重新發送一個普通的request
        //所以@GetMapping("/index.html")，這寫法必須和redirect搭配
    }
	
	@GetMapping("/login")
    public String goToLogin() {
        return "login";
    }

	@RequestMapping("/testLogin")
    public String testLogin(HttpSession session) {
//		SecurityContext securityContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
//		System.out.println("NAME:"+securityContext.getAuthentication().getName());
        return "index";
    }
}
