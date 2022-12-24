package com.aloneatwar.laboratory.util;


import com.aloneatwar.laboratory.vo.response.LoginStudent;
import com.aloneatwar.laboratory.vo.response.LoginTeacher;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class JWTUtil {
    public static final long EXPIRE_TIME = 60 * 60 * 1000;
    public static String sign(Map<String,String> map, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        final JWTCreator.Builder[] jwt = {JWT.create()};
        map.forEach(new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                jwt[0] = jwt[0].withClaim(s,s2);
            }
        });
        return jwt[0].withExpiresAt(date).sign(algorithm);
    }



    public static String TeacherSign(LoginTeacher loginTeacher, String secret){
        HashMap<String, String> map = new HashMap<>();
        map.put("id",loginTeacher.id);
        map.put("name",loginTeacher.name);
        map.put("role","teacher");
        return sign(map,secret);
    }


    public static String StudentSign(LoginStudent loginStudent, String secret){
        HashMap<String, String> map = new HashMap<>();
        map.put("id",loginStudent.id);
        map.put("number",loginStudent.number);
        map.put("name",loginStudent.name);
        map.put("role","student");
        return sign(map,secret);
    }


    public static DecodedJWT getJWT(String token){
        return JWT.decode(token);
    }
}
