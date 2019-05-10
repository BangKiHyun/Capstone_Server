package com.bang.ds_alcohol_project.api;

import com.bang.ds_alcohol_project.service.PyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bang.ds_alcohol_project.model.DefaultRes.FAIL_DEFAULT_RES;


@Slf4j
@RestController
@RequestMapping("users")
public class PyController {
    private final PyService pyService;

    public PyController(final PyService pyService) {
        this.pyService = pyService;
    }

/*    @GetMapping("learnVoice")
    public ResponseEntity learnVoice() {
        try {
            return new ResponseEntity<>(pyService.learnVoice(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("learnImage")
    public ResponseEntity learnImage() {
        try {
            return new ResponseEntity<>(pyService.learnImage(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @GetMapping("learnModel")
    public ResponseEntity learn() {
        try {
            return new ResponseEntity<>(pyService.learn(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
