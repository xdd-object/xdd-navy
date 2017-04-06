package com.java.xdd.common.domain;

import com.java.xdd.common.util.DateUtil;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import java.util.Date;

/**
 * Created by huanghu on 2017/1/27.
 */
public class BaseDomain implements java.io.Serializable{
    private Long insertAuthor;
    private Long updateAuthor;
    private Date insertTime;
    private Date updateTime;

    public Long getInsertAuthor() {
        return insertAuthor;
    }

    public void setInsertAuthor(Long insertAuthor) {
        this.insertAuthor = insertAuthor;
    }

    public Long getUpdateAuthor() {
        return updateAuthor;
    }

    public void setUpdateAuthor(Long updateAuthor) {
        this.updateAuthor = updateAuthor;
    }

    public Date getInsertTime() {
        return insertTime;
    }
    public String getInsertTimeStr() {
        if (null == insertTime) return null;
        return DateUtil.formatDate(insertTime, null);
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public String getUpdateTimeStr() {
        if (null == updateTime) return null;
        return DateUtil.formatDate(updateTime, null);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
