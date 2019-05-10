package com.bang.ds_alcohol_project.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUpReq {
    //User 정보
    private int userIdx;
    private String ID;
    private String password;
    private String name;
    private String gender;
    private String birth;
    private String phone;
    private String email;
    //음성 파일
    private MultipartFile voiceFile1;
    private String voiceFileUrl1;
    private MultipartFile voiceFile2;
    private String voiceFileUrl2;
    private MultipartFile voiceFile3;
    private String voiceFileUrl3;

    //영상 파일
    private MultipartFile imageFile;
    private String imageFileUrl;
}
