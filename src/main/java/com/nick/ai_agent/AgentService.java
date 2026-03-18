package com.nick.ai_agent;

import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.model.ollama.OllamaChatModel;
import java.time.Duration;
import java.util.Scanner;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

  OllamaChatModel model =
      OllamaChatModel.builder()
          .baseUrl("http://localhost:11434")
          .modelName("llama3")
          .topP(0.6) // 模型將可能結果依據出現機率排序，從最高開始取，直到機率總和超過設定值，剃除剩下的
          .temperature(0.3) // 重新計算可能結果的機率，低溫度：機率高的變更高，低的變更低，高溫度：把大家的機率調得差不多
          .maxRetries(3) // 跟 Vertex AI 一樣的重試邏輯
          .timeout(Duration.ofSeconds(120)) // 給本地模型多一點思考時間
          .logRequests(true) // 在 Console 印出發送的 JSON
          .logResponses(true) // 在 Console 印出收到的 JSON
          .build();

  // 如果單純調用 model.generate()，AI 不會記得上一句說了什麼；透過 ConversationalChain，會把之前的對話歷史一起傳給模型
  ConversationalChain chain = ConversationalChain.builder().chatModel(model).build();

  public String chat(String message){
    return sendMessageToAi(message);
  }

  private String sendMessageToAi(String message) {
    return chain.execute(message);
  }
}
