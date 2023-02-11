package com.peaksoft.dao.impl;

import com.peaksoft.dao.CourseDao;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseDaoImpl implements CourseDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Course> getAllCourse() {
        List<Course> courses = entityManager.createQuery("from Course").getResultList();
        return courses;
    }

    @Override
    public void addCourse(Course course) {
     entityManager.persist(course);
    }

    @Override
    public void updateCourse(long id, Course course) {
    Course course1 = entityManager.find(Course.class,id);
    course1.setCourseName(course.getCourseName());
    course1.setDurationMonth(course.getDurationMonth());
    course1.setCompany(course.getCompany());
    entityManager.merge(course1);
    }

    @Override
    public Course getById(long id) {
        Course course = entityManager.find(Course.class,id);
        return course;
    }

    @Override
    public void deleteCourse(Course course) {
        entityManager.remove(entityManager.contains(course) ? course : entityManager.merge(course));
    }

    @Override
    public List<Group> getGroupById(Long id) {
        List<Group>groups = entityManager.createQuery(" select g from Group g join g.courses cou where cou.id=?1")
                .setParameter(1,id).getResultList();
        return groups;
    }
    @Override
    public List<Teacher> getTeacherById(Long id) {
        List<Teacher> teachers =  entityManager.createQuery(" select t from Teacher t join t.course cou where cou.id=?1")
                .setParameter(1,id).getResultList();
        return teachers;
    }
}
