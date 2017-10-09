package org.aming.web.qq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author daming
 * @version 2017/10/5.
 */
public class Relationship implements Serializable {
    private int id;
    private String userId1;
    private String userId2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId1() {
        return userId1;
    }

    public void setUserId1(String userId1) {
        this.userId1 = userId1;
    }

    public String getUserId2() {
        return userId2;
    }

    public void setUserId2(String userId2) {
        this.userId2 = userId2;
    }

    public Relationship(){
        super();
    }

    public Relationship(String userId1,String userId2){
        super();
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    public Relationship(int id,String userId1,String userId2){
        super();
        this.id = id;
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this);
    }

    public Relationship reverse(){
        return new Relationship(this.userId2,this.userId1);
    }


}
