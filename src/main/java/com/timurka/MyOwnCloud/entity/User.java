package com.timurka.MyOwnCloud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "users", indexes = @Index(columnList = "login"))
@NoArgsConstructor
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @JsonProperty("login")
    @Column(nullable = false, unique = true)
    private String login;

    @JsonProperty("password")
    private String password;

    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Token token;

    @Enumerated(EnumType.STRING)
    @JsonManagedReference
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<File> file;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getToken() {
        return this.token.getToken();
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public Role getRole() {
        return this.role;
    }
}
