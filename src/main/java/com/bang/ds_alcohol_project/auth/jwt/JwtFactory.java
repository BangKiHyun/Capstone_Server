/*
package com.bang.ds_alcohol_project.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bang.ds_alcohol_project.utils.JwtInfo;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

public class JwtFactory {

    private static final LocalDateTime NOW = LocalDateTime.now();

    //로그인 로직이 성공하면
    //유저의 primary key값을 토큰에 저장한다.
    //반환되는 값은 jwtString이다.
    public static String create(final int idx) {
        final Date expiredDate = Date.from(NOW.plusDays(JwtInfo.EXPIRES_LIMIT).toInstant(ZoneOffset.ofHours(9)));
        return create(idx, expiredDate);
    }

    public static String create(final int idx, final Date expiredDate) throws JWTCreationException {
        return JWT.create()
                .withClaim(JwtInfo.USER_IDX, idx)
                .withIssuer(JwtInfo.ISSUER)
                .withExpiresAt(expiredDate)
                .sign(JwtInfo.getAlgorithm());
    }

    public static Boolean isValid(final String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(JwtInfo.getAlgorithm()).build();
            jwtVerifier.verify(token);

            return true;
        } catch (JWTVerificationException verificationException) {

            return false;
        }
    }

    public static DecodedJWT decode(final String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException decodeException) {
            return null;
        }
    }

    //반환될 토큰Res
    public static class TokenRes {
        //실제 토큰
        private String token;

        public TokenRes() {
        }

        public TokenRes(final String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
*/
