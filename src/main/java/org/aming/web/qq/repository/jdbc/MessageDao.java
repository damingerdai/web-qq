package org.aming.web.qq.repository.jdbc;

import org.aming.web.qq.domain.Message;

import java.util.List;

/**
 * @author daming
 * @version 2017/10/5.
 */
public interface MessageDao {

    int saveMessage(Message message);

    List<Message> getMessage(String sendUserId, String receiveUserId,int startIndex,int endIndex);
}
