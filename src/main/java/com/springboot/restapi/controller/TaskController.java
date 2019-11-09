package com.springboot.restapi.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restapi.controller.data.TaskBean;
import com.springboot.restapi.entity.Syain;
import com.springboot.restapi.entity.Task;
import com.springboot.restapi.repository.TaskListRepository;
import com.springboot.restapi.repository.TaskRepository;
import com.springboot.restapi.repository.TaskViewRepository;
import com.springboot.restapi.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	TaskRepository taskRepository;
	@Autowired
	TaskViewRepository taskViewRepository;
	@Autowired
	TaskListRepository taskListRepository;
	@Autowired
	TaskService tasktService;
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
				"%" + bean.getKeyword() + "%",
				bean.getTaskListIds());
	}

	@PostMapping
	@RequestMapping(value="/api/createTask", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Task createTask(@RequestBody TaskBean bean) {
		System.out.println(bean.toString());
		ModelMapper modelMapper = new ModelMapper();
		Task task = modelMapper.map(bean, Task.class);
		Integer maxSortNo = taskRepository.getMaxSortNo(bean.getTaskListId());
		Syain syain = (Syain) session.getAttribute("user");
		task.setUserId(syain.getUserId());
		Date date= new Date();
        long time = date.getTime();
        task.setCreateDate(new Timestamp(time));
        task.setUpdateDate(new Timestamp(time));
        task.setDeleteFlg(false);
        task.setSortNo(maxSortNo);
		System.out.println("TaskListId:" + task.getTaskListId() + "名前:" + task.getName());

		return taskRepository.save(task);
	}

	@PutMapping
	@RequestMapping(value="/api/tasks", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Task tasks(@RequestBody TaskBean bean) {
		System.out.println(bean.toString());
		Optional<Task> data = taskRepository.findById(bean.getTaskId());
		Task task = data.get();
		task.setName(!StringUtils.isEmpty(bean.getName()) ? bean.getName() : task.getName());
		task.setTaskListId(bean.getTaskListId());
		task.setDescription(bean.getDescription());
		task.setUserId(bean.getUserId());
		Date date= new Date();
        long time = date.getTime();
        task.setUpdateDate(new Timestamp(time));

		return taskRepository.save(task);
	}

	@PostMapping
	@RequestMapping(value="/api/deleteTasks", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Task deleteTasks(@RequestBody TaskBean bean) {
		System.out.println(bean.toString());
		Optional<Task> data = taskRepository.findById(bean.getTaskId());
		Task task = data.get();
		task.setDeleteFlg(true);
		Date date= new Date();
        long time = date.getTime();
        task.setUpdateDate(new Timestamp(time));

		return taskRepository.save(task);
	}

	/**
	 * タスクカードを移動した際の処理
	 * @param bean
	 * @return
	 */
	@PutMapping
	@RequestMapping(value="/api/taskMove", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Task taskMove(@RequestBody TaskBean bean) {
		System.out.println(bean.toString());
		return tasktService.taskMove(bean);
	}

	/**
	 * タスクカードを移動した際の処理（同一タスクリスト内）
	 * @param bean
	 * @return
	 */
	@PutMapping
	@RequestMapping(value="/api/setSortTask", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Task setSortTask(@RequestBody TaskBean bean) {
		System.out.println(bean.toString());
		return tasktService.setSortTask(bean);
	}

//	@GetMapping
//	public List<TaskView> getTaskView() {
//		return taskViewRepository.findAll();
//	}

	@GetMapping
	public List<Task> test(@RequestParam("param") String param) {
		return tasktService.parallel(param);
	}
}
