package com.muyi.studCRUD.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(message = "First name is required ")
    @Size(min = 3, max = 50, message = "min character is 3 max is 50")
    private String firstName;

    @NotEmpty(message = "Lat name is required ")
    @Size(min = 3, max = 50, message = "min character is 3 max is 50")
    private String lastName;

    @NotEmpty(message = "Email is required ")
    @Email
    private String email;

    private float cgpa;
}
