package org.aming.web.qq.service;

import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Set;

/**
 * @author daming
 * @version 2017/10/1.
 */
public interface UserService extends UserDetailsService {

    //public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<User> getFriendsByUsername(String username);

    UserDetails getCurrentUser();

    List<User> getFriendsForCurrentUser();

    Set<User> findMoreUser(String condition);

    boolean saveRelationship(Relationship relationship);

    boolean addRelationship(User friend);

    boolean addUser(User user);
}
