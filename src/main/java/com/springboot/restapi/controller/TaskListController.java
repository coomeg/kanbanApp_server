package com.springboot.restapi.controller;

import static org.springframework.data.domain.Sort.Direction.*;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.controller.data.BordBean;
import com.springboot.restapi.controller.data.TaskListBean;
import com.springboot.restapi.entity.TaskList;
import com.springboot.restapi.repository.TaskListRepository;
import com.springboot.restapi.service.TaskListService;

@RestController
public class TaskListController {

	@Autowired
	TaskListRepository taskListRepository;

	@Autowired
	HttpSession session;

	@Autowired
	TaskListService taskListService;

	@PostMapping
	@RequestMapping(value="/api/getTaskListAll")
	public List<TaskList> index() {
		return taskListRepository.findAll(new Sort(ASC, "taskListId"));
	}

	@RequestMapping(value="/api/taskLists", method = RequestMethod.PUT)
	public void updateTaskLists(@RequestBody List<TaskListBean> bean) {
		taskListService.updateTaskList(bean);
		System.out.println("TaskList更新完了");
	}

	@RequestMapping(value="/api/bord", method = RequestMethod.POST)
	public BordBean bord() {
		BordBean bean = new BordBean();
		bean.setTaskList(taskListRepository.findAll(new Sort(ASC, "taskListId")));
		bean.setTasks(taskListRepository.getBordInfo());
		return bean;
	}
}
