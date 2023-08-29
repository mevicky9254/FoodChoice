package com.foodchoice.dto;

public class ForumCommentDto {
	
	 private String content;

	public ForumCommentDto() {
		super();
	}

	public ForumCommentDto(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
