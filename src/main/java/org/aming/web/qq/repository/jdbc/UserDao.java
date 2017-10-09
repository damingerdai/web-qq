package org.aming.web.qq.repository.jdbc;

import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author daming
 * @version 2017/10/1.
 */
public interface UserDao {

    User loadUserByUsername(String username) throws UsernameNotFoundException;

    List<User> getFriendsByUsername(String username);

    JdbcTemplate getJdbcTemplate();

    List<User> findMoreUser(String condition);

    int saveRelationship(Relationship relationship);

    int exsitRelationship(Relationship relationship);

    int addUser(User user);
}
