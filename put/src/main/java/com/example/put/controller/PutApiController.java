package com.example.put.controller;


import com.example.put.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put/{userId}")
    public PostRequestDto put(@RequestBody PostRequestDto postRequestDto, @PathVariable(name = "userId") Long userId){
        System.out.println(userId);
        return postRequestDto;

    }
}
