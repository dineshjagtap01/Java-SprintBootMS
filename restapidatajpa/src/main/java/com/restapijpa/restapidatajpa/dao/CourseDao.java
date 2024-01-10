package com.restapijpa.restapidatajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restapijpa.restapidatajpa.entities.Course;

@Repository
public interface CourseDao extends JpaRepository<Course, Long> {

}