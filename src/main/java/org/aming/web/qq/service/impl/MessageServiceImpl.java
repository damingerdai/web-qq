package org.aming.web.qq.service.impl;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.domain.Page;
import org.aming.web.qq.domain.TimeInterval;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.repository.jdbc.MessageDao;
import org.aming.web.qq.repository.jdbc.UserDao;
import org.aming.web.qq.service.MessageService;
import org.aming.web.qq.service.SecurityContextService;
import org.aming.web.qq.utils.NumberUtils;
import org.aming.web.qq.utils.SecurityContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * @author daming
 * @version 2017/10/5.
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService{

    private MessageDao messageDao;
    private UserDao userDao;


    @Override
    public boolean saveMessage(Message message) {
        if(Objects.isNull(message.getSendUser())){
           message.setSendUser(new User(SecurityContextUtils.getCurrentUser()));
        }
        return doSaveMessage(message);
    }

    private boolean doSaveMessage(Message message) {
        return NumberUtils.isGreaterThanZero(messageDao.saveMessage(message));
    }

    @Override
    public List<Message> getMessage(@Nullable UserDetails sendUser, @Nonnull UserDetails receiveUser, Page page) {
        if(Objects.isNull(sendUser)){
            sendUser = SecurityContextUtils.getCurrentUser();
        }
        List<Message> result = doGetMessage(
                userDao.loadUserByUsername(sendUser.getUsername()), //param: sendUser
                userDao.loadUserByUsername(receiveUser.getUsername()), //param: receiveUser
                page //param: page
        );
        return result
                .stream()
                .sorted(
                        Comparator
                                .comparing(
                                        Message ::getSendDate
                                ))
                .collect(
                        Collectors
                                .toList()
                );
    }

    @Override
    public List<Message> getMessage(@Nullable UserDetails sendUser,@Nonnull UserDetails receiveUser, TimeInterval timeInterval) {
        if(Objects.isNull(sendUser)){
            sendUser = SecurityContextUtils.getCurrentUser();
        }
        List<Message> result = doGetMessage(
                userDao.loadUserByUsername(sendUser.getUsername()), //param: sendUser
                userDao.loadUserByUsername(receiveUser.getUsername()), //param: receiveUser
                timeInterval //param: timeInterval
        );
        return result
                .stream()
                .sorted(
                        Comparator
                                .comparing(
                                        Message ::getSendDate
                                ))
                .collect(
                        Collectors
                                .toList()
                );
    }

    private List<Message> doGetMessage(User sendUser,User receiveUser,Page page){
        return messageDao.getMessage(sendUser,receiveUser,page);
    }

    private List<Message> doGetMessage(User sendUser,User receiveUser,TimeInterval TimeInterval){
        return messageDao.getMessage(sendUser,receiveUser,TimeInterval);
    }

    @Autowired
    public void setMessageDao(MessageDao messageDao){
        this.messageDao = messageDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

}
