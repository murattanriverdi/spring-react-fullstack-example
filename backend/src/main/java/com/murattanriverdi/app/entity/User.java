package com.murattanriverdi.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@Getter
@Setter
@ToString
public class User {

    @Id
    @SequenceGenerator(name = "users_seq",sequenceName = "users_seq",allocationSize = 1,initialValue = 1)
    @GeneratedValue(generator = "users_seq")
    private Long id;

    private String username;

    private String email;

    private String password;




}
