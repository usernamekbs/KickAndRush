package com.kick.repository.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kick.entity.Likes;
import com.kick.entity.Post;
import com.kick.oauth.User;

public interface LikesRepository extends JpaRepository<Likes, Long>{

	@Query("SELECT count(l) FROM Likes l WHERE l.post.id=:postId") 
	int countPostAndUser(@Param("postId") long postId);

	@Query("SELECT l FROM Likes l JOIN FETCH l.post p JOIN FETCH l.user u WHERE p.id = :postId AND u.id = :userId")
	Likes findByPostIdAndUserId(@Param("postId") Long postId, @Param("userId") Long userId);

	Likes findByPostAndUser(Post post, User user);

}
