package com.liao.power.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liao.exception.SqlException.DeleteException;
import com.liao.exception.SqlException.InsertException;
import com.liao.exception.SqlException.UpdateException;
import com.liao.exception.UserException.UsernameDuplicatedException;
import com.liao.model.entity.enums.ResultCodeEnum;
import com.liao.model.entity.po.SysUser;
import com.liao.model.entity.query.SysUserQuery;
import com.liao.power.mappers.SysUserMapper;
import com.liao.power.service.SysUserService;
import com.liao.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsPackage: com.liao.power.service.impl
 * @Author: Liao YunYang
 * @Description: 用户业务层
 * @CreateTime: 2023-07-01  18:08
 * @Version: 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 获取所有用户
     *
     * @return
     */
    @Override
    public ResponseResult getSysUserList() {
        return ResponseResult.ok().put("userList", sysUserMapper.selectByAll(new SysUserQuery()));
    }

    /**
     * 根据id查询记录
     *
     * @param id 用户id
     * @return
     */
    @Override
    public ResponseResult getSysUserById(Long id) {
        return ResponseResult.ok().put("user", sysUserMapper.selectUserById(id));
    }

    /**
     * 条件分页查询用户
     *
     * @param page      当前页
     * @param limit     每页显示记录数
     * @param userQuery 条件查询
     * @return
     */
    @Override
    public ResponseResult getUserPage(Integer page, Integer limit, SysUserQuery userQuery) {

        List<SysUser> userList = sysUserMapper.selectByAll(userQuery);

        // 计算总记录数
        int total = userList.size();

        List<SysUser> resList = userList.stream()
                .skip((page - 1) * limit)
                .limit(limit)
                .collect(Collectors.toList());

        // 计算总页数
        int pageSum = total % limit == 0 ? total / limit : total / limit + 1;
        PageHelper.startPage(page, limit);
        PageInfo<SysUser> scorePageInfo = new PageInfo<>(resList);

        // 总记录数
        scorePageInfo.setTotal(total);
        // 总页数
        scorePageInfo.setPages(pageSum);
        // 清除分页缓存
        PageHelper.clearPage();

        return ResponseResult.ok().put("pageInfo", scorePageInfo);
    }

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return
     */
    @Override
    public ResponseResult saveUser(SysUser user) {
        // 判断用户名是否存在
        if (sysUserMapper.selectUserByUserName(user.getUsername()) != null) {
            throw new UsernameDuplicatedException(ResultCodeEnum.CODE_902.getMsg());
        }

        // 封装密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            sysUserMapper.insertUser(user);
        } catch (Exception e) {
            throw new InsertException(ResultCodeEnum.CODE_600.getMsg());
        }
        return ResponseResult.ok();
    }

    /**
     * 更改用户信息
     * id是必传参数
     *
     * @param user 用户对象
     * @return
     */
    @Override
    public ResponseResult changeUser(SysUser user) {
        try {
            sysUserMapper.updateUserByUser(user);
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
    public ResponseResult removeUserByIds(String ids) {
        try {
            String[] split = ids.split(",");

            List<Integer> collect = Arrays.stream(split)
                    .map(id -> Integer.parseInt(id))
                    .collect(Collectors.toList());

            sysUserMapper.deleteUserByIds(collect);
        } catch (Exception e) {
            throw new DeleteException(ResultCodeEnum.CODE_501.getMsg());
        }

        return ResponseResult.ok();
    }
}
