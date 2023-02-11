package com.peaksoft.entity;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Transient
    private Long courseId;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "course_id")
    private Course course;

}
