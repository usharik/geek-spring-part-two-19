package ru.geekbrains.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CommonAdvices {

    private static final Logger logger = LoggerFactory.getLogger(CommonAdvices.class);

    private final EurekaClient eurekaClient;

    @Autowired
    public CommonAdvices(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @ModelAttribute
    public void pictureServiceUrlAttribute(Model model) {
        InstanceInfo server = eurekaClient.getNextServerFromEureka("GATEWAY-SERVICE", false);
        logger.info("Picture service instance: " + server);
        model.addAttribute("pictureServiceUrl", server.getHomePageUrl() + "picture-service");
    }
}
