package com.studentcrud.entities;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student 
{
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
   
   @Column(name="name")
   @NotBlank
   private String name;
   
   @Column(name="roll_number")
   @NotNull
   private int rollNumber;
   
   @Column(precision = 4,scale = 2)
   @NotNull
   private BigDecimal percentage;
   
   @Column
   @NotNull
   private String batch;
}
