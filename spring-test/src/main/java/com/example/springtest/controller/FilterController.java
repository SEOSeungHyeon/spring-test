package com.example.springtest.controller;

import com.example.springtest.dto.ParamDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("")
@Api(tags = {"필터 api"})
@RequiredArgsConstructor
public class FilterController {

    @PostMapping("/fn1")
//    @RequestMapping(method = {RequestMethod.GET, RequestMethomd.POST}, value="/fn1")
//    public String fn1(@RequestBody HashMap<String, Object> param) {
//    public String fn1(@RequestPart HashMap<String, Object> param) {
//    public String fn1(@ModelAttribute final ParamDto param) {
    public String fn1(@RequestBody ParamDto param) {

        System.out.println("넘어왔다!");

//        return param.get("param1").toString();
        return param.getParam1();
    }

    @PostMapping("/nextPage")
    public String nextPage(@RequestParam HashMap<String, Object> param) {
        System.out.println("넘어왔냐?");

        return "/nextPage";
    }

    @ApiOperation(value ="RequestPart테스트")
    @PostMapping(value = "/fileUpload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> fileUpload(
            @RequestPart(name = "param") ParamDto param
//            @RequestPart MultipartFile multipartFile
    ) {
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
