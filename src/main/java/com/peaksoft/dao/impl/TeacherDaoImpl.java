package com.peaksoft.dao.impl;

import com.peaksoft.dao.TeacherDao;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;
import com.peaksoft.entity.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TeacherDaoImpl implements TeacherDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Teacher> getAllTeacher() {
        List<Teacher> teachers = entityManager.createQuery("from Teacher ").getResultList();
        return teachers;
    }

    @Override
    public void addTeacher(Teacher  teacher) {
        entityManager.persist(teacher);
    }

    @Override
    public void updateTeacher(long id, Teacher teacher ) {
        Teacher teacher1 = entityManager.find(Teacher.class,id);
        teacher1.setFirstName(teacher.getFirstName());
        teacher1.setLastName(teacher.getLastName());
        teacher1.setEmail(teacher.getEmail());
        teacher1.setCourse(teacher.getCourse());
        entityManager.merge(teacher1);
    }

    @Override
    public Teacher getById(long id) {
        Teacher teacher = entityManager.find(Teacher.class,id);
        return teacher;
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        entityManager.remove(entityManager.contains(teacher) ? teacher : entityManager.merge(teacher));
    }

    @Override
    public List<Student> getStudentsById(Long id) {
        List<Student> students = entityManager.createQuery
                ("select s from Student s join s.group g join g.courses c join" +
                        "  c.teachers t where t.id=?1").setParameter(1,id).getResultList();
        return students;
    }

}
