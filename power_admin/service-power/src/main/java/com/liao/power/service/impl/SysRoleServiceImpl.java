package com.liao.power.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liao.exception.SqlException.DeleteException;
import com.liao.exception.SqlException.InsertException;
import com.liao.exception.SqlException.UpdateException;
import com.liao.model.entity.enums.ResultCodeEnum;
import com.liao.model.entity.po.SysRole;
import com.liao.model.entity.query.SysRoleQuery;
import com.liao.power.mappers.SysRoleMapper;
import com.liao.power.mappers.SysRoleMenuMapper;
import com.liao.power.service.SysRoleService;
import com.liao.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @BelongsPackage: com.liao.power.service.impl
 * @Author: Liao YunYang
 * @Description: 角色业务层
 * @CreateTime: 2023-06-28  23:00
 * @Version: 1.0
 */
@Service(value = "RoleService")
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;


    /**
     * 获取所有角色
     *
     * @return 所有角色，集合
     */
    @Override
    public ResponseResult getSysRoleList() {
        // 封装查询条件
        SysRoleQuery sysRoleQuery = new SysRoleQuery();

        List<SysRole> resultList = sysRoleMapper.selectByAll(sysRoleQuery);

        return ResponseResult.ok().put("roleList", resultList);
    }

    /**
     * 给角色分配菜单
     *
     * @param roleId  角色id
     * @param menuIds 菜单id集合
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult doAssign(Long roleId, String menuIds) {
        try {
            // 删除所有菜单
            sysRoleMenuMapper.deleteRoleMenuAllByRoleId(roleId);

            if (menuIds.equals("0")) ResponseResult.ok();

            if (menuIds != "0") {
                String[] split = menuIds.split(",");
                List<Long> ids = Arrays.stream(split)
                        .map(id -> Long.parseLong(id))
                        .collect(Collectors.toList());
                sysRoleMenuMapper.insertUserRoleByUserIdAndRoleIds(roleId, ids);
            }
        } catch (Exception e) {
            throw new DeleteException(ResultCodeEnum.CODE_602.getMsg());
        }
        return ResponseResult.ok();
    }


    /**
     * 条件分页查询角色
     *
     * @param page      当前页
     * @param limit     每页显示记录数
     * @param roleQuery 条件查询
     * @return
     */
    @Override
    public ResponseResult getRolePage(Integer page, Integer limit, SysRoleQuery roleQuery) {
        // 查询所有角色记录
        SysRoleQuery sysRoleQuery = new SysRoleQuery();
        sysRoleQuery.setRoleName(roleQuery.getRoleName());
        List<SysRole> roleList = sysRoleMapper.selectByAll(sysRoleQuery);

        // 总记录数
        int total = roleList.size();

        List<SysRole> collect = roleList.stream()
                .skip((page - 1) * limit)
                .limit(limit)
                .collect(Collectors.toList());

        // 计算总页数
        int pageSum = total % limit == 0 ? total / limit : total / limit + 1;
        PageHelper.startPage(page, limit);
        PageInfo<SysRole> scorePageInfo = new PageInfo<>(collect);

        // 总记录数
        scorePageInfo.setTotal(total);
        // 总页数
        scorePageInfo.setPages(pageSum);
        // 清除分页缓存
        PageHelper.clearPage();
        return ResponseResult.ok().put("pageInfo", scorePageInfo);
    }

    /**
     * 添加role对象
     *
     * @param role 角色对象
     * @return
     */
    @Override
    public ResponseResult saveRole(SysRole role) {
        // 判断角色是否存在
        if (sysRoleMapper.selectRoleByRoleName(role.getRoleName()) != null) {
            throw new InsertException(ResultCodeEnum.CODE_600.getMsg());
        }

        try {
            sysRoleMapper.insertRole(role);
        } catch (Exception e) {
            throw new InsertException(ResultCodeEnum.CODE_600.getMsg());
        }
        return ResponseResult.ok();
    }

    /**
     * 更改角色信息
     * id是必传参数
     *
     * @param role 角色对象
     * @return
     */
    @Override
    public ResponseResult changeRole(SysRole role) {

        try {
            sysRoleMapper.updateRoleByRole(role);
        } catch (Exception e) {
            throw new UpdateException(ResultCodeEnum.CODE_601.getMsg());
        }


        return ResponseResult.ok();
    }

    /**
     * 删除角色
     *
     * @param ids id集合
     * @return
     */
    @Override
    public ResponseResult removeRoleByIds(String ids) {
        try {
            String[] split = ids.split(",");

            List<Integer> collect = Arrays.stream(split)
                    .map(id -> Integer.parseInt(id))
                    .collect(Collectors.toList());

            sysRoleMapper.deleteRoleByIds(collect);
        } catch (Exception e) {
            throw new DeleteException(ResultCodeEnum.CODE_602.getMsg());
        }

        return ResponseResult.ok();
    }

    /**
     * 根据id查询角色
     *
     * @param id 角色id
     * @return
     */
    @Override
    public ResponseResult getSysRoleById(Integer id) {
        return ResponseResult.ok().put("role", sysRoleMapper.selectById(id));
    }

    /**
     * 根据用户id查询他所拥有的角色
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public ResponseResult getRoleByUserId(Long userId) {
        List<SysRole> userRoles = sysRoleMapper.selectRolesByUserId(userId);

        Set<Long> userRoleIds = userRoles.stream()
                .map(role -> role.getId())
                .collect(Collectors.toSet());

        return ResponseResult.ok().put("userRoleIds", userRoleIds).put("allRoles", sysRoleMapper.selectByAll(new SysRoleQuery()));
    }
}
