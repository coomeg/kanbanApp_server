package com.springboot.restapi.controller.data;

import java.util.List;

import com.springboot.restapi.entity.Task;
import com.springboot.restapi.entity.TaskList;

import lombok.Data;
@Data
public class BordBean {

	private List<Task> tasks;

	private List<TaskList> taskList;
}
