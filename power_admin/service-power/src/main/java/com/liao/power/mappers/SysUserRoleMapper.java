package com.liao.power.mappers;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @BelongsPackage: com.liao.power.mappers
 * @Author: Liao YunYang
 * @Description:
 * @CreateTime: 2023-07-01  22:03
 * @Version: 1.0
 */
@Mapper
public interface SysUserRoleMapper {

    /**
     * 为用户添加角色
     *
     * @param userId     用户id
     * @param roleIdList 角色列表
     * @return
     */
    Integer insertUserRoleByUserIdAndRoleIds(@Param("userId") Long userId, @Param("roleIdList") List<Long> roleIdList);

    /**
     * 根据用户id删除所有角色
     *
     * @param userId 用户id
     * @return
     */
    Integer deleteUserRoleAllByUserId(@Param("userId") Long userId);

}
