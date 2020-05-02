package com.example.demo.controller;
import com.example.demo.entity.IpZone;
import com.example.demo.entity.Result;
import com.example.demo.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IpController {
   private final IpService ipService;

    public IpController(IpService ipService) {
        this.ipService = ipService;
    }

    @RequestMapping("/ip")
    public Result searchIp(@RequestParam String ip, @RequestHeader ("Authorization") String authorization) {
        System.out.println("结果：" + authorization);
        if (ipService.AppCode(authorization)) {              //就新加了AppCode()这一个方法而已。
            return Result.success(ipService.ipSearch(ip));
        }
        return Result.fail();

    }
}