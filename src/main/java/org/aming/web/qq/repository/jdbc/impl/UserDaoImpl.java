package org.aming.web.qq.repository.jdbc.impl;

import com.google.common.collect.Sets;
import org.aming.web.qq.domain.Relationship;
import org.aming.web.qq.domain.User;
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
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_GET_USER_BY_USERNAME = " SELECT id,username,`password`,email FROM User WHERE username = ? ";
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> list = getJdbcTemplate().query(
                SQL_GET_USER_BY_USERNAME
                ,new Object[]{
                        username
                },(rs, i) -> {
                    User user = new User(username,rs.getString("password"));
                    user.setId(rs.getString("id"));
                    user.setEmail(rs.getString("email"));
                    return user;
                });
        if(list != null && list.size() > 0){
            return list.get(0);
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

    private static final String SQL_GET_FRIENDS_BY_USERNAME = " SELECT A.id AS id,A.username AS username,A.`password` AS `password`,A.email AS email FROM user A JOIN relationships B ON a.id = b.userid2 JOIN user C ON B.userid1 = C.id AND C.username = ? ";
    public List<User> getFriendsByUsername(String username) {
        return getJdbcTemplate().query(
                SQL_GET_FRIENDS_BY_USERNAME
                ,new Object[]{
                        username
                },(rs,i) -> {
                    return newUserForResultSet(rs);
                });
    }

    private static final String SQL_FIND_MORE_USER = "SELECT id,username,email FROM user WHERE id LIKE ? OR username LIKE ? OR email LIKE ?";
    public List<User> findMoreUser(String condition){
        return getJdbcTemplate().query(SQL_FIND_MORE_USER,new Object[]{condition,condition,condition},((rs, i) -> {
          return newUserForResultSet(rs);
        }));
    }

    private static final String SQL_SAVE_RELATIONSHIP = " INSERT INTO relationships (userid1,userid2) VALUE ( ? , ?) ";
    public int saveRelationship(Relationship relationship) {
        return getJdbcTemplate().update(SQL_SAVE_RELATIONSHIP,relationship.getUserId1(),relationship.getUserId2());
    }

    private static final String SQL_EXSIT_RELATIONSHIP = " SELECT COUNT(id) FROM relationships WHERE userid1 = ? AND userid2 = ? ";
    public int exsitRelationship(Relationship relationship) {
        Object[] args = new Object[] { relationship.getUserId1(),relationship.getUserId2() };
        int[] argTypes = new int[] { Types.VARCHAR,Types.VARCHAR };
        return getJdbcTemplate().queryForObject(SQL_EXSIT_RELATIONSHIP,args,argTypes,Integer.class);
    }

    private static final String SQL_ADD_USER = " INSERT INTO `user` (id,username,password,email) VALUE (?,?,?,?) ";
    public int addUser(User user){
        Object[] args = new Object[]{ user.getId(),user.getUsername(),user.getPassword(),user.getEmail() };
        int[] argTypes = new int[]{ Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR };
        return getJdbcTemplate().update(SQL_ADD_USER,args,argTypes);
    }

    @Override
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    private User newUserForResultSet(ResultSet rs) throws SQLException{
        User user = new User();
        user.setId(rs.getString("id"));
        user.setUsername(rs.getString("username"));
        //user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
