package com.blogmeproject2.controllers;

import com.blogmeproject2.exceptions.CustomErrorType;
import com.blogmeproject2.models.Post;
import com.blogmeproject2.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kaiser on 4/27/2017.
 */
@RestController
@RequestMapping("/api")
public class PostsAPIController {

    private PostService service;

    @Autowired
    public PostsAPIController(PostService service) {  // local variable
        this.service = service;
    }


    @RequestMapping(value = "/posts/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Post>> listAllPosts() {
        Iterable<Post> posts = service.findAllPosts();
        if (posts.equals(null)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<Iterable<Post>>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPost(@PathVariable("id") long id) {
        Post post = service.findOnePost(id);
        if (post == null) {
            return new ResponseEntity(new CustomErrorType("Post with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

}
