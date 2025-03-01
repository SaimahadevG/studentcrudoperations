package com.studentcrud.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentcrud.entities.Student;

public interface StudentRepo extends JpaRepository<Student, UUID> {

}
