package com.foodchoice.chatgpt.config;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ChatGptRequest {
	
	private String model;
	private List<Message> messages;
	
	public ChatGptRequest(String model, String prompt) {
		super();
		this.model = model;
		this.messages = new ArrayList<>();
		this.messages.add(new Message("user", prompt));
	}
	
	public ChatGptRequest(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
	

}
