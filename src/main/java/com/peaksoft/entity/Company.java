package com.peaksoft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Course> courses;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Student> students;
}
