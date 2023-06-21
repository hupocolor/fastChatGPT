package cn.mychat.chatgpt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatRequest {
    private ChatInput[] messages;

    public ChatRequest(ChatInput chatInput) {
        this.messages = new ChatInput[] {chatInput};
    }

    // Getters and setters
}