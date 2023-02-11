package com.peaksoft.service;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Teacher;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
    void addCourse(Course course,Long companyId);
    void updateCourse(long id,Course course,Long companyId);
    Course getById(long id);
    void deleteCourse(Course course);

    List<Group> getGroupById(Long id);

    List<Teacher> getTeacherById(Long id);
}
