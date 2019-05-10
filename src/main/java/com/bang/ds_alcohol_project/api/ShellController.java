package com.bang.ds_alcohol_project.api;


import com.bang.ds_alcohol_project.service.ShellService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.bang.ds_alcohol_project.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("users")
public class ShellController {

    private  final ShellService shellService;

    public ShellController(ShellService shellService) {
        this.shellService = shellService;
    }

    //model 실행
    @PostMapping("learnModel")
    public ResponseEntity learn(@RequestParam(name = "name") String name){
        try {
            return new ResponseEntity<>(shellService.learnModel(name), HttpStatus.OK);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
