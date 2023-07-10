package com.liao.power;

import com.liao.model.entity.po.SysRole;
import com.liao.model.entity.query.SysRoleQuery;
import com.liao.power.mappers.SysRoleMapper;
import com.liao.power.mappers.SysUserRoleMapper;
import com.liao.power.service.SysRoleService;
import com.liao.utils.ResponseResult;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = PowerApplication.class)
class ServicePowerApplicationTests {

    @Resource
    private SysRoleMapper roleMapper;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void t6() {
        String encode = passwordEncoder.encode("liaomou");
        System.out.println(encode);
        System.out.println(encode.length());
    }

    @Test
    void t5() {
        ArrayList<Long> i = new ArrayList<>();
        i.add(1L);
        i.add(2L);

        userRoleMapper.insertUserRoleByUserIdAndRoleIds(1L, i);
    }

    @Test
    void t4() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(5);
        integers.add(4);

        roleMapper.deleteRoleByIds(integers);
    }

    @Test
    void t3() {

        SysRole sysRole = new SysRole();
        sysRole.setRoleCode("8");
        sysRole.setRoleName("8");
        sysRole.setDescription("8");

        roleMapper.insertRole(sysRole);

    }

    @Test
    void t2() {
        SysRoleQuery sysRoleQuery = new SysRoleQuery();
        // sysRoleQuery.setRoleName("1");
        sysRoleService.getRolePage(1, 3, sysRoleQuery);
    }

    @Test
    void t1() {
        ResponseResult sysRoleList = sysRoleService.getSysRoleList();

        System.err.println(sysRoleList);

    }

}
