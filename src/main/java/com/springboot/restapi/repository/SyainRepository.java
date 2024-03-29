package com.springboot.restapi.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.Syain;

@Repository
public interface SyainRepository extends JpaRepository<Syain, Integer> {

	@Query("select s from Syain s where s.email = :email AND password = :password")
	public Syain selectToken(@Param("email")String email, @Param("password")String password);

	@Query("select s from Syain s where name like :name and delete_flg = 0 order by user_id")
	public List<Syain> likeUserName(@Param("name")String name);
}
