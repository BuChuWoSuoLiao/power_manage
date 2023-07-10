package com.liao.power.mappers;

import com.liao.model.entity.po.SysUser;
import com.liao.model.entity.query.SysUserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsPackage: com.liao.power.mappers
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-07-01  18:08
 * @Version: 1.0
 */
@Mapper
public interface SysUserMapper {

    /**
     * 查询所有用户
     *
     * @return
     */
    List<SysUser> selectByAll(@Param("query") SysUserQuery query);

    /**
     * 根据id查询用户
     *
     * @param id 用户id
     * @return
     */
    SysUser selectUserById(@Param("id") Long id);

    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return
     */
    SysUser selectUserByUserName(@Param("userName") String userName);

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return
     */
    Integer insertUser(@Param("user") SysUser user);

    /**
     * 更改用户信息
     *
     * @param user 用户对象
     * @return
     */
    Integer updateUserByUser(@Param("user") SysUser user);

    /**
     * 根据id删除用户
     *
     * @param list id集合
     * @return
     */
    Integer deleteUserByIds(@Param("list") List<Integer> list);


}
