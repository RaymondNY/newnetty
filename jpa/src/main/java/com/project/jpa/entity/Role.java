package com.project.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    //@ManyToMany(targetEntity = User.class,cascade = CascadeType.ALL)
    @ManyToMany(mappedBy = "roles")
//    @JoinTable(name = "user_role",
//            //joinColumns,当前对象在中间表中的外键
//            joinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")},
//            //inverseJoinColumns，对方对象在中间表的外键
//            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")}
//    )
    //mappedBy与@JoinColumn和@JoinTable互斥，不能同时作用于一个成员
    private Set<User> users = new HashSet<>();

}
