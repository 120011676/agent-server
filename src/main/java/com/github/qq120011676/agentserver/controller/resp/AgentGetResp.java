package com.github.qq120011676.agentserver.controller.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class AgentGetResp implements Serializable {
    private byte[] bytes;
    private String contentType;
}
