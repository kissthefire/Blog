package com.blog.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.blog.common.result.Response;
import com.blog.common.utils.MailUtils;
import com.blog.user.service.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by Dell on 2018/10/9.
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Reference
    private CommonService commonService;


    /*@RequestMapping("/sendMail")
    public Response sendVerifyCodeMail() {

    }*/

    @RequestMapping("/uploadIng")
    public Response uploadImg(@RequestParam("multipartFile") CommonsMultipartFile multipartFile){
        return null;
    }

    @RequestMapping("/sendVerifyCode")
    public Response sendVerifyCode(@RequestParam("email") String email){
        return this.commonService.sendVerifyEmail(email);
    }

}
