package org.aming.web.qq.service.impl;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.domain.Page;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.repository.jdbc.MessageDao;
import org.aming.web.qq.repository.jdbc.UserDao;
import org.aming.web.qq.service.MessageService;
import org.aming.web.qq.service.SecurityContextService;
import org.aming.web.qq.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private SecurityContextService securityContextService;

    @Override
    public boolean saveMessage(Message message) {
        if(Objects.isNull(message.getSendUser())){
           message.setSendUser(new User(securityContextService.getCurrentUser()));
        }
        return doSaveMessage(message);
    }

    private boolean doSaveMessage(Message message) {
        return NumberUtils.isGreaterThanZero(messageDao.saveMessage(message));
    }

    @Override
    public List<Message> getMessage(UserDetails sendUser, UserDetails receiveUser, Page page) {
        if(Objects.isNull(sendUser)){
            sendUser = securityContextService.getCurrentUser();
        }
        List<Message> result = doGetMessage(
                userDao.loadUserByUsername(sendUser.getUsername()).getId(), //param: sendUserId
                userDao.loadUserByUsername(receiveUser.getUsername()).getId(), //param: receiveUserId
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

    private List<Message> doGetMessage(String sendUserId,String receiveUserId,Page page){
        return messageDao.getMessage(sendUserId,receiveUserId,page.getCurrentPage(),page.getPageSize());
    }

    @Autowired
    public void setMessageDao(MessageDao messageDao){
        this.messageDao = messageDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setSecurityContextService(SecurityContextService securityContextService){
        this.securityContextService = securityContextService;
    }
}
