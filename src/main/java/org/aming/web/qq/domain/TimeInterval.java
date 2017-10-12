package org.aming.web.qq.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class TimeInterval implements Serializable{

    private static final long serialVersionUID = -5374504819644936313L;

    private Timestamp beginDateTime;
    private Timestamp endDateTime;

    public Timestamp getBeginDateTime() {
        return beginDateTime;
    }

    public TimeInterval setBeginDateTime(Timestamp beginDateTime) {
        this.beginDateTime = beginDateTime;
        return this;
    }

    public Timestamp getEndDateTime() {
        return endDateTime;
    }

    public TimeInterval setEndDateTime(Timestamp endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public TimeInterval() {
        super();
        this.beginDateTime = new Timestamp(0L);
        this.endDateTime = new Timestamp(System.currentTimeMillis());
    }

    public TimeInterval(Timestamp beginDateTime,Timestamp endDateTime){
        super();
        this.beginDateTime = beginDateTime;
        this.endDateTime = endDateTime;
    }
}
