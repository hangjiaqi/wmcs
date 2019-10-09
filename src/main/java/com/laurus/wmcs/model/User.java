package com.laurus.wmcs.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private Integer age;

    private BigDecimal balance;

    private static final long serialVersionUID = 1L;

    public User() {
    }


}