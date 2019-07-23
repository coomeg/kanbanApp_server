package com.springboot.restapi.controller.data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;

import lombok.Data;

@Data
public class TaskBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private  Integer taskId;

	private  String name;

	private Integer taskListId;

	private  String description;

	private  Integer userId;

	private  String lName;

	private  String sName;

	private  Date dateFrom;

	private  Date dateTo;

	private  String keyword;

	private  Integer sortNo;

	private  Integer taskListIdFrom;

	private  Integer taskListIdTo;

	private  Integer sortNoFrom;

	private  Integer sortNoTo;

	private  Integer[] taskListIds;

	private Timestamp createDate;

	private Timestamp updateDate;

	private Boolean deleteFlg;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}