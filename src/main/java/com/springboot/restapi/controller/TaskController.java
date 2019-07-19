package com.springboot.restapi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.controller.data.TaskBean;
import com.springboot.restapi.entity.Task;
import com.springboot.restapi.repository.TaskListRepository;
import com.springboot.restapi.repository.TaskRepository;

@RestController
public class TaskController {

	@Autowired
	TaskRepository taskRepository;
	@Autowired
	TaskListRepository taskListRepository;
	@Autowired
	HttpSession session;

	@PostMapping
	@RequestMapping(value="/api/getTask")
	public List<Task> index() {

		return taskRepository.getTaskAll();
	}

	@PostMapping
	@RequestMapping(value="/api/searchTasks", consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Object> searchTasks(@RequestBody TaskBean bean) {

		System.out.println("sName:" + bean.getSName()
		+ "キーワード:" + bean.getKeyword());
		return taskRepository.searchTasks(
				bean.getSName() + "%",
				bean.getDateFrom(),
				bean.getDateTo(),
				bean.getKeyword() + "%",
				bean.getTaskListIds());
	}
}
