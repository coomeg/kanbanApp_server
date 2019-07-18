package com.springboot.restapi.controller.data;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class TaskListBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private  Integer taskListId;

	private  String name;

	private Timestamp createDate;

	private Timestamp updateDate;

	private Boolean deleteFlg;

}