package com.springboot.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.Task;
import com.springboot.restapi.entity.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
	@Query(value="select t from Task t left join TaskList tl "
			+ " on t.taskListId = tl.taskListId "
			+ " where tl.deleteFlg = false and t.deleteFlg = false "
			+ " order by tl.taskListId, t.sortNo ")
	public List<Task> getBordInfo();
}
