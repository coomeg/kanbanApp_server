package com.springboot.restapi.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query("select t from Task t where delete_flg = 0 order by task_list_id")
	public List<Task> getTaskAll();
// t.task_id, t.name, s.name as sname, tl.name as lname, t.create_date
	@Query(value="select t from Task t left join TaskList tl "
			+ " on t.taskListId = tl.taskListId and tl.deleteFlg = 0 and t.deleteFlg = 0 "
			+ " left join Syain s on s.userId = t.userId and s.deleteFlg = 0 "
			+ " where s.name like :name "
			+ " and t.createDate between :dateFrom and :dateTo "
			+ " and (t.name like :keyword or t.description like :keyword) "
			+ " and t.taskListId in (:taskListIds) "
			+ " order by t.taskId ")
	public List<Object> searchTasks(
			@Param("name")String name,
			@Param("dateFrom")Date dateFrom,
			@Param("dateTo")Date dateTo,
			@Param("keyword")String keyword,
			@Param("taskListIds")Integer[] taskListIds);
}
