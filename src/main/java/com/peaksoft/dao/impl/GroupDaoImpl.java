package com.peaksoft.dao.impl;

import com.peaksoft.dao.GroupDao;
import com.peaksoft.entity.Course;
import com.peaksoft.entity.Group;
import com.peaksoft.entity.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Group> getAllGroup() {
        List<Group> groups = entityManager.createQuery("from Group ").getResultList();
        return groups;
    }

    @Override
    public void addGroup(Group group) {
        entityManager.persist(group);
    }

    @Override
    public void updateGroup(long id, Group group) {
        Group group1 = entityManager.find(Group.class,id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setDateOfFinish(group.getDateOfFinish());
        entityManager.merge(group1);
    }

    @Override
    public Group getById(long id) {
        Group group = entityManager.find(Group.class,id);
        return group;
    }

    @Override
    public void deleteGroup(Group group) {
        entityManager.remove(entityManager.contains(group) ? group : entityManager.merge(group));    }

    @Override
    public List<Student> getStudentsByGroupId(Long id) {
        List<Student> students = entityManager.createQuery("select s from Student s join " +
                "s.group gro where gro.id=?1").setParameter(1,id).getResultList();
        return students;
    }

    @Override
    public List<Student> search(Long groupId,String studentName) {
        List<Student> students= entityManager.createQuery(
                "select s from Student s join s.group gro where gro.id=?1 and s.firstName=?2")
                .setParameter(1,groupId).setParameter(2,studentName).getResultList();
        return students;


    }

    @Override
    public List<Course> getCoursesById(Long id) {
        List<Course> courses = entityManager.createQuery("select c from Course c join " +
                "c.groups gr where gr.id=?1").setParameter(1,id).getResultList();
        return courses;
    }



}
