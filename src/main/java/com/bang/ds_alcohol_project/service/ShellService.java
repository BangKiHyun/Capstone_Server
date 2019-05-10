package com.bang.ds_alcohol_project.service;

import com.bang.ds_alcohol_project.mapper.UserMapper;
import com.bang.ds_alcohol_project.model.DefaultRes;
import com.bang.ds_alcohol_project.utils.ResponseMessage;
import com.bang.ds_alcohol_project.utils.ShellUtill;
import com.bang.ds_alcohol_project.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ShellService {
    private static final String BASH = "/bin/bash";
    private static final String RUN = "-c";

    private final UserMapper userMapper;

    ShellUtill shellUtill = new ShellUtill();

    public ShellService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Transactional
    public DefaultRes learnModel(String name) {
        try {
            String result;
            Map<Integer, String> map = new HashMap<Integer, String>();
            log.info("@@@");

            //String cmds = "sh .../script/" + name;
            String cmds = "D:/4-1/캡디 자료/Project 자료/py 소스/drunken" + name;
            String[] callCmd = {BASH, RUN, cmds};

            log.info("실행됨");
            map = shellUtill.execCommand(callCmd);
            result = map.get((1));
            log.info("값 :" +result);
            //userMapper.saveResult(result);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.SAVE_SUCCESS);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
