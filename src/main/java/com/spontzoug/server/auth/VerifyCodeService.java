package com.spontzoug.server.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class VerifyCodeService implements IVerifyCodeService{
    @Autowired
    private SecureRandom secureRandom;
    public String generate32(){
        Integer code = secureRandom.nextInt();
        String mycode = Integer.toHexString(code).toUpperCase();
        StringBuilder builder= new StringBuilder();
        for (int i=0; i<mycode.length(); i++){
            if( i % 4 == 0 && i!= 0) builder.append('-');
            builder.append(mycode.charAt(i));
        }
        return "6666-6666";
                //return  builder.toString();
    }

    public String generate64(){
        Long code = secureRandom.nextLong();
        String mycode = Long.toHexString(code).toUpperCase();
        StringBuilder builder= new StringBuilder();
        for (int i=0; i<mycode.length(); i++){
            if( i % 4 == 0 && i!= 0) builder.append('-');
            builder.append(mycode.charAt(i));
        }
        //return "666666"; //test
        return  builder.toString();
    }
}