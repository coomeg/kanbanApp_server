package com.springboot.restapi.controller.data;

import java.io.Serializable;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

		private  String email;

		private  String password;

		private  String token;

		private  int userId;

		private  String name;

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

		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

}