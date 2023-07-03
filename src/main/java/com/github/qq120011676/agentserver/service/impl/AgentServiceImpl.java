package com.github.qq120011676.agentserver.service.impl;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.github.qq120011676.agentserver.controller.req.AgentGetReq;
import com.github.qq120011676.agentserver.controller.resp.AgentGetResp;
import com.github.qq120011676.agentserver.service.AgentService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class AgentServiceImpl implements AgentService {
    @Resource
    private RedisTemplate<String, AgentGetResp> redisTemplate;

    @Override
    public AgentGetResp get(AgentGetReq req) {
        ValueOperations<String, AgentGetResp> valueOperations = redisTemplate.opsForValue();
        if (Objects.isNull(req.getCache())
                || Objects.equals(req.getCache(), Boolean.TRUE)) {
            AgentGetResp resp = valueOperations.get(req.getUrl());
            if (Objects.nonNull(resp)) {
                return resp;
            }
        }
        String u = Base64.decodeStr(req.getUrl());
        try (HttpResponse httpResponse = HttpUtil.createGet(u).execute()) {
            AgentGetResp resp = new AgentGetResp();
            resp.setBytes(httpResponse.bodyBytes());
            resp.setContentType(httpResponse.header("Content-Type"));
            valueOperations.set(req.getUrl(), resp);
            return resp;
        }
    }
}
