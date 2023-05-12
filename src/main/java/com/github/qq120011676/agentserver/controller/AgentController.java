package com.github.qq120011676.agentserver.controller;

import cn.hutool.core.codec.Base64;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @RequestMapping("/get")
    public void url(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Enumeration<String> enumeration = request.getHeaderNames();
        String u = Base64.decodeStr(url);
        Connection connection = Jsoup
                .connect(u)
                .maxBodySize(0)
                .timeout(1000 * 60 * 30);
        System.out.println("========请求headers==========");
        while (enumeration.hasMoreElements()) {
            String n = enumeration.nextElement();
            if (Objects.equals("host", n)) {
                continue;
            }
            String v = request.getHeader(n);
            System.out.println(n + ":" + v);
            connection.header(n, v);
        }
        Connection.Response resp = connection
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .execute();
        response.setStatus(resp.statusCode());
        Map<String, String> headers = resp.headers();
        System.out.println("---------应答headers----------");
        headers.forEach((n, v) -> {
            System.out.println(n + ":" + v);
        });
        headers.forEach(response::setHeader);
        try (BufferedInputStream bin = resp.bodyStream();
             OutputStream out = response.getOutputStream()) {
            bin.transferTo(out);
            out.flush();
        }
    }
}
