package com.springboot.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query("select t from Task t where delete_flg = 0 order by task_list_id")
	public List<Task> getTaskAll();
}
