package org.aming.web.qq.service.impl;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.repository.jdbc.MessageDao;
import org.aming.web.qq.repository.jdbc.UserDao;
import org.aming.web.qq.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author daming
 * @version 2017/10/5.
 */
@Service
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;

    @Async
    public void saveRelationShipAsync(Relationship relationship) {
        userDao.saveRelationship(relationship);
    }

    @Async
    public void saveMessageAsync(Message message) {
        messageDao.saveMessage(message);
    }
}
