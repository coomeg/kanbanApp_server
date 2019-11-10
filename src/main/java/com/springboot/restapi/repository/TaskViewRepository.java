package com.springboot.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.Task;
import com.springboot.restapi.entity.TaskView;

@Repository
public interface TaskViewRepository extends JpaRepository<TaskView, Integer> {
	@Query("select t from Task t")
	public List<Task> getTaskAll();
}
