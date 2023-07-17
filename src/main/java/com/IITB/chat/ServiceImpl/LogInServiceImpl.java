package com.IITB.chat.ServiceImpl;

import com.IITB.chat.Dto.LogInDto;
import com.IITB.chat.Exception.UserException;
import com.IITB.chat.Service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Component;



@Component
public class LogInServiceImpl implements LogInService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String LogIn(LogInDto Dto) throws UserException {
        String email = Dto.getEmail();
        String password = Dto.getPassword();
        String query = "SELECT hpass FROM accountINUP WHERE emailid = ?";
        Object[] params = { email };

        if (query == null) {
            throw new UserException("User Not Found");
        } else {

            String storedPassword = jdbcTemplate.queryForObject(query, params, String.class);

            MessageDigestPasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");

            if (passwordEncoder.matches(password, storedPassword)) {
                return "LogIn succesfull";
            } else {
                return "Invalid UserName And Password";
            }
        }
    }
}
