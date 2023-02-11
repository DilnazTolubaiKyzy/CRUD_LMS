package com.peaksoft.entity;

import com.peaksoft.enums.StudyFormat;
import lombok.*;
import javax.persistence.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    private String email;
    @Column(name = "study_format")
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    @Transient
    private Long group_id;
    @ManyToOne(cascade ={CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "company_id")
    private Company company;

}
