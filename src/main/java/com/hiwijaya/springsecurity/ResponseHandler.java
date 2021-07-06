package com.hiwijaya.springsecurity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Happy Indra Wijaya
 */
public class ResponseHandler {

    private Object data;
    private HttpStatus code;
    private String message;

    private static Map<String, Object> body;


    public ResponseHandler(Object data){
        this(data, HttpStatus.OK, "Success");
    }

    public ResponseHandler(Object data, HttpStatus code, String message){
        this.data = data;
        this.code = code;
        this.message = message;

        initResponse(data, code, message);

    }

    private static void initResponse(Object data, HttpStatus code, String message){
        Map<String, Object> status = new HashMap<>();
        status.put("code", code.value());
        status.put("message", message);
        status.put("timestamp", Lib.getServerCurrentTime().toString());

        body = new HashMap<>();
        body.put("data", data);
        body.put("status", status);
    }

    public Map<String, Object> getBody(){
        return body;
    }

    public ResponseEntity<Object> getResponse(){
        return new ResponseEntity<Object>(body, this.code);
    }

    public static ResponseEntity<Object> ok(){

        initResponse(null, HttpStatus.OK, "Success");

        return new ResponseEntity<Object>(body, HttpStatus.OK);

    }

}
