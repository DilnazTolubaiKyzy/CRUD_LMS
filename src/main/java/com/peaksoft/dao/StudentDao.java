package com.peaksoft.dao;

import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getAllStudent();
    void addStudent(Student student);
    void updateStudent(long id,Student student);
    Student getById(long id);
    void deleteStudent(Student student);

    List<Group> getGroupById(Long id);

}
