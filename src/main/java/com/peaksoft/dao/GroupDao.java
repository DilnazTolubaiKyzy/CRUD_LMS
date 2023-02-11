package com.peaksoft.dao;

import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;

import java.util.List;

public interface GroupDao {
    List<Group> getAllGroup();
    void addGroup(Group group);
    void updateGroup(long id,Group group);
    Group getById(long id);
    void deleteGroup( Group group);
    List<Student> getStudentsByGroupId(Long id);
    
    List<Student> search(Long groupId,String studentName);
    List<Course> getCoursesById(Long id);
}
