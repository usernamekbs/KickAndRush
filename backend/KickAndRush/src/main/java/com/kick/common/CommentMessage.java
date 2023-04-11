package com.kick.common;

import lombok.Getter;

@Getter
public enum CommentMessage {
	LIST_COMMENT("댓글이 조회되었습니다."),SAVE_COMMENT("댓글이 추가되었습니다."),DEL_COMMENT("댓글이 삭제되었습니다."),UPD_COMMENT("댓글이 수정되었습니다.");
	
	private String content;
	
	private CommentMessage(String content) {
		this.content = content;
	}

}
