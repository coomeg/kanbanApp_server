package com.springboot.restapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.controller.data.UserBean;
import com.springboot.restapi.entity.Syain;
import com.springboot.restapi.repository.SyainRepository;

@RestController
@CrossOrigin
public class Login {

	@Autowired
	SyainRepository syainRepository;

	@RequestMapping(value="/api/login", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Syain index(@RequestBody UserBean bean, HttpServletRequest request) {
		System.out.println(bean.getEmail() + bean.getPassword());
		Syain syain = syainRepository.selectToken(bean.getEmail(), bean.getPassword());
		request.getSession().setAttribute("user", syain);
		return syain;
	}

    @RequestMapping(value="/api/login", method = RequestMethod.GET)
    public Syain get(HttpServletRequest request) throws Exception {

        return (Syain)request.getSession().getAttribute("user");
    }
}
