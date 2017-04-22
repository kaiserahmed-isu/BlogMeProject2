
package com.blogmeproject2.security;

import com.blogmeproject2.models.Post;
import com.blogmeproject2.models.User;
import com.blogmeproject2.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostOwnerExpression {
    private PostsRepository posts;

    @Autowired
    public PostOwnerExpression(PostsRepository posts) {
        this.posts = posts;
    }

    public boolean isAuthor(User currentUser, Long postId) {
        Post post = posts.findOne(postId);
        User author = post.getAuthor();
        return author != null && author.getId() ==  currentUser.getId();
    }
}
