package com.kick.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kick.entity.Likes;
import com.kick.entity.Post;
import com.kick.oauth.User;
import com.kick.oauth.UserRepository;
import com.kick.repository.like.LikesRepository;
import com.kick.repository.post.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class LikesService {
	
	private final LikesRepository likesRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	
	public int likeSave(long postId,long userId) {
	 	Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));
	 	User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id=" + userId));
	 	Likes like = likesRepository.findByPostIdAndUserId(postId, userId);
	 	
	 	if (like==null) {
        	Likes likes = Likes.builder()
            					.post(post)
            					.user(user)
            					.build();
            likesRepository.save(likes);
        } else {
        	likesRepository.delete(like);
        	
        }
        return likesRepository.countPostAndUser(postId);
	}
 
}