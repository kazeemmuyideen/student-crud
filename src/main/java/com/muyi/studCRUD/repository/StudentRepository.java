package com.muyi.studCRUD.repository;

import com.muyi.studCRUD.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {
}
