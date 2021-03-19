package com.example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Manager;

@Repository
public interface ManagerRepository<T, ID extends Serializable> extends JpaRepository<Manager,Integer> {

}
