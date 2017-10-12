package org.aming.web.qq.service;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.domain.Page;
import org.aming.web.qq.domain.TimeInterval;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * @author daming
 * @version 2017/10/5.
 */
public interface MessageService {

    boolean saveMessage(Message message);

    List<Message> getMessage(@Nullable UserDetails sendUser, @Nonnull UserDetails receiveUser, Page page);

    List<Message> getMessage(@Nullable UserDetails sendUser, @Nonnull UserDetails receiveUser,TimeInterval timeInterval);

}
