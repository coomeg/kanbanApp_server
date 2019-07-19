package com.springboot.restapi.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="task")
@Data
public class Task implements Serializable {
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

    @Column(name="create_date")
	private Timestamp createDate;

    @Column(name="update_date")
	private Timestamp updateDate;

    @Column(name="delete_flg")
	private Boolean deleteFlg;

    @ManyToOne
    @JoinColumn(name="user_id", insertable=false, updatable=false)
    private Syain syain;

    @ManyToOne
    @JoinColumn(name="task_list_id", insertable=false, updatable=false)
    private TaskList taskList;
}