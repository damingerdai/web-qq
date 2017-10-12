package org.aming.web.qq.repository.jdbc.impl;

import com.google.common.collect.Sets;
import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
import org.aming.web.qq.exceptions.WebQQDaoException;
import org.aming.web.qq.repository.jdbc.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

/**
 * @author daming
 * @version 2017/10/2.
 */
@Repository
public class UserDaoImpl implements UserDao{

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_USER_BY_USERNAME = " SELECT id,username,`password`,email FROM User WHERE username = ? ";
    public User loadUserByUsername(String username) throws WebQQDaoException {
        try{
            List<User> list = jdbcTemplate.query(
                    SQL_GET_USER_BY_USERNAME
                    ,new Object[]{
                            username
                    }, ( rs, i) ->
                            newUserWithoutPasswordFormResultSet(rs)
                                    .setPassword(rs.getString("password"))
            );
            if(list != null && list.size() > 0){
                return list.get(0);
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new WebQQDaoException(SQL_GET_USER_BY_USERNAME, ex);
        }

    }

    private static final String SQL_GET_FRIENDS_BY_USERNAME = " SELECT A.id AS id,A.username AS username,A.`password` AS `password`,A.email AS email FROM user A JOIN relationships B ON a.id = b.userid2 JOIN user C ON B.userid1 = C.id AND C.username = ? ";
    public List<User> getFriendsByUsername(String username) throws WebQQDaoException {
        try{
            return jdbcTemplate.query(
                    SQL_GET_FRIENDS_BY_USERNAME
                    ,new Object[]{
                            username
                    },(rs,i) -> newUserWithoutPasswordFormResultSet(rs));
        } catch (Exception ex) {
            throw new WebQQDaoException(SQL_GET_FRIENDS_BY_USERNAME, ex);
        }

    }

    private static final String SQL_FIND_MORE_USER = "SELECT id,username,email FROM user WHERE id LIKE ? OR username LIKE ? OR email LIKE ?";
    public List<User> findMoreUser(String condition) throws WebQQDaoException{
        try {
            return jdbcTemplate.query(
                    SQL_FIND_MORE_USER,
                    new Object[]{
                            condition,
                            condition,
                            condition
                    },(rs, i) -> newUserWithoutPasswordFormResultSet(rs));
        } catch (Exception ex) {
            throw new WebQQDaoException(SQL_FIND_MORE_USER, ex);
        }

    }

    private static final String SQL_SAVE_RELATIONSHIP = " INSERT INTO relationships (userid1,userid2) VALUE ( ? , ?) ";
    public int saveRelationship(Relationship relationship) throws WebQQDaoException {
        try{
            return jdbcTemplate.update(
                    SQL_SAVE_RELATIONSHIP,
                    relationship.getUserId1(),
                    relationship.getUserId2()
            );
        } catch (Exception ex) {
            throw new WebQQDaoException(SQL_SAVE_RELATIONSHIP, ex);
        }
    }

    private static final String SQL_EXSIT_RELATIONSHIP = " SELECT COUNT(id) FROM relationships WHERE userid1 = ? AND userid2 = ? ";
    public int exsitRelationship(Relationship relationship) throws WebQQDaoException {
        try{
            Object[] args = new Object[] {
                    relationship.getUserId1(),
                    relationship.getUserId2()
            };
            int[] argTypes = new int[] {
                    Types.VARCHAR
                    ,Types.VARCHAR
            };
            return jdbcTemplate.queryForObject(
                    SQL_EXSIT_RELATIONSHIP,
                    args,
                    argTypes,
                    Integer.class
            );
        } catch (Exception ex) {
            throw new WebQQDaoException(SQL_EXSIT_RELATIONSHIP, ex);
        }

    }

    private static final String SQL_ADD_USER = " INSERT INTO `user` (id,username,password,email) VALUE (?,?,?,?) ";
    public int addUser(User user) throws WebQQDaoException{
        try{
            Object[] args = new Object[]{
                    user.getId(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getEmail()
            };
            int[] argTypes = new int[]{
                    Types.VARCHAR,
                    Types.VARCHAR,
                    Types.CHAR,
                    Types.VARCHAR
            };
            return jdbcTemplate.update(
                    SQL_ADD_USER,
                    args,
                    argTypes
            );
        } catch (Exception ex){
            throw new WebQQDaoException(SQL_ADD_USER, ex);
        }
    }


    private User newUserWithoutPasswordFormResultSet(ResultSet rs) throws SQLException{
        return new User()
                .setId(rs.getString("id"))
                .setUsername(rs.getString("username"))
                .setEmail(rs.getString("email")); //一般是没有必要获取密码的
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
