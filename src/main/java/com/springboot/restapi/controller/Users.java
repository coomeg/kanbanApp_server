package com.springboot.restapi.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.controller.data.UserBean;
import com.springboot.restapi.entity.Syain;
import com.springboot.restapi.repository.SyainRepository;

@RestController
@CrossOrigin
public class Users {

	@Autowired
	SyainRepository syainRepository;

	@GetMapping
	@RequestMapping(path = "/users/{id}", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public int getUser(@PathVariable("id") int id, HttpServletRequest request) {
		return id;
	}

	@PostMapping
	@RequestMapping(path = "/api/users", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Syain postUser(@RequestBody UserBean bean, HttpServletRequest request) {
		ModelMapper modelMapper = new ModelMapper();
		Syain syain = modelMapper.map(bean, Syain.class);
		System.out.println("Email:" + syain.getEmail() + "名前:" + syain.getName());
		syainRepository.save(syain);
		return syain;
	}

	@PutMapping
	@RequestMapping(path = "/api/users", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Syain putUser(@RequestBody UserBean bean, HttpServletRequest request) {
		ModelMapper modelMapper = new ModelMapper();
		Syain syain = modelMapper.map(bean, Syain.class);
		System.out.println("ユーザーID:" + syain.getUserId() + "Email:" + syain.getEmail() + "名前:" + syain.getName());
		syainRepository.save(syain);
		return syain;
	}

	@DeleteMapping
	@RequestMapping(path = "/api/users", method = RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Syain deleteUser(@RequestBody UserBean bean, HttpServletRequest request) {
		ModelMapper modelMapper = new ModelMapper();
		Syain syain = modelMapper.map(bean, Syain.class);
		System.out.println("Email:" + syain.getEmail() + "名前:" + syain.getName());
		syainRepository.save(syain);
		return syain;
	}
}
