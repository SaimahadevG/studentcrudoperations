package com.studentcrud.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.studentcrud.entities.Student;
import com.studentcrud.services.StudentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController 
{
	private final StudentService studentService;
	private final ObjectMapper objectMapper;
	
   @GetMapping("/{id}")
   public ResponseEntity<Student>  getStudentById(@PathVariable UUID id)
   {
	  Student studentById = studentService.getStudentById(id);
	  return studentById!=null?ResponseEntity.ok(studentById):ResponseEntity.notFound().build();
   }
   
   @GetMapping()
   public ResponseEntity<List<Student>> getAllStudents()
   {
	   List<Student> allStudent = studentService.getAllStudent();
	   return ResponseEntity.ok(allStudent);
   }
   
   @PostMapping
   public ResponseEntity<Student> createStudentRecord(@RequestBody Student student)
   {
	    Student studentRecord = studentService.createStudentRecord(student);
	    return ResponseEntity.status(HttpStatus.CREATED).body(studentRecord);
   }
   
   @PutMapping("/{id}")
   public ResponseEntity<Student> updateStudentRecord(@PathVariable UUID id,@RequestBody Student student)
   {
	   Student updateStudentInfo = studentService.updateStudentInfo(id, student);
	   return ResponseEntity.ok(updateStudentInfo);
   }
   
   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteStudentRecord(@PathVariable UUID id)
   {
	   studentService.deleteStudentRecord(id);
	   return ResponseEntity.noContent().build();
   }
   
   @PatchMapping("/{id}")
   public ResponseEntity<?> partialUpdate(@PathVariable UUID id,@RequestBody JsonPatch jsonpatch) throws JsonProcessingException, IllegalArgumentException, JsonPatchException
   {
	    Student studentById = studentService.getStudentById(id);
	    Student applyPatch = applyPatch(studentById,jsonpatch);
	    studentService.updateStudentInfo(id, applyPatch);
	    return ResponseEntity.ok(applyPatch);
   }
   
   public Student applyPatch(Student student,JsonPatch jsonPatch) throws IllegalArgumentException, JsonPatchException, JsonProcessingException
   {
	   JsonNode apply = jsonPatch.apply(objectMapper.convertValue(student, JsonNode.class));
	   return objectMapper.treeToValue(apply,Student.class);
   }
 }
