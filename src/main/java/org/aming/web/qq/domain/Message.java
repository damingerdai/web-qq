package org.aming.web.qq.domain;

import org.aming.web.qq.utils.IdGen;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author daming
 * @version 2017/10/5.
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -7770415263565665094L;

    private String id;
    private User sendUser;
    private User receiveUser;
    private String content;
    private Timestamp sendDate;
    private int sendDeleteFlag;
    private int receiveDeleteFlag;

    public String getId() {
        return id;
    }

    public Message setId(String id) {
        this.id = id;
        return this;
    }

    public User getSendUser() {
        return sendUser;
    }

    public Message setSendUser(User sendUser) {
        this.sendUser = sendUser;
        return this;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public Message setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public Message setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public int getSendDeleteFlag() {
        return sendDeleteFlag;
    }

    public Message setSendDeleteFlag(int sendDeleteFlag) {
        this.sendDeleteFlag = sendDeleteFlag;
        return this;
    }

    public int getReceiveDeleteFlag() {
        return receiveDeleteFlag;
    }

    public Message setReceiveDeleteFlag(int receiveDeleteFlag) {
        this.receiveDeleteFlag = receiveDeleteFlag;
        return this;
    }

    public Message() {
        super();
        this.id = IdGen.uuid();
        this.sendDeleteFlag = 0;
        this.receiveDeleteFlag = 0;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }
}
