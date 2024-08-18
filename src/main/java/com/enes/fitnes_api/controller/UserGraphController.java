package com.enes.fitnes_api.controller;

import com.enes.fitnes_api.response.ResponseUserDetailsDTO;
import com.enes.fitnes_api.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserGraphController {

    @Autowired
    private UserServices userServices;


    @QueryMapping
    public ResponseUserDetailsDTO userInfo(@Argument Long id) {
        return userServices.getUserDetails(id);
    }

}
