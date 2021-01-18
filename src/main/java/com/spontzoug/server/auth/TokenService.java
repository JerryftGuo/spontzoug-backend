package com.spontzoug.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;

@Service
public class TokenService implements ITokenService {

    @Autowired
    private SecureRandom secureRandom;

    public String generate(String prefix,String id){
        String token = id;
        String time = Instant.now().toString();
        Long random = secureRandom.nextLong();
        String data = id + time + random.toString();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            token = stringBuilder.toString()+"-"+ Long.toHexString(secureRandom.nextLong());
        } catch (NoSuchAlgorithmException e){
        }
        return  prefix+"."+token;
    }

    public String code(String id){
        String time = Instant.now().toString();
        Long random = secureRandom.nextLong();
        String data = id + time + random.toString();
        return  Integer.toHexString(data.hashCode());
    }
}