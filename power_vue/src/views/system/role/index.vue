<template>
  <div class="app-container">
    <!--查询表单-->
    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="24">
            <el-form-item label="角色名称">
              <el-input style="width: 100%" v-model="roleQuery.roleName" placeholder="角色名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="display:flex">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="getRolePage()">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
          <el-button type="success" icon="el-icon-plus" size="mini" @click="add">添 加</el-button>
          <el-button class="btn-add" size="mini" @click="batchRemove()">批量删除</el-button>
        </el-row>
      </el-form>
    </div>

    <!-- 表格 -->
    <el-table
      :data="roleList"
      stripe
      border
      style="width: 100%;margin-top: 10px;"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection"/>
      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="roleName" label="角色名称"/>
      <el-table-column prop="roleCode" label="角色编码"/>
      <el-table-column prop="createTime" label="创建时间" width="160"/>
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="修改"/>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataByIds(scope.row.id)"
                     title="删除"/>
          <el-button type="warning" icon="el-icon-baseball" size="mini" @click="showAssignAuth(scope.row)"
                     title="分配权限"/>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页组件 -->
    <el-pagination
      :current-page="page"
      :total="total"
      :page-size="limit"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getRolePage"
    />

    <!--添加用户弹框-->
    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="sysRole" label-width="150px" size="small" style="padding-right: 40px;">
        <el-form-item label="角色名称">
          <el-input v-model="sysRole.roleName"/>
        </el-form-item>
        <el-form-item label="角色编码">
          <el-input v-model="sysRole.roleCode"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>

import api from '@/api/system/role'

export default {
  name: "Role",
  data() {
    return {
      dialogVisible: false, // 添加用户弹窗
      roleList: [], // 角色列表
      page: 1,      // 当前页
      limit: 10,    // 显示数据条数
      total: 0,     // 总记录数
      roleQuery: {}, // 查询条件
      sysRole: {},  // 角色对象
      batchRemoveIds: '' // 批量删除的id
    }
  },
  created() {
    this.getRolePage()
  },
  methods: {
    // 条件分页查询方法
    async getRolePage(pageNum = 1) {
      this.page = pageNum
      await api.getRolePage(this.page, this.limit, this.roleQuery).then(response => {
        this.roleList = response.pageInfo.list
        this.total = response.pageInfo.total
      })
    },

    // 删除角色
    async removeDataByIds(id) {
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return api.removeRole(id)
      }).then((response) => {
        this.getRolePage(this.page)
        this.$message.success('删除成功')
      }).catch(() => {
        this.$message.info('取消删除')
      })
    },

    // 添加角色或者删除角色
    saveOrUpdate() {
      if (!this.sysRole.id) {
        this.saveRole()
      } else {
        this.updateRole()
      }
    },

    // 添加角色
    saveRole() {
      api.saveRole(this.sysRole).then(response => {
        this.$message.success(response.msg || '操作成功')
        this.dialogVisible = false
        this.getRolePage(this.page)
      })
    },

    // 修改角色
    updateRole() {
      api.changeRole(this.sysRole).then(resp => {
        this.$message.success(resp.msg || '操作成功')
        this.dialogVisible = false
        this.getRolePage(this.page)
      })
    },

    // 点击按钮弹出修改狂
    edit(id) {
      this.dialogVisible = true
      // 查询记录
      api.getRoleById(id).then(resp => {
        this.sysRole = resp.role
      })
    },

    // 批量删除
    batchRemove() {
      this.removeDataByIds(this.batchRemoveIds)
    },

    // 获取复选框内容
    handleSelectionChange(selection) {
      var res = ''
      for (let i = 0; i < selection.length; i++) {
        res += ',' + selection[i].id
      }
      this.batchRemoveIds = res.substring(1)
    },

    // 点击添加按钮
    add() {
      this.dialogVisible = true
    },

    // 重置表单
    resetData() {
      this.roleQuery = {}
      this.getRolePage()
    },

    // 跳转到分配菜单页面
    showAssignAuth(row) {
      this.$router.push('/system/assignMenu?id=' + row.id + '&roleName=' + row.roleName);
    }
  }
}


</script>

<style scoped>

</style>
