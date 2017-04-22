
package com.blogmeproject2.repositories;

import com.blogmeproject2.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Post, Long> {
}
