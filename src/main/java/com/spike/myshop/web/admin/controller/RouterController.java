package com.spike.myshop.web.admin.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.spike.myshop.service.content.api.ContentConsumerService;
import com.spike.myshop.service.user.api.UserConsumerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "myshop")
public class RouterController {

    @Value("${services.ports.user}")
    private String userPort;

    @Value("${services.ports.content}")
    private String contentPort;

    @Reference(version = "${services.versions.user.v1}")
    private UserConsumerService userConsumerService;

    @Reference(version = "${services.versions.content.v1}")
    private ContentConsumerService contentConsumerService;

    /**
     * 重定向到user服务
     *
     * @param path 路径
     * @return
     */
    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String user(String path) {
        userConsumerService.info();
        return redirect(path, userPort);
    }


    /**
     * 重定向到content服务
     *
     * @param path 路径
     * @return
     */
    @RequestMapping(value = "content", method = RequestMethod.GET)
    public String content(String path) {
        contentConsumerService.info();
        return redirect(path, contentPort);
    }

    /**
     * 重定向
     *
     * @param path 路径
     * @param port 端口
     * @return
     */
    private String redirect(String path, String port) {
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        if (isConsumerSide) {
            String remoteHost = RpcContext.getContext().getRemoteHost();
            return String.format("redirect:http://%s:%s%s", remoteHost, port, path);
        }
        return null;
    }
}
