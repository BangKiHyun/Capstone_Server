package com.bang.ds_alcohol_project.service;

import com.bang.ds_alcohol_project.dto.User;
import com.bang.ds_alcohol_project.mapper.UserMapper;
import com.bang.ds_alcohol_project.model.DefaultRes;
import com.bang.ds_alcohol_project.model.SignUpReq;
import com.bang.ds_alcohol_project.utils.ResponseMessage;
import com.bang.ds_alcohol_project.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Slf4j
@Service
public class UserService {

    private final UserMapper userMapper;
    private final S3FileUpload s3FileUpload;

    public UserService(final UserMapper userMapper, final S3FileUpload s3FileUpload) {
        this.userMapper = userMapper;
        this.s3FileUpload = s3FileUpload;
    }


    //회원 가입
    @Transactional
    public DefaultRes save(final SignUpReq signUpReq) {
        try {
            //파일 있으면 파일 S3에 저장 후 그 경로를 저장
            if (signUpReq.getVoiceFile1() != null)
                signUpReq.setVoiceFileUrl1(s3FileUpload.upload(signUpReq.getVoiceFile1()));
            if (signUpReq.getVoiceFile2() != null)
                signUpReq.setVoiceFileUrl2(s3FileUpload.upload(signUpReq.getVoiceFile2()));
            if (signUpReq.getVoiceFile3() != null)
                signUpReq.setVoiceFileUrl3(s3FileUpload.upload(signUpReq.getVoiceFile3()));
            if (signUpReq.getImageFile() != null)
                signUpReq.setImageFileUrl(s3FileUpload.upload(signUpReq.getImageFile()));

            userMapper.saveUser(signUpReq);
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.CREATE_USER);
        } catch (Exception e) {
            //Rollback
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    //id 중복 chk
    @Transactional
    public DefaultRes IDchk(String id) {
        try {
            final User user = userMapper.findByID(id);
            if (user == null)
                return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATE_ID);
            else
                return DefaultRes.res(StatusCode.OK, ResponseMessage.NOT_CREATE_ID);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
