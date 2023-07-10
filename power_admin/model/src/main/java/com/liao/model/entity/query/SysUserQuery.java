package com.liao.model.entity.query;

import java.io.Serializable;
import java.util.Objects;

/**
 * @BelongsPackage: com.liao.model.entity.query
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-07-01  18:28
 * @Version: 1.0
 */
public class SysUserQuery implements Serializable {
    private static final long serialVersionUID = 2262182921333593815L;
    private Long id;

    private String userName;

    private String password;

    private String name;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserQuery query = (SysUserQuery) o;
        return Objects.equals(id, query.id) && Objects.equals(userName, query.userName) && Objects.equals(password, query.password) && Objects.equals(name, query.name) && Objects.equals(status, query.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, name, status);
    }

    @Override
    public String toString() {
        return "UserQuery{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
