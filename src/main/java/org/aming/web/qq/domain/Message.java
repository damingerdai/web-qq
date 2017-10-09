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

    public void setId(String id) {
        this.id = id;
    }

    public User getSendUser() {
        return sendUser;
    }

    public void setSendUser(User sendUser) {
        this.sendUser = sendUser;
    }

    public User getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(User receiveUser) {
        this.receiveUser = receiveUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sendDate) {
        this.sendDate = sendDate;
    }

    public int getSendDeleteFlag() {
        return sendDeleteFlag;
    }

    public void setSendDeleteFlag(int sendDeleteFlag) {
        this.sendDeleteFlag = sendDeleteFlag;
    }

    public int getReceiveDeleteFlag() {
        return receiveDeleteFlag;
    }

    public void setReceiveDeleteFlag(int receiveDeleteFlag) {
        this.receiveDeleteFlag = receiveDeleteFlag;
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
