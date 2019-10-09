package com.laurus.wmcs.dao.primary;

import com.laurus.wmcs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserWriteRepository extends  JpaRepository<User, Long> {
}
