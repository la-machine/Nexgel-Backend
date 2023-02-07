package com.example.nexgel.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User_file")
public class User_file implements Serializable{
    
    private static final long SerialVersionUID = 7778312944L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "modifiedFileName")
    private String modifiedFile;
    @Column(name = "fileExtension")
    private String fileExtension;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    


    public User_file(Long id, String fileName, String modifiedFile, String fileExtension, User user) {
        this.id = id;
        this.fileName = fileName;
        this.modifiedFile = modifiedFile;
        this.fileExtension = fileExtension;
        this.user = user;
    }
    public User_file() {
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getModifiedFile() {
        return modifiedFile;
    }
    public void setModifiedFile(String modifiedFile) {
        this.modifiedFile = modifiedFile;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
