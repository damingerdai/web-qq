package org.aming.web.qq.service;

import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.exceptions.WebQQServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/**
 * @author daming
 * @version 2017/10/1.
 */
public interface UserService extends UserDetailsService {

    List<User> getFriendsByUsername(String username) throws WebQQServiceException;

    UserDetails getCurrentUser() throws WebQQServiceException;

    List<User> getFriendsForCurrentUser() throws WebQQServiceException;

    Set<User> findMoreUser(@Nonnull String condition) throws WebQQServiceException;

    void addRelationship(User friend) throws WebQQServiceException;

    boolean addUser(User user) throws WebQQServiceException;

    User getUserInfo(@Nullable String username) throws WebQQServiceException;
}
