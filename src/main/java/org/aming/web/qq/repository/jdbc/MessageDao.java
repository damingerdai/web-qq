package org.aming.web.qq.repository.jdbc;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.domain.Page;
import org.aming.web.qq.domain.TimeInterval;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.exceptions.WebQQDaoException;

import java.util.List;

/**
 * @author daming
 * @version 2017/10/5.
 */
public interface MessageDao {

    int saveMessage(Message message) throws WebQQDaoException;

    List<Message> getMessage(User sendUser, User receiveUser, Page page) throws WebQQDaoException;

    List<Message> getMessage(User sendUser, User receiveUser, TimeInterval timeInterval) throws WebQQDaoException;
}
