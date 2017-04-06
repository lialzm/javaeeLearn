package com.lialzm.service;

import com.lialzm.bean.User;
import org.springframework.stereotype.Service;

/**
 * Created by apple on 17/4/6.
 */
@Service
public class UserService {

    public Boolean checkUser(User user) {
        return ("1".equals(user.getId()));
    }

}
