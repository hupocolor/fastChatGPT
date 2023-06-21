package cn.mychat.chatgpt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatInput {
    private String role = "system";
    private String content;

    public ChatInput(String content) {
        this.content = content;
    }

    // Getters and setters
}