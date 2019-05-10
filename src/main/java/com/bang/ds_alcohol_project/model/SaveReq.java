package com.bang.ds_alcohol_project.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SaveReq {

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
