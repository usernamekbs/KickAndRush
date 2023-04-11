package com.kick.model.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
	private Long pno;
    private String title;
    private String content;
    private String writer;
    private int comment_cnt;
    private String file_name;
    private int file_size;
    private String file_uid;
    
    public PostRequestDto(Long pno,String title, String content, String writer,int comment_cnt,String file_name,int file_size,String file_uid) {
    	this.pno = pno;
    	this.title = title;
        this.content = content;
        this.writer = writer;
        this.comment_cnt = comment_cnt;
        this.file_name = file_name;
        this.file_size = file_size;
        this.file_uid = file_uid;
    }

	

}

