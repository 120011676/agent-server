package com.github.qq120011676.agentserver.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @RequestMapping("/get")
    public ResponseEntity<byte[]> url(String url) {
        String u = Base64.decodeStr(url);
        try (HttpResponse httpResponse = HttpUtil.createGet(u).execute()) {
            return ResponseEntity.status(httpResponse.getStatus())
                    .header("Content-Type", httpResponse.header("Content-Type"))
                    .body(httpResponse.bodyBytes());
        }
    }
}
