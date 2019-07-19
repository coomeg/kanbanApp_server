package com.springboot.restapi.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.restapi.controller.data.TaskListBean;
import com.springboot.restapi.entity.TaskList;
import com.springboot.restapi.repository.TaskListRepository;

@Service
@Transactional
public class TaskListService {

	@Autowired
	TaskListRepository taskListRepository;

	private TaskList convertTaskList(TaskListBean bean) {
		System.out.println("ID:" + bean.getTaskListId() + "name:" + bean.getName());
		ModelMapper modelMapper = new ModelMapper();
		TaskList taskList = modelMapper.map(bean, TaskList.class);
		Date date= new Date();
        long time = date.getTime();
        taskList.setUpdateDate(new Timestamp(time));
        taskList.setDeleteFlg(false);
		return taskList;
	}

	public void updateTaskList(List<TaskListBean> bean) {
		bean.stream()
			.map(this::convertTaskList)
			.forEach(data -> taskListRepository.save(data));
	}
}
