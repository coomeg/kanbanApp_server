package com.springboot.restapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.controller.data.UserBean;
import com.springboot.restapi.entity.Syain;
import com.springboot.restapi.repository.SyainRepository;

@RestController
@CrossOrigin
public class Users {

	@Autowired
	SyainRepository syainRepository;

	@RequestMapping(value="/api/users", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Syain index(@RequestBody UserBean bean) {
		ModelMapper modelMapper = new ModelMapper();
		Syain syain = modelMapper.map(bean, Syain.class);
		System.out.println("Email:" + syain.getEmail() + "名前:" + syain.getName());
		syainRepository.save(syain);
		return syain;
	}
}
