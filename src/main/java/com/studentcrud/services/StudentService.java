package com.studentcrud.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.studentcrud.entities.Student;
import com.studentcrud.repositories.StudentRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService 
{
   private StudentRepo studentRepo;
   
   public Student getStudentById(UUID id)
   {
	   return studentRepo.findById(id).
			   orElseThrow(()->new RuntimeException("student not found with id"+id ));
   }
   
   public List<Student> getAllStudent()
   {
	    List<Student> all = studentRepo.findAll();
	    return all;
   }
   
   public Student createStudentRecord(Student student)
   {
	   Student save = studentRepo.save(student);
	   return save;
   }
   
   public Student updateStudentInfo(UUID id,Student upstudent)
   {
	   Student byId = studentRepo.findById(id)
			   .orElseThrow(()->new RuntimeException("student not found with id"+id));
	   Student student = byId;
	   student.setName(upstudent.getName());
	   student.setBatch(upstudent.getBatch());
	   student.setPercentage(upstudent.getPercentage());
	   student.setRollNumber(upstudent.getRollNumber());
	   return studentRepo.save(student);
   }
   
   public void deleteStudentRecord(UUID id)
   {
	   if(!studentRepo.existsById(id))
	   {
		   throw new RuntimeException("student not found with id"+ id);
	   }
	   studentRepo.deleteById(id);
   }
   
}
