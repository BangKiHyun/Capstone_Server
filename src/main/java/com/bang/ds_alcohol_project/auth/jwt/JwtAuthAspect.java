package com.bang.ds_alcohol_project.auth.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.bang.ds_alcohol_project.dto.User;
import com.bang.ds_alcohol_project.mapper.UserMapper;
import com.bang.ds_alcohol_project.model.DefaultRes;
import com.bang.ds_alcohol_project.service.JwtService;
import com.bang.ds_alcohol_project.utils.JwtInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/*@Slf4j
@Component
@Aspect
@RequiredArgsConstructor
public class JwtAuthAspect {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthAspect.class);
    private final HttpServletRequest httpServletRequest;


    @Around("@annotation(com.bang.ds_alcohol_project.auth.jwt.JwtAuth)")
    public Object validateJWT(final ProceedingJoinPoint pjp) throws Throwable {
        final String token = httpServletRequest.getHeader("Authorization");
        //토큰 존재 여부 확인
        checkToken(token);
        log.info("존재 확인");
        //토큰 검사
        validateToken(token);
        log.info("검사");
        //토큰 해독
        final DecodedJWT decodedJWT = JwtFactory.decode(token);
        logger.info("Authorized Token Access - " + decodedJWT.getClaim(JwtInfo.USER_IDX).asString());
        return pjp.proceed(pjp.getArgs());

    }

    private void checkToken(String token) {
        if (token == null) {
            logger.info("비어있는 토큰 접근.");
            throw new RuntimeException("토큰없음");
        }
    }

    private void validateToken(String token) {
        if (!JwtFactory.isValid(token)) {
            logger.info("유효하지 않은 토큰 접근.");
            throw new RuntimeException("유효성 작살.");
        }
    }
}*/

@Slf4j
@Component
@Aspect
public class JwtAuthAspect {

    private final static String AUTHORIZATION = "Authorization";

    private final static DefaultRes DEFAULT_RES = DefaultRes.builder().status(401).message("인증 실패").build();
    private final static ResponseEntity<DefaultRes> RES_RESPONSE_ENTITY = new ResponseEntity<>(DEFAULT_RES, HttpStatus.UNAUTHORIZED);

    private final HttpServletRequest httpServletRequest;

    private final UserMapper userMapper;

    private final JwtService jwtService;


    public JwtAuthAspect(final HttpServletRequest httpServletRequest, final UserMapper userMapper, final JwtService jwtService) {
        this.httpServletRequest = httpServletRequest;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }


    //항상 @annotation 패키지 이름을 실제 사용할 annotation 경로로 맞춰줘야 한다.
    @Around("@annotation(com.bang.ds_alcohol_project.auth.jwt.JwtAuth)")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        final String jwt = httpServletRequest.getHeader(AUTHORIZATION);
        //토큰 존재 여부 확인
        if (jwt == null) return RES_RESPONSE_ENTITY;
        //토큰 해독
        final JwtService.Token token = jwtService.decode(jwt);
        //토큰 검사
        if (token == null) {
            log.info("토큰 없음");
            return RES_RESPONSE_ENTITY;
        } else {
            final User user = userMapper.findByUserIdx(token.getUser_idx());
            log.info("유효 검사");
            //유효 사용자 검사
            if (user == null) return RES_RESPONSE_ENTITY;
            return pjp.proceed(pjp.getArgs());
        }
    }
}


