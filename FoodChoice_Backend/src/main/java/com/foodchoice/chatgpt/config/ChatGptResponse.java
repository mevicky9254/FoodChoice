package com.foodchoice.chatgpt.config;


import java.util.List;

import lombok.Data;

@Data

public class ChatGptResponse {
	
	private List<Choice> choices;

}
