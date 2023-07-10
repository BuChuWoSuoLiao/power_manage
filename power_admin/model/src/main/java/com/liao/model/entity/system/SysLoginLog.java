package com.liao.model.entity.system;

import com.liao.model.entity.base.BaseEntity;

import java.util.Date;


public class SysLoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String username;

    private String ipaddr;

    private Integer status;

    private String msg;

    private Date accessTime;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public String toString() {
        return "SysLoginLog{" +
                "username='" + username + '\'' +
                ", ipaddr='" + ipaddr + '\'' +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", accessTime=" + accessTime +
                '}';
    }
}