package com.bang.ds_alcohol_project.utils;

import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;

public class JwtInfo {

    public static final String HEADER_NAME = "JWT";

    public static final String ISSUER = "DS";

    public static final String TOKEN_KEY = "1q2w3e4r!";

    public static final Long EXPIRES_LIMIT = 100L;

    public static final String USER_IDX = "idx";

    public static final String USER_AUTHORITY = "authority";

    public static Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(JwtInfo.TOKEN_KEY);
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            return Algorithm.none();
        }
    }
}