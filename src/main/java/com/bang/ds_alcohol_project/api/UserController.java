package com.bang.ds_alcohol_project.api;

import com.bang.ds_alcohol_project.model.SignUpReq;
import com.bang.ds_alcohol_project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.bang.ds_alcohol_project.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    //회원 가입
    @PostMapping("saveUser")
    public ResponseEntity saveUser(
            SignUpReq signUpReq,
            @RequestPart(value = "voiceFile1", required = false) final MultipartFile voiceFile1,
            @RequestPart(value = "voiceFile2", required = false) final MultipartFile voiceFile2,
            @RequestPart(value = "voiceFile3", required = false) final MultipartFile voiceFile3,
            @RequestPart(value = "imageFile", required = false) final MultipartFile imageFile) {
        try {
            if (voiceFile1 != null) signUpReq.setVoiceFile1(voiceFile1);
            if (voiceFile2 != null) signUpReq.setVoiceFile2(voiceFile2);
            if (voiceFile3 != null) signUpReq.setVoiceFile3(voiceFile3);
            if (imageFile != null) signUpReq.setImageFile(imageFile);
            return new ResponseEntity<>(userService.save(signUpReq), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //id 중복 chk
    @GetMapping("chkID")
    public ResponseEntity chkID(
            @RequestParam(name = "id") String id) {
        try {
            return new ResponseEntity<>(userService.IDchk(id), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
