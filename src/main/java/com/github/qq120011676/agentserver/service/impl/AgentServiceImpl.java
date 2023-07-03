package com.github.qq120011676.agentserver.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.github.qq120011676.agentserver.controller.req.AgentGetReq;
import com.github.qq120011676.agentserver.controller.resp.AgentGetResp;
import com.github.qq120011676.agentserver.service.AgentService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AgentServiceImpl implements AgentService {
    private final Map<String, AgentGetResp> MAP = new HashMap<>(100000);

    @Override
    public AgentGetResp get(AgentGetReq req) {
        if (Objects.isNull(req.getCache())
                || Objects.equals(req.getCache(), Boolean.TRUE)) {
            AgentGetResp resp = MAP.get(req.getUrl());
            if (Objects.nonNull(resp)) {
                return resp;
            }
        }
        String u = Base64.decodeStr(req.getUrl());
        try (HttpResponse httpResponse = HttpUtil.createGet(u).execute()) {
            AgentGetResp resp = new AgentGetResp();
            resp.setBytes(httpResponse.bodyBytes());
            resp.setContentType(httpResponse.header("Content-Type"));
            try {
                MAP.put(req.getUrl(), resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resp;
        }
    }
}
