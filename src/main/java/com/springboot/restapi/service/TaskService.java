package com.springboot.restapi.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.restapi.controller.data.TaskBean;
import com.springboot.restapi.entity.Base;
import com.springboot.restapi.entity.Task;
import com.springboot.restapi.repository.TaskListRepository;
import com.springboot.restapi.repository.TaskRepository;

@Service
@Transactional
public class TaskService {

	@Autowired
	TaskListRepository taskListRepository;
	@Autowired
	TaskRepository taskRepository;

	/**
	 * タスクカード移動処理
	 * @param bean
	 * @return
	 */
	public Task taskMove(TaskBean bean) {
		Task result = this.updateTask(bean);

		// 同一タスクリスト内での移動の場合
		if (bean.getTaskListIdFrom().equals(bean.getTaskListIdTo())) {
			if (bean.getSortNoFrom() < bean.getSortNoTo()) {
				// 下に移動した場合：例） 2⇒5
				this.updateSortDestination(bean);
			} else {
				// 上に移動した場合：例） 5⇒2
				this.updateSortOrigin(bean);
			}
		}  else {
			this.updateSortOrigin(bean);
			this.updateSortDestination(bean);
		}
		// 異なるタスクリストへ移動した場合
		return result;
	}

	/**
	 * システム日付を取得
	 */
	private Timestamp sysdate() {
		Date date= new Date();
        long time = date.getTime();
        return new Timestamp(time);
	}

	/**
	 * 共通項目の設定
	 */
	private void setCommonData(Base base) {
		base.setUpdateDate(sysdate());
		base.setDeleteFlg(false);

	}

	/**
	 * タスクカード更新処理
	 */
	private Task updateTask(TaskBean bean) {
		Optional<Task> data = taskRepository.findById(bean.getTaskId());
		Task task = data.get();
		task.setTaskListId(bean.getTaskListIdTo());
		task.setSortNo(bean.getSortNoTo());
		setCommonData(task);
		taskRepository.save(task);
		return taskRepository.save(task);
	}

	/**
	 * ソート順の変更（移動元）
	 */
	private void updateSortOrigin(TaskBean bean) {
		taskRepository.sortNoMinus(bean.getSortNoFrom(), bean.getSortNoTo(), bean.getTaskId(), bean.getTaskListIdFrom());
	}

	/**
	 * ソート順の変更（移動先）
	 */
	private void updateSortDestination(TaskBean bean) {
		taskRepository.sortNoMinus(bean.getSortNoFrom(), bean.getSortNoTo(), bean.getTaskId(), bean.getTaskListIdTo());
	}
	/**
	 * タスクカード削除処理
	 */
}
