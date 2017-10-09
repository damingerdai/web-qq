package org.aming.web.qq.service;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.domain.Relationship;

/**
 * @author daming
 * @version 2017/10/5.
 */
public interface AsyncService {

    void saveRelationShipAsync(Relationship relationship);

    void saveMessageAsync(Message message);
}
