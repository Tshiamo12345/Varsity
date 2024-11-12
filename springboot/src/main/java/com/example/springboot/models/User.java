package com.example.springboot.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", nullable = false)
    @JsonIgnore
    private String userId;

    @Lob
    @Column(name = "profile_pic", columnDefinition = "LONGBLOB",nullable = true)
    private byte[] profilePic;

    @NotEmpty(message = "Name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;


    @NotEmpty(message = "Surname cannot be empty")
    @Column(name = "surname", nullable = false)
    private String surname;

    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    @NotEmpty(message = "Phone number cannot be empty")
    @Column(name = "phone_no", nullable = false)
    private String phoneNo;

    @NotEmpty(message = "Password cannot be empty")
    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", columnDefinition = "role_id",nullable = false)
    private Role role;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name = "last_logon",nullable = true)
    private LocalDateTime lastLogon;

    
    @Transient
    private String base64;

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
        this.base64 = Base64.getEncoder().encodeToString(profilePic);
    }

    public String getBase64() {
        if (this.base64 == null && this.profilePic != null) {
            this.base64 = Base64.getEncoder().encodeToString(this.profilePic);
        }
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

}

