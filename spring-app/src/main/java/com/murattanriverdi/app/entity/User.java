package com.murattanriverdi.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1,allocationSize = 1)
    @GeneratedValue(generator = "user_seq")
    private Long id;
    private String username;
    private String name;
    private String password;

}
