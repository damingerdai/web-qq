package org.aming.web.qq.service;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author daming
 * @version 2017/10/5.
 */
public interface MessageService {

    boolean saveMessage(Message message);

    List<Message> getMessage(UserDetails sendUser, UserDetails receiveUser,Page page);
}
