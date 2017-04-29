
package com.blogmeproject2.repositories;

import com.blogmeproject2.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    // select * from user where username = ?
    // automagic
    public User findByUsername(String username);

}
