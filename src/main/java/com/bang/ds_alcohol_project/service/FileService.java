package com.bang.ds_alcohol_project.service;

import com.bang.ds_alcohol_project.dto.User;
import com.bang.ds_alcohol_project.mapper.UserMapper;
import com.bang.ds_alcohol_project.model.DefaultRes;
import com.bang.ds_alcohol_project.model.SaveReq;
import com.bang.ds_alcohol_project.model.SignUpReq;
import com.bang.ds_alcohol_project.utils.ResponseMessage;
import com.bang.ds_alcohol_project.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Slf4j
@Service
public class FileService {

    private final S3FileUpload s3FileUpload;
    private final UserMapper userMapper;

    public FileService(S3FileUpload s3FileUpload, UserMapper userMapper) {
        this.s3FileUpload = s3FileUpload;
        this.userMapper = userMapper;
    }


    //음주 데이터 저장
    @Transactional
    public DefaultRes saveAlcohol(final SaveReq saveReq, final int user_idx) {
        try {
            if (saveReq.getVoiceFile1() != null)
                saveReq.setVoiceFileUrl1(s3FileUpload.upload(saveReq.getVoiceFile1()));
            if (saveReq.getVoiceFile2() != null)
                saveReq.setVoiceFileUrl2(s3FileUpload.upload(saveReq.getVoiceFile2()));
            if (saveReq.getVoiceFile3() != null)
                saveReq.setVoiceFileUrl3(s3FileUpload.upload(saveReq.getVoiceFile3()));
            if (saveReq.getImageFile() != null)
                saveReq.setImageFileUrl(s3FileUpload.upload(saveReq.getImageFile()));

            final User user = userMapper.findByAlcoholData(user_idx);
            if (user == null)
                userMapper.saveAlcohol(saveReq, user_idx);
            else
                userMapper.updateAlcohol(saveReq, user_idx);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATE_DATA);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    //초기 데이터 저장
    @Transactional
    public DefaultRes saveInit(final SaveReq saveReq, final int user_idx) {
        try {
            if (saveReq.getVoiceFile1() != null)
                saveReq.setVoiceFileUrl1(s3FileUpload.upload(saveReq.getVoiceFile1()));
            if (saveReq.getVoiceFile2() != null)
                saveReq.setVoiceFileUrl2(s3FileUpload.upload(saveReq.getVoiceFile2()));
            if (saveReq.getVoiceFile3() != null)
                saveReq.setVoiceFileUrl3(s3FileUpload.upload(saveReq.getVoiceFile3()));
            if (saveReq.getImageFile() != null)
                saveReq.setImageFileUrl(s3FileUpload.upload(saveReq.getImageFile()));

            userMapper.saveInitData(saveReq, user_idx);

            return DefaultRes.res(StatusCode.OK, ResponseMessage.CREATE_DATA);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    //초기 데이터 존재여부 확인
    @Transactional
    public DefaultRes chkFile(final int userIdx) {
        try {
            final User user = userMapper.chkFile(userIdx);
            log.info("user :" + user);
            if (user == null)
                return DefaultRes.res(StatusCode.OK, ResponseMessage.CONTENT_FILE);
            else
                return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_CONTENT_FILE);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
