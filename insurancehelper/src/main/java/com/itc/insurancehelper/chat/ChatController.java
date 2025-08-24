package com.itc.insurancehelper.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// ChatController class to handle chat requests
// and interact with the ChatClient
// to get responses from the chat model
// The controller has a single endpoint /api/chat/messages
// that accepts POST requests with a ChatRequest body
// and returns the chat response as a String

@RestController
@RequestMapping("/api/chat")
public class ChatController {

  @Autowired
  private ChatClient chatClient;

  @PostMapping("/messages")
  public ResponseEntity<String> chat(@RequestBody ChatRequest req) {
    //ChatResponse resp = chatClient.prompt().user(req.prompt()).call().chatResponse();
   String resp =  chatClient.prompt(req.getPrompt()).call().content();
   // return chatClient.prompt(req.getPrompt()).call().content();
    return ResponseEntity.ok(resp);
  }
}
