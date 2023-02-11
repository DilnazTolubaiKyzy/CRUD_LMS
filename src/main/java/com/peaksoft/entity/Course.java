package com.peaksoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "course_name")
    private String CourseName;
    @Column(name = "duration_month")
    private String durationMonth;
    @Transient
    private Long companyId;
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinTable(name = "course_group",
               joinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "group_id",referencedColumnName = "id"))
    private List<Group> groups;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
//    @JoinColumn(name = "teacher_id")
    private List<Teacher> teachers;
}
