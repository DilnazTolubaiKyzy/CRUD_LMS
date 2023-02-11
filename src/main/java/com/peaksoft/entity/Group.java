package com.peaksoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "group_name")
    private String groupName;
    @Column(name = "date_of_start")
    private String dateOfStart;
    @Column(name = "date_of_finish")
    private String dateOfFinish;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "course_group",
               joinColumns = @JoinColumn(name = "group_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"))
    private List<Course> courses;
    @Transient
    private Long courseId;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "group")
    private List<Student>students;








}
