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
@Table(name="view_name")
@Data
@EqualsAndHashCode(callSuper=true)
public class TaskView extends Base implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
	private  Integer taskId;

    @Column(name="name")
	private  String name;

    @Column(name="description")
	private  String description;

    @Column(name="user_id")
	private  Integer userId;

    @Column(name="task_list_id")
	private  Integer taskListId;

    @Column(name="sort_no")
	private  Integer sortNo;
}