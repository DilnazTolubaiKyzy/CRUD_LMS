package com.peaksoft.service;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Student;
import com.peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeacher();
    void addTeacher(Teacher teacher,Long courseId);
    void updateTeacher(long id ,Teacher teacher,Long courseId);
    Teacher getById(long id);
    void deleteTeacher(Teacher teacher);
    List<Student> getStudentsById(Long id);
}
