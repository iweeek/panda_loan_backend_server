package com.pinganzhiyuan.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/", produces="application/json;charset=UTF-8")
public class IndexController {
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> read() {
        return ResponseEntity.ok().body(null);
    }

}
