package com.nick.ai_agent;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AgentController {
    private final AgentService agentService;

    @PostMapping("/chat")
    public String chatWithAi(@RequestBody String message){
        return agentService.chat(message);
    }
}
