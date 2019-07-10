package com.springboot.restapi.dto;

import java.io.Serializable;
public class SessionInfo implements Serializable {

    private static final long serialVersionUID = 8048097948251750715L;
	private  String email;

	private  String password;

	private  String token;

	private  int userId;

	private  String name;
}
