package com.IITB.chat.Service;

import com.IITB.chat.Dto.LogInDto;
import com.IITB.chat.Exception.UserException;

public interface LogInService {

    public String LogIn (LogInDto Dto) throws UserException;

}
