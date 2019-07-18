package com.springboot.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Integer> {
}
