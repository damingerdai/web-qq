package org.aming.web.qq.repository.jdbc;

import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.exceptions.WebQQDaoException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * @author daming
 * @version 2017/10/1.
 */
public interface UserDao {

    User loadUserByUsername(String username) throws WebQQDaoException;

    List<User> getFriendsByUsername(String username) throws WebQQDaoException;

    List<User> findMoreUser(String condition) throws WebQQDaoException;

    int saveRelationship(Relationship relationship) throws WebQQDaoException;

    int exsitRelationship(Relationship relationship) throws WebQQDaoException;

    int addUser(User user) throws WebQQDaoException;

}
