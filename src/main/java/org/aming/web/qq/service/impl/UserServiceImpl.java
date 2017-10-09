package org.aming.web.qq.service.impl;

import com.google.common.collect.Sets;
import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.repository.jdbc.UserDao;
import org.aming.web.qq.service.AsyncService;
import org.aming.web.qq.service.UserService;
import org.aming.web.qq.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author daming
 * @version 2017/10/2.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AsyncService asyncService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }

    @Override
    public List<User> getFriendsByUsername(String username) {
        return userDao.getFriendsByUsername(username);
    }

    @Override
    public UserDetails getCurrentUser(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public List<User> getFriendsForCurrentUser(){
        return getFriendsByUsername(getCurrentUser().getUsername());
    }

    @Override
    public Set<User> findMoreUser(String condition){
        Set<User> result = Sets.newHashSet();
        if(StringUtils.isNotBlank(condition)){
            condition = "%" + condition + "%";
            List<User> users = doFindMoreUser(condition);
            UserDetails onlineUser = getCurrentUser();
            for(User user : users){
                if(!user.getUsername().equals(onlineUser.getUsername())){
                    result.add(user);
                }
            }
        }
        return result;
    }

    @Override
    public boolean saveRelationship(Relationship relationship) {
        if(NumberUtils.isGreaterThanZero(userDao.exsitRelationship(relationship))){
            boolean success = NumberUtils.isGreaterThanZero(userDao.saveRelationship(relationship));
            asyncService.saveRelationShipAsync(relationship.reverse());
            return success;
        } else {
            return true;
        }
    }

    @Override
    public boolean addRelationship(User friend){
        UserDetails onLineUserDetails = getCurrentUser();
        User onLineUser = userDao.loadUserByUsername(onLineUserDetails.getUsername());
        Relationship relationship = new Relationship(onLineUser.getId(),friend.getId());
        return NumberUtils.isGreaterThanZero(userDao.saveRelationship(relationship));
    }

    @Override
    public boolean addUser(User user){
        return NumberUtils.isGreaterThanZero( userDao.addUser(user) );
    }

    private List<User> doFindMoreUser(String condition) {
        return userDao.findMoreUser(condition);
    }

    private boolean isGreaterThanZero(int i){
        return i > 0 ? true : false;
    }
}
