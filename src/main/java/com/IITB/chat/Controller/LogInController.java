package com.IITB.chat.Controller;

import com.IITB.chat.Dto.LogInDto;
import com.IITB.chat.Exception.UserException;
import com.IITB.chat.Service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class LogInController {

    @Autowired
    private LogInService logInService;
    @PostMapping("login")
    public ResponseEntity<?> logInHandeller(@RequestBody LogInDto Dto) {

        try {
            String msz = logInService.LogIn(Dto);
            return new ResponseEntity<>(msz, HttpStatus.OK);
        } catch (UserException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
