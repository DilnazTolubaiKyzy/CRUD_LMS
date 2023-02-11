package com.peaksoft.service;

import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudent();
    void addStudent(Student student, Long groupId);
    void updateStudent(long id,Student student,Long groupId);
    Student getById(long id);
    void deleteStudent(Student student);
    List<Group> getGroupById(Long id);
}
