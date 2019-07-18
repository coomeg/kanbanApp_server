package com.springboot.restapi.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.controller.data.UserBean;
import com.springboot.restapi.entity.Syain;
import com.springboot.restapi.repository.SyainRepository;

@RestController
public class LoginController {

	@Autowired
	SyainRepository syainRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value="/api/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Syain index(@RequestBody UserBean bean) {
		System.out.println(bean.getEmail() + bean.getPassword());
		Syain syain = syainRepository.selectToken(bean.getEmail(), bean.getPassword());
		if (syain == null) {
			throw new RuntimeException("メールアドレスまたはパスワードが違います。");
		}
		if (session.getAttribute("user") == null) {
			session.setAttribute("user", syain);
		}
		return syain;
	}

	@PostMapping
	@RequestMapping(value="/api/logout", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void logout(@RequestBody UserBean bean) {
		session.invalidate();
		System.out.println("ログアウト完了!!");
	}
}
