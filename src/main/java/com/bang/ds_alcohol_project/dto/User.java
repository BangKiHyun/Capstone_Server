package com.bang.ds_alcohol_project.dto;

import lombok.Data;

@Data
public class User {
    private int userIdx;
    private String ID;
    private String password;
    private String name;
    private String voiceFileUrl1;
    private String voiceFileUrl2;
    private String voiceFileUrl3;
    private String imageFileUrl;
}
