package com.peaksoft.dao;

import com.peaksoft.entity.Company;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Teacher;

import java.util.List;

public interface CourseDao {
    List<Course> getAllCourse();
    void addCourse(Course course);
    void updateCourse(long id,Course course);
    Course getById(long id);
    void deleteCourse(Course course);
    List<Group> getGroupById(Long id);

    List<Teacher> getTeacherById(Long id);

}
