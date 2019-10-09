package com.laurus.wmcs.dao.secondary;

import com.laurus.wmcs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReadRepository extends  JpaRepository<User, Long> {
    User findByUsername(String s);
}
