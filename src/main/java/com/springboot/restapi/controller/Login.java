package com.springboot.restapi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.controller.data.UserBean;
import com.springboot.restapi.entity.Syain;
import com.springboot.restapi.repository.SyainRepository;

@RestController
public class Login {

	@Autowired
	SyainRepository syainRepository;

	@Autowired
	HttpSession session;

	@ExceptionHandler(Exception.class)
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

    @RequestMapping(value="/api/login", method = RequestMethod.GET)
    public Syain get(HttpServletRequest request) throws Exception {

        return (Syain)request.getSession().getAttribute("user");
    }
}
