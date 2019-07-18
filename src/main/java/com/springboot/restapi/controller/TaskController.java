package com.springboot.restapi.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
