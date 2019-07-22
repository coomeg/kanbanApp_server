package com.springboot.restapi.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
@MappedSuperclass
@Data
public class Base implements Serializable {
	private static final long serialVersionUID = 1L;

    @Column(name="create_date")
	private Timestamp createDate;

    @Column(name="update_date")
	private Timestamp updateDate;

    @Column(name="delete_flg")
	private Boolean deleteFlg;
}