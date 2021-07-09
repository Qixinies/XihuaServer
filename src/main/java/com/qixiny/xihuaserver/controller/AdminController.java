package com.qixiny.xihuaserver.controller;

import com.qixiny.xihuaserver.common.Utils;
import com.qixiny.xihuaserver.pojo.Serial;
import com.qixiny.xihuaserver.service.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {
    private SerialService serialService;
    @Autowired
    public void setSerialService(SerialService serialService) {
        this.serialService = serialService;
    }

    @RequestMapping("")
    public String admin(Model model){
        Utils.logger.info("?");
        model.addAttribute("msg","Hello World");
        return "admin/index";
    }
    @RequestMapping("serial")
    public String serial(){
        return "admin/serial";
    }

    @GetMapping("serial/generate")
    @ResponseBody
    public String generateSerial(String type){
        return serialService.generateSerial(type);
    }

    @GetMapping("serial/all")
    @ResponseBody
    public List<Serial> getAllSerial(){
        return serialService.findAll();
    }
}
