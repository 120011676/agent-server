package com.github.qq120011676.agentserver.controller;

import com.github.qq120011676.agentserver.controller.req.AgentGetReq;
import com.github.qq120011676.agentserver.controller.resp.AgentGetResp;
import com.github.qq120011676.agentserver.service.AgentService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/agent")
public class AgentController {
    @Resource
    private AgentService agentService;

    @RequestMapping("/get")
    public ResponseEntity<byte[]> get(AgentGetReq req) {
        AgentGetResp resp = agentService.get(req);
        if (Objects.isNull(resp)) {
            throw new RuntimeException("代理错误，地址不正确？");
        }
        return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", resp.getContentType())
                .body(resp.getBytes());
    }
}
