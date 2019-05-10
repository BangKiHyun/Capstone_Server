package com.bang.ds_alcohol_project.service;


import com.bang.ds_alcohol_project.dto.Login;
import com.bang.ds_alcohol_project.mapper.UserMapper;
import com.bang.ds_alcohol_project.model.DefaultRes;
import com.bang.ds_alcohol_project.model.LoginReq;
import com.bang.ds_alcohol_project.model.SignUpReq;
import com.bang.ds_alcohol_project.utils.ResponseMessage;
import com.bang.ds_alcohol_project.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/*@Service
@Slf4j
public class AuthService {

    private UserMapper userMapper;
    HttpServletResponse res;
    String token;


    public DefaultRes<JwtFactory.TokenRes> login(final LoginReq loginReq) {
        final User user = userMapper.findByNameAndPassword(loginReq.getId(), loginReq.getPassword());

        log.info(user.getID());
        log.info(user.getPassword());
        if (user != null) {
            //토큰 생성
            token = JwtFactory.create(user.getUserIdx());

            res.addHeader(JwtInfo.HEADER_NAME, token);
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            log.info("토큰 생성");

            //return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, token);
        }
        log.info("생성 실패");
        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL);
    }
}*/


@Slf4j
@Service
public class AuthService {

    private final UserMapper userMapper;
    private final JwtService jwtService;

    public AuthService(final UserMapper userMapper, JwtService jwtService) {
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }


    public DefaultRes<Login> login(final LoginReq loginReq) {
        //final User user = userMapper.findByNameAndPassword(loginReq.getId(), loginReq.getPassword());
        int user_idx;
        String token;
        final SignUpReq signUpReq = userMapper.findByNameAndPassword(loginReq.getId(), loginReq.getPassword());
        if (signUpReq != null) {
            //토큰 생성
            final JwtService.TokenRes tokenDto = new JwtService.TokenRes(jwtService.create(signUpReq.getUserIdx()));

            token = tokenDto.getToken();

            Login login = new Login(token, signUpReq.getName(), signUpReq.getGender(), signUpReq.getBirth());

            return DefaultRes.res(StatusCode.OK, ResponseMessage.LOGIN_SUCCESS, login);
        }
        return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.LOGIN_FAIL);
    }
}
