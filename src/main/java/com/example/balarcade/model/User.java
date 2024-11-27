package com.example.balarcade.model;

import com.example.balarcade.enumeration.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Entity
public class User {
    @Id
    private String id;
    @Enumerated(value = EnumType.STRING)
    protected UserRole role;
    protected String firstname;
    protected String lastname;
    protected String email;
    protected String phoneNumber;

}
