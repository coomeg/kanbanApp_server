package com.springboot.restapi.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class UsersController {

	@Autowired
	SyainRepository syainRepository;

	@PostMapping
	@RequestMapping(path = "/api/getUser", method = RequestMethod.POST)
	public Optional<Syain> getUser(HttpServletRequest request) {
		Syain syain = (Syain) request.getSession().getAttribute("user");
		if (syain == null) {
			throw new RuntimeException("ユーザー情報が存在しません。");
		}
		System.out.println("user_id:" + syain.getUserId());
		return syainRepository.findById(syain.getUserId());
	}

	@PostMapping
	@RequestMapping(path = "/api/users", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Syain postUser(@RequestBody UserBean bean, HttpServletRequest request) {
		System.out.println(bean.toString());
		ModelMapper modelMapper = new ModelMapper();
		Syain syain = modelMapper.map(bean, Syain.class);
		Date date= new Date();
        long time = date.getTime();
        syain.setCreateDate(new Timestamp(time));
        syain.setUpdateDate(new Timestamp(time));
		syain.setToken("123");
		syain.setDeleteFlg(false);
		syainRepository.save(syain);
		return syain;
	}

	@PutMapping
	@RequestMapping(path = "/api/users", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Syain putUser(@RequestBody UserBean bean, HttpServletRequest request) {
		System.out.println(bean.toString());
		ModelMapper modelMapper = new ModelMapper();
		Syain syain = modelMapper.map(bean, Syain.class);
		Date date= new Date();
        long time = date.getTime();
        syain.setUpdateDate(new Timestamp(time));
        syain.setToken("123");
        syain.setDeleteFlg(false);
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

	@PostMapping
	@RequestMapping(path = "/api/getUserLike", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Syain>getUserLike(@RequestBody UserBean bean) {
		return syainRepository.likeUserName(bean.getName() + "%");
	}
}
