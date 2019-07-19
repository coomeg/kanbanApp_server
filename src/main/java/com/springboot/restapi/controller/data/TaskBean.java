package com.springboot.restapi.controller.data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class TaskBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private  Integer taskId;

	private  String name;

	private  String lName;

	private  String sName;

	private  Date dateFrom;

	private  Date dateTo;

	private  String keyword;

	private  Integer[] taskListIds;

	private Timestamp createDate;

	private Timestamp updateDate;

	private Boolean deleteFlg;

}