package com.springboot.restapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="syain")
@Data
@EqualsAndHashCode(callSuper=true)
public class Syain extends Base implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
	private  Integer userId;

    @Column(name="token")
	private  String token;

    @Column(name="email")
	private  String email;

    @Column(name="password")
	private  String password;

    @Column(name="name")
	private  String name;

    @Column(name="teamId")
	private Integer teamId;
}