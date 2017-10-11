package org.aming.web.qq.service.impl;

import com.google.common.collect.Sets;
import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.exceptions.WebQQException;
import org.aming.web.qq.repository.jdbc.UserDao;
import org.aming.web.qq.service.AsyncService;
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

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author daming
 * @version 2017/10/2.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private AsyncService asyncService;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            return Optional
                    .of(userDao.loadUserByUsername(username))
                    .get();
        } catch (Exception ex){
            throw new UsernameNotFoundException("用户不存在");
        }
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
    public void addRelationship(User friend){
        UserDetails onLineUserDetails = getCurrentUser();
        User onLineUser = userDao.loadUserByUsername(onLineUserDetails.getUsername());
        Relationship relationship = new Relationship().setUserId1(onLineUser.getId()).setUserId2(friend.getId());
        if(NumberUtils.equalsZero(userDao.exsitRelationship(relationship))){
            doAddRelationship(relationship);
        }
        if(NumberUtils.equalsZero(userDao.exsitRelationship(relationship.reverse()))){
            doAddRelationship(relationship.reverse());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean addUser(User user){
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        if(userDao.loadUserByUsername(user.getUsername()) != null){
            throw new WebQQException(5002,"用户名重复",new RuntimeException(""));
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
    public void setAsyncService(AsyncService asyncService){
        this.asyncService = asyncService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
}
