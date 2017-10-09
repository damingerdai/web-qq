package org.aming.web.qq.repository.jdbc.impl;

import org.aming.web.qq.domain.Message;
import org.aming.web.qq.repository.jdbc.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.Types;
import java.util.List;

/**
 * @author daming
 * @version 2017/10/5.
 */
@Repository
public class MessageDaoImpl implements MessageDao {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SAVE_MESSAGE = " INSERT INTO message (id,sendUserId,receiveUserId,content,sendDate,sendDeleteFlag,receiveDeleteFlag,create_id,creation_date,update_id,update_date) SELECT ?,A.id,B.id,?,?,?,?,'system',NOW(),'system',NOW() FROM `user` A  JOIN USER B ON  B.username = ? WHERE A.username = ? ";
    public int saveMessage(Message message) {
        Object[]  args = new Object[]{
                message.getId(),
                message.getContent(),
                new java.sql.Timestamp(message.getSendDate() != null ? message.getSendDate().getTime() : System.currentTimeMillis()),
                message.getSendDeleteFlag(),
                message.getReceiveDeleteFlag(),
                message.getReceiveUser().getUsername(),
                message.getSendUser().getUsername()
        };
        return jdbcTemplate.update(SQL_SAVE_MESSAGE,args);
    }

    private static final String SQL_GET_MESSAGE = "SELECT id,sendUserId,receiveUserId,content,sendDate,sendDeleteFlag,receiveDeleteFlag FROM message A  WHERE (A.sendUserId = ? AND A.receiveUserId = ?) OR (A.sendUserId = ? AND A.receiveUserId = ?) ORDER BY A.sendDate DESC LIMIT ?,?";
    public List<Message> getMessage(String sendUserId, String receiveUserId,int startIndex,int num) {
        Object[] args = new Object[]{
                sendUserId,
                receiveUserId,
                receiveUserId,
                sendUserId,
                startIndex,
                num
        };
        int[] argTypes = new int[]{
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.INTEGER,
                Types.INTEGER
        };
        return jdbcTemplate.query(
                SQL_GET_MESSAGE,
                args,
                argTypes,
                (rs, i) -> {
                    Message message = new Message();
                    message.setId(rs.getString("id"));
                    message.setContent(rs.getString("content"));
                    message.setSendDate(rs.getTimestamp("sendDate"));
                    message.setSendDeleteFlag(rs.getInt("sendDeleteFlag"));
                    message.setReceiveDeleteFlag(rs.getInt("receiveDeleteFlag"));
                    return message;
                    // userid暂时不拿
                });
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
}
