package com.springboot.restapi.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.restapi.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	@Query("select t from Task t where delete_flg = 0 order by task_list_id")
	public List<Task> getTaskAll();

	@Query(value="select t from Task t left join TaskList tl "
			+ " on t.taskListId = tl.taskListId "
			+ " left join Syain s on s.userId = t.userId and s.deleteFlg = false"
			+ " where s.name like :name"
			+ " and tl.deleteFlg = false and t.deleteFlg = false"
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
	@Transactional
	@Modifying
	@Query("update Task t set t.sortNo = t.sortNo + 1 where t.sortNo >= :sortNoFrom and t.sortNo < :sortNoTo and t.taskId != :taskId and t.taskListId = :taskListId and t.deleteFlg = false")
	public void sortNoPlus(@Param("sortNoFrom")Integer sortNoFrom, @Param("sortNoTo")Integer sortNoTo, @Param("taskId")Integer taskId, @Param("taskListId")Integer taskListId);

	@Transactional
	@Modifying
	@Query("update Task t set t.sortNo = t.sortNo - 1 where t.sortNo > :sortNoFrom and t.sortNo <= :sortNoTo and t.taskId != :taskId and t.taskListId = :taskListId and t.deleteFlg = false")
	public void sortNoMinus(@Param("sortNoFrom")Integer sortNoFrom, @Param("sortNoTo")Integer sortNoTo, @Param("taskId")Integer taskId, @Param("taskListId")Integer taskListId);

	@Query("select max(t.sortNo) + 1 as sortNo from Task t where t.taskListId = :taskListId and t.deleteFlg = false")
	public Integer getMaxSortNo(@Param("taskListId")Integer taskListId);
}
