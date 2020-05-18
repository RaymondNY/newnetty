package com.project.jpa.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "userTail")
public class UserTail {
    @Id
    @GeneratedValue
    private long id;


}
