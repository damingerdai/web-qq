package org.aming.web.qq.domain;

import org.aming.web.qq.utils.IdGen;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author daming
 * @version 2017/9/30.
 */
public class User extends AbstractUserDetails implements UserDetails {

    private static final long serialVersionUID = -5509256807259591938L;

    private String id;
    private String username;
    private String password;
    private String email;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User(String id, String username, String password, String email) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password) {
        super();
        this.id = IdGen.uuid();
        this.username = username;
        this.password = password;
    }

    public User(UserDetails userDetails){
        this(userDetails.getUsername(),userDetails.getPassword());
    }

    public User() {
        super();
        this.id = IdGen.uuid();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj == this){
            return true;
        }
        if(obj instanceof User){
            if(obj instanceof UserDetails){
                UserDetails userDetails = (UserDetails)obj;
                if(this.getUsername().equals(userDetails.getUsername())){
                    return true;
                }
            }else{
                User user = (User)obj;
                if(this.getUsername().equals(user.getUsername())){
                    return true;
                }
            }
        }
        return false;
    }
}
