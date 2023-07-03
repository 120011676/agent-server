package com.github.qq120011676.agentserver.service;

import com.github.qq120011676.agentserver.controller.req.AgentGetReq;
import com.github.qq120011676.agentserver.controller.resp.AgentGetResp;

public interface AgentService {
    AgentGetResp get(AgentGetReq req);
}
