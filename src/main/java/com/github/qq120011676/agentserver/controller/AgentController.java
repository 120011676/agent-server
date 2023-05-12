package com.github.qq120011676.agentserver.controller;

import cn.hutool.core.codec.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/agent")
public class AgentController {

    @RequestMapping("/get")
    public ResponseEntity<byte[]> url(String url) throws IOException {
        String u = Base64.decodeStr(url);
        Connection connection = Jsoup
                .connect(u)
                .maxBodySize(0)
                .timeout(1000 * 60 * 30);
        Connection.Response resp = connection
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .execute();
        return ResponseEntity.status(resp.statusCode())
                .header("Content-Type", resp.contentType())
                .body(resp.bodyAsBytes());
    }
}
