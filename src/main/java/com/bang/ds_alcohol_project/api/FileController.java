package com.bang.ds_alcohol_project.api;

import com.bang.ds_alcohol_project.auth.jwt.JwtAuth;
import com.bang.ds_alcohol_project.dto.User;
import com.bang.ds_alcohol_project.mapper.UserMapper;
import com.bang.ds_alcohol_project.model.DefaultRes;
import com.bang.ds_alcohol_project.model.SaveReq;
import com.bang.ds_alcohol_project.model.SignUpReq;
import com.bang.ds_alcohol_project.service.FileService;
import com.bang.ds_alcohol_project.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.bang.ds_alcohol_project.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("users")
public class FileController {

    private FileService fileService;
    private UserMapper userMapper;
    private int user_idx;
    private final JwtService jwtService;
    private final static DefaultRes DEFAULT_RES = DefaultRes.builder().status(401).message("인증 실패").build();
    private final static ResponseEntity<DefaultRes> RES_RESPONSE_ENTITY = new ResponseEntity<>(DEFAULT_RES, HttpStatus.UNAUTHORIZED);

    private User user;

    public FileController(final FileService fileService, UserMapper userMapper, JwtService jwtService) {
        this.fileService = fileService;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    //음주 Data 저장
    @PostMapping("saveAlcohol")
    public ResponseEntity saveAlcohol(
            SaveReq saveReq,
            @RequestHeader("Authorization") String token,
            @RequestPart(value = "voiceFile1", required = false) final MultipartFile voiceFile1,
            @RequestPart(value = "voiceFile2", required = false) final MultipartFile voiceFile2,
            @RequestPart(value = "voiceFile3", required = false) final MultipartFile voiceFile3,
            @RequestPart(value = "imageFile", required = false) final MultipartFile imageFile) {
        try {
            if (voiceFile1 != null) saveReq.setVoiceFile1(voiceFile1);
            if (voiceFile2 != null) saveReq.setVoiceFile2(voiceFile2);
            if (voiceFile3 != null) saveReq.setVoiceFile3(voiceFile3);
            if (imageFile != null) saveReq.setImageFile(imageFile);

            user_idx = jwtService.decode(token).getUser_idx();
            return new ResponseEntity<>(fileService.saveAlcohol(saveReq, user_idx), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //초기 Data 저장
    @JwtAuth
    @PostMapping("saveInit")
    public ResponseEntity saveInit(
            SaveReq saveReq,
            @RequestHeader("Authorization") String token,
            @RequestPart(value = "voiceFile1", required = false) final MultipartFile voiceFile1,
            @RequestPart(value = "voiceFile2", required = false) final MultipartFile voiceFile2,
            @RequestPart(value = "voiceFile3", required = false) final MultipartFile voiceFile3,
            @RequestPart(value = "imageFile", required = false) final MultipartFile imageFile) {
        try {
            if (voiceFile1 != null) saveReq.setVoiceFile1(voiceFile1);
            if (voiceFile2 != null) saveReq.setVoiceFile2(voiceFile2);
            if (voiceFile3 != null) saveReq.setVoiceFile3(voiceFile3);
            if (imageFile != null) saveReq.setImageFile(imageFile);
            user_idx = jwtService.decode(token).getUser_idx();
            return new ResponseEntity<>(fileService.saveInit(saveReq, user_idx), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //초기 데이터 유무 확인
    @JwtAuth
    @GetMapping("/fileChk")
    public ResponseEntity chkFile(@RequestHeader("Authorization") String token) {
        try {
            user_idx = jwtService.decode(token).getUser_idx();
            return new ResponseEntity<>(fileService.chkFile(user_idx), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}