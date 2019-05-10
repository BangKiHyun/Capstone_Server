package com.bang.ds_alcohol_project.dto;

import lombok.Data;

@Data
public class Login {
    private String token;
    private String name;
    private String gender;
    private String birth;

    public Login(String token, String name, String gender, String birth) {
        this.token = token;
        this.name = name;
        this.gender = gender;
        this.birth = birth;
    }
}
