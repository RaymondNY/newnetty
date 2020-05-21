package com.project.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "userName",nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private int age;

    @ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            //joinColumns,当前对象在中间表中的外键
            joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            //inverseJoinColumns，对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
    )
    private Set<Role> roles=new HashSet<>();
    //当使用单向关联时，由父类管理关联关系，子类无法管理，而这时，父亲知道自己的儿子，但是，从儿子对象不知道父亲是谁。
    //单向关联时，只指定<one-to-many>
    //当使用双向关联时，关联关系的管理可以通过inverse指定，这时，儿子能清楚的知道自己的父亲是谁。 双向关联时，还要指定<many-to-one>

}
