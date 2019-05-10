package com.bang.ds_alcohol_project.service;

import com.bang.ds_alcohol_project.mapper.UserMapper;
import com.bang.ds_alcohol_project.model.DefaultRes;
import com.bang.ds_alcohol_project.utils.ResponseMessage;
import com.bang.ds_alcohol_project.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


@Slf4j
@Service
public class ResultService {

    private final UserMapper userMapper;

    public ResultService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

/*    @Transactional
    public DefaultRes result() {
        String init;
        String alcohol;
        String result;
        try {
            init = userMapper.findByInitResult();
            alcohol = userMapper.findByalcoholResult();

            //초기값과 음주값 비교
            if (init == "어떠한 값")
                //뭐시가다
                return DefaultRes.res(StatusCode.OK, ResponseMessage.NO_ALCOHOL);
            else
                //뭐시기다
                return DefaultRes.res(StatusCode.OK, ResponseMessage.ALCOHOL);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }*/
}
