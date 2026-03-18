package com.nick.ai_agent;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Init {

  private final AgentService agentService;

  @EventListener(ApplicationReadyEvent.class)
  public void init() {
    agentService.chat();
  }
}
