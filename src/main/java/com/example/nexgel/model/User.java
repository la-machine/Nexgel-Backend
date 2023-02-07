package com.example.nexgel.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode
@Table (name = "users", schema = "public")
public class User implements UserDetails {

    @SequenceGenerator(
        name = "student_sequence",
        sequenceName = "student_sequence",
        allocationSize = 1
    )
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_sequence"
    )
    private int userId;
    private String firstname;
    private String lastname;
    private String email;
    private String branch;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean locked = false;
    private Boolean enabled = false;

    @ManyToOne
    @JoinColumn(name = "room_d")
    private ClassRoom classRooms;

    @Transient
    private MultipartFile file;
   

    public User(String firstname, String lastname, String email, String branch, String password, Role role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.branch = branch;
        this.password = password;
        this.role = role;
    }

    public User() {
    }
    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    public int getId() {
        return userId;
    }
    public void setId(int id) {
        this.userId = id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return firstname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }
    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MultipartFile getFile(){
        return file;
    }

    public void setFiles(MultipartFile file){
        this.file = file;
    }

}
