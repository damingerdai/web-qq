package org.aming.web.qq.service.impl;

import com.google.common.collect.Sets;
import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.exceptions.WebQQDaoException;
import org.aming.web.qq.exceptions.WebQQServiceException;
import org.aming.web.qq.repository.jdbc.UserDao;
import org.aming.web.qq.service.UserService;
import org.aming.web.qq.utils.NumberUtils;
import org.aming.web.qq.utils.SecurityContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @author daming
 * @version 2017/10/2.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            return Optional
                    .of(userDao.loadUserByUsername(username))
                    .get();
        } catch (WebQQDaoException ex){
            throw new UsernameNotFoundException("用户不存在");
        }
    }

    @Override
    public List<User> getFriendsByUsername(String username) throws WebQQServiceException {
        try{
            return userDao.getFriendsByUsername(username);
        } catch (WebQQDaoException ex) {
            throw new WebQQServiceException("获取好友失败",ex);
        }
    }

    @Override
    public UserDetails getCurrentUser() throws WebQQServiceException {
        try{
            return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (WebQQDaoException ex) {
            throw new WebQQServiceException("获取当前登录用户失败",ex);
        }

    }

    @Override
    public List<User> getFriendsForCurrentUser() throws WebQQServiceException {
        try{
            return getFriendsByUsername(getCurrentUser().getUsername());
        } catch (WebQQDaoException ex) {
            throw new WebQQServiceException("获取当前登录用户的好友失败",ex);
        } catch (WebQQServiceException ex) {
            throw ex;
        }

    }

    @Override
    public Set<User> findMoreUser(@Nonnull String condition) throws WebQQServiceException {
        try{
            UserDetails onlineUser = getCurrentUser();
            return doFindMoreUser( "%" + condition + "%").stream()
                    .filter(user -> !user.getUsername().equals(onlineUser))
                    .collect(toSet());
        } catch (WebQQDaoException ex) {
            throw new WebQQServiceException("发现好友失败",ex);
        } catch (WebQQServiceException ex) {
            throw ex;
        }

    }

    @Override
    public void addRelationship(User friend) throws WebQQServiceException {
        try{
            User onLineUser = userDao.loadUserByUsername(getCurrentUser().getUsername());

            Relationship relationship = new Relationship()
                    .setUserId1(onLineUser.getId())
                    .setUserId2(friend.getId());

            if(NumberUtils.equalsZero(userDao.exsitRelationship(relationship))){
                doAddRelationship(relationship);
            }
            if(NumberUtils.equalsZero(userDao.exsitRelationship(relationship.reverse()))){
                doAddRelationship(relationship.reverse());
            }
        } catch (WebQQDaoException ex) {
            throw new WebQQServiceException("添加好友失败",ex);
        } catch (WebQQServiceException ex) {
            throw ex;
        }

    }

    @Override
    @Transactional(readOnly = false)
    public boolean addUser(User user){
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        if(userDao.loadUserByUsername(user.getUsername()) != null){
            throw new RuntimeException("用户名重复");
        }
        return NumberUtils.isGreaterThanZero( userDao.addUser(user) );
    }

    @Override
    public User getUserInfo(@Nullable String username) {
        if(StringUtils.isEmpty(username)){
            username = SecurityContextUtils.getCurrentUser().getUsername();
        }
        return userDao.loadUserByUsername(username);
    }

    private List<User> doFindMoreUser(String condition) {
        return userDao.findMoreUser(condition);
    }

    private boolean doAddRelationship(Relationship relationship){
        return NumberUtils.isGreaterThanZero(userDao.saveRelationship(relationship));
    }


    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }


    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
}
