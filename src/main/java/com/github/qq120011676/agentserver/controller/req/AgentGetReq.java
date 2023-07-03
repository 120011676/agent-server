package com.github.qq120011676.agentserver.controller.req;

import lombok.Data;

@Data
public class AgentGetReq {
    private String url;
    private Boolean cache;
}
