package com.peaksoft.dao;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Student;
import com.peaksoft.entity.Teacher;

import java.util.List;

public interface TeacherDao {

    List<Teacher> getAllTeacher();
    void addTeacher(Teacher teacher);
    void updateTeacher(long id ,Teacher teacher);
    Teacher getById(long id);
    void deleteTeacher(Teacher teacher);

    List<Student> getStudentsById(Long id);

}
