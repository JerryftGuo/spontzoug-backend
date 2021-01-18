package com.spontzoug.server.auth;

import com.spontzoug.server.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class SignatureService implements  ISignatureService {
    @Value("${session.salt}")
    private String salt;

    public Mono<UserSession> sign(UserSession session){
        String data = session.getData().toString() + salt;
        String result = "";
        UserSession ses = session;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        ses.setSign(result);
        return Mono.just(ses);
    }


    public Mono<CustomerSession> sign(CustomerSession session){
        String data = session.getData().toString() + salt;
        String result = "";
        CustomerSession ses = session;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        ses.setSign(result);
        return Mono.just(ses);
    }

    public Mono<TemporarySession> sign(TemporarySession session){
        String data = session.getData().toString()+salt;
        String result = "";
        TemporarySession ses = session;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        ses.setSign(result);
        return Mono.just(ses);
    }
    public Mono<JoinSession> sign(JoinSession session){
        String data = session.getData().toString()+salt;
        String result = "";
        JoinSession ses = session;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        ses.setSign(result);
        return Mono.just(ses);
    }
    public Mono<CodeVerifyItem> sign(CodeVerifyItem code){
        String data = code.getData().toString()+salt;
        String result = "";
        CodeVerifyItem item = code;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        item.setSign(result);
        return Mono.just(item);
    }

    public Mono<UserSession> verify(UserSession session){
        String data = session.getData().toString()+salt;
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        if(session.getSign().equals(result)){
            return Mono.just(session);
        } else {
            return Mono.empty();
        }
    //    return Mono.just(session.getSign().equals(result));
    }

    public Mono<CustomerSession> verify(CustomerSession session){
        String data = session.getData().toString()+salt;
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        if(session.getSign().equals(result)){
            return Mono.just(session);
        } else {
            return Mono.empty();
        }
    }

    public Mono<TemporarySession> verify(TemporarySession session){
        String data = session.getData().toString()+salt;
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        if(session.getSign().equals(result)){
            return Mono.just(session);
        } else {
            return Mono.empty();
        }
     //   return Mono.just(session.getSign().equals(result));
    }
    public Mono<JoinSession> verify(JoinSession session){
        String data = session.getData().toString()+salt;
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        if(session.getSign().equals(result)){
            return Mono.just(session);
        } else {
            return Mono.empty();
        }

    }

    public Mono<CodeVerifyItem> verify(CodeVerifyItem code){
        String data = code.getData().toString()+salt;
        String result = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[]  bytes = messageDigest.digest(data.getBytes());
            StringBuilder stringBuilder = new StringBuilder();
            for( int i=0; i< bytes.length; i++){
                stringBuilder.append(Integer.toString(bytes[i] & 0xff + 0x100, 16).substring(1));
            }
            result = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e){
        }
        log.info("signature:org:"+ code.getSign() +" new:"+result);
        if(code.getSign().equals(result)){

            return Mono.just(code);
        } else {
            return Mono.empty();
        }
        //   return Mono.just(session.getSign().equals(result));
    }
}