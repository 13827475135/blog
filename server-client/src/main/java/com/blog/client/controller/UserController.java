package com.blog.client.controller;


import com.blog.api.RestResponse;
import com.blog.server.component.controller.AbstractController;
import com.blog.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户api
 *
 * @author Nicholas
 * @since 2019-06-27
 */
@RestController()
@RequestMapping("/client/user")
public class UserController extends AbstractController {

    @Autowired(required = false)
    private UserService userService;

    @GetMapping(value = "/hello")
    public RestResponse hello() {
        System.out.println("Yes Nicholas");
        return RestResponse.ok("Hello Nicholas");
    }

    @PostMapping(value = "/login")
    public void login(@RequestBody Object obj) {

    }



}
