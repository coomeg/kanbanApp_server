package com.springboot.restapi.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.restapi.entity.Syain;

@Repository
public interface SyainRepository extends JpaRepository<Syain, Long> {

	@Query("select s from Syain s where s.email = :email AND password = :password")
	public Syain selectToken(@Param("email")String email, @Param("password")String password);
}
