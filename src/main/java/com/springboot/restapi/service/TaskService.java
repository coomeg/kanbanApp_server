package com.springboot.restapi.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import javax.annotation.PostConstruct;

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
	private ExecutorService executor;
	@Autowired
	private TaskBean test;

	@PostConstruct
	void postConstruct() {
//		executor = new ThreadPoolExecutor(5, 20,
//	            0L, TimeUnit.SECONDS,
//	            new LinkedBlockingQueue<Runnable>(20),
//	            Executors.defaultThreadFactory());
//		executor = Executors.newFixedThreadPool(20);
		executor = Executors.newFixedThreadPool(test.getSortNo());

	}

	public List<Task> parallel(String param) {

		System.out.println(param + " 処理開始:"+"**"+Thread.currentThread().getName() +"**"+ LocalDateTime.now());
		if (executor.isShutdown()) {
			System.out.println("シャットダウンしている");
			postConstruct();
		}
		List<Task> output = new ArrayList<Task>();
		List<Integer> list =  Arrays.asList(1,2,3, 4,5,6,7,8,9,10);
		List<Callable<Optional<Task>>> tasks = new ArrayList<Callable<Optional<Task>>>();

		list.stream().forEach(id ->{
			tasks.add(() -> {
				System.out.println(param + " 2秒待機:"+"**"+Thread.currentThread().getName() +"**"+ LocalDateTime.now());
				Thread.sleep(2000);
				return taskRepository.findById(id);
			});
		});

		try {
			List<Future<Optional<Task>>> result = executor.invokeAll(tasks);
			for (Future<Optional<Task>> future : result) {
				output.add(future.get().get());
			}
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("エラー*************************************************************");
			executor.shutdownNow();
		} catch (ExecutionException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			System.out.println("エラー*************************************************************");
			executor.shutdownNow();
		} catch (RejectedExecutionException e) {
			throw new RuntimeException("Please try again later.");
		}
		System.out.println(param + " 処理終了:"+"**"+Thread.currentThread().getName() +"**"+ LocalDateTime.now());

		return output;
	}

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
	 * タスクカードのソート順振り直し処理
	 */
	public Task setSortTask(TaskBean bean) {
		bean.setTaskListIdFrom(bean.getTaskListId());
		bean.setTaskListIdTo(bean.getTaskListId());
		Task result = this.updateTask(bean);
		if (bean.getSortNoFrom() < bean.getSortNoTo()) {
			// 下に移動した場合：例） 2⇒5
			this.updateSortDestination(bean);
		} else {
			// 上に移動した場合：例） 5⇒2
			this.updateSortOrigin(bean);
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
