package com.renshuo.cloud.wechat.controller;

import com.renshuo.cloud.wechat.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Lenovo on 2021/1/29.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService createMenu;

    @GetMapping(value = "/home")
    public String home() {
        String menu = createMenu.createMenu();
        return menu;
    }
}
