package com.github.qq120011676.agentserver.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.net.URLDecoder;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/agent")
public class AgentController {

    @RequestMapping("/get")
    public ResponseEntity<byte[]> url(String url) {
        String u = Base64.decodeStr(url);
        try (HttpResponse httpResponse = HttpRequest.get(u).execute()) {
            String ct = httpResponse.header("Content-Type");
            String cd = httpResponse.header("Content-Disposition");
            return ResponseEntity.status(httpResponse.getStatus())
                    .header("Content-Type", ct)
                    .header("Content-Disposition", cd)
                    .body(httpResponse.bodyBytes());
        }
    }
}
