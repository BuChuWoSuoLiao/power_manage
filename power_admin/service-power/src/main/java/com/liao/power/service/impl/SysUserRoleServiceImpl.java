package com.liao.power.service.impl;

import com.liao.exception.SqlException.DeleteException;
import com.liao.model.entity.enums.ResultCodeEnum;
import com.liao.power.mappers.SysUserRoleMapper;
import com.liao.power.service.SysUserRoleService;
import com.liao.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsPackage: com.liao.power.service.impl
 * @Author: Liao YunYang
 * @Description: 用户角色关系业务层
 * @CreateTime: 2023-07-01  22:04
 * @Version: 1.0
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 给用户分配权限
     *
     * @param userId  用户id
     * @param roleIds 角色id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult saveRoleByUserId(Long userId, String roleIds) {
        // 删除所有权限
        try {
            sysUserRoleMapper.deleteUserRoleAllByUserId(userId);
            if (roleIds.equals("0")) return ResponseResult.ok();

            if (roleIds != "0") {
                // 处理数据
                String[] split = roleIds.split(",");
                List<Long> idList = Arrays.stream(split)
                        .map(sId -> Long.parseLong(sId))
                        .collect(Collectors.toList());

                sysUserRoleMapper.insertUserRoleByUserIdAndRoleIds(userId, idList);
            }
        } catch (Exception e) {
            throw new DeleteException(ResultCodeEnum.CODE_602.getMsg());
        }

        return ResponseResult.ok();
    }
}
