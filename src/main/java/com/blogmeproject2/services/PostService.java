
package com.blogmeproject2.services;

import com.blogmeproject2.models.Post;
import com.blogmeproject2.repositories.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostsRepository repository;

    @Autowired
    public PostService(PostsRepository repository) {
        this.repository = repository;
    }

    public Post findOnePost(long id) {
        return repository.findOne(id);
    }

    public Iterable<Post> findAllPosts() {
        return repository.findAll();
    }

    public void save(Post post) {
        repository.save(post);
    }

    public void update(Post post) {
        repository.save(post);
    }

    public void deletePostWith(Long id) {
        repository.delete(id);
    }
}
