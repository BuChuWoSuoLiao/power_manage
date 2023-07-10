<template>
  <div class="app-container">

    <div class="search-div">
      <el-form label-width="70px" size="small">
        <el-row>
          <el-col :span="8">
            <el-form-item label="关 键 字">
              <el-input style="width: 95%" v-model="userQuery.username" placeholder="用户名"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row style="display:flex">
          <el-button type="primary" icon="el-icon-search" size="mini" @click="getUserList()">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetData">重置</el-button>
          <el-button type="success" icon="el-icon-plus" size="mini" @click="addUser">添 加</el-button>
        </el-row>
      </el-form>
    </div>

    <!-- 列表 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      stripe
      border
      style="width: 100%;margin-top: 10px;">

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="username" label="用户名" width="180"/>
      <el-table-column prop="name" label="姓名" width="110"/>
      <el-table-column prop="phone" label="手机"/>
      <el-table-column label="所属角色">
        <template slot-scope="scope">
          <div>
            <el-tag v-for="item in scope.row.roleList" :key="item.id" style="margin: 2px">{{ item.roleName }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="状态" width="80">
        <template slot-scope="scope">
          <el-switch
            active-value="1"
            inactive-value="0"
            v-model="scope.row.status + ''"
            @change="switchStatus(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间"/>

      <el-table-column label="操作" align="center" fixed="right">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="修改"/>
          <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeUserById(scope.row.id)"
                     title="删除"/>
          <el-button type="warning" icon="el-icon-baseball" size="mini" @click="showAssignRole(scope.row)"
                     title="分配角色"/>
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
      @current-change="getUserList"
    />

    <!--添加和修改用户-->
    <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%">
      <el-form ref="dataForm" :model="sysUser" label-width="100px" size="small" style="padding-right: 40px;">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="sysUser.username"/>
        </el-form-item>
        <el-form-item v-if="!sysUser.id" label="密码" prop="password">
          <el-input v-model="sysUser.password" type="password"/>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="sysUser.name"/>
        </el-form-item>
        <el-form-item label="手机" prop="phone">
          <el-input v-model="sysUser.phone"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
    </el-dialog>

    <!--分配角色-->
    <el-dialog title="分配角色" :visible.sync="dialogRoleVisible">
      <el-form label-width="80px">
        <el-form-item label="用户名">
          <el-input disabled :value="sysUser.username"></el-input>
        </el-form-item>

        <el-form-item label="角色列表">
          <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选
          </el-checkbox>
          <div style="margin: 15px 0;"></div>
          <el-checkbox-group v-model="userRoleIds" @change="handleCheckedChange">
            <el-checkbox v-for="role in allRoles" :key="role.id" :label="role.id">{{ role.roleName }}</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button type="primary" @click="assignRole" size="small">保存</el-button>
        <el-button @click="dialogRoleVisible = false" size="small">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
import api from '@/api/system/user'
import roleApi from '@/api/system/role'

const defaultForm = {
  id: '',
  username: '',
  password: '',
  name: '',
  phone: '',
  status: 1
}
export default {
  name: "User",
  data() {
    return {
      listLoading: true, // 数据是否正在加载
      list: null, // banner列表
      total: 0, // 数据库中的总记录数
      page: 1, // 默认页码
      limit: 10, // 每页记录数
      userQuery: {}, // 查询表单对象
      dialogVisible: false,
      sysUser: defaultForm,
      saveBtnDisabled: false,

      dialogRoleVisible: false,
      allRoles: [], // 所有角色列表
      userRoleIds: [], // 用户的角色ID的列表
      isIndeterminate: false, // 是否是不确定的
      checkAll: false // 是否全选
    }
  },

  // 生命周期函数：内存准备完毕，页面尚未渲染
  created() {
    this.getUserList()
  },

  // 生命周期函数：内存准备完毕，页面渲染成功
  mounted() {
    // console.log('list mounted......')
  },

  methods: {
    // 加载banner列表数据
    async getUserList(page = 1) {
      this.page = page
      await api.getUserPage(this.page, this.limit, this.userQuery).then(
        response => {
          this.list = response.pageInfo.list
          this.total = response.pageInfo.total
          // 数据加载并绑定成功
          this.listLoading = false
        }
      )
    },

    // 重置查询表单
    resetData() {
      // console.log('重置查询表单')
      this.userQuery = {}
      this.getUserList()
    },

    // 根据id删除数据
    removeUserById(id) {
      debugger
      this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用ajax
        return api.removeUser(id)
      }).then((response) => {
        this.getUserList()
        this.$message.success('删除成功')
      }).catch((res) => {
        // console.log(res)

        this.$message.info('取消删除')
      })
    },

    //弹出添加表单
    addUser() {
      this.dialogVisible = true
      this.sysUser = {}
    },

    //编辑
    edit(id) {
      this.dialogVisible = true
      api.getUserById(id).then(response => {
        this.sysUser = response.user
      })
    },

    //添加或更新
    saveOrUpdate() {
      if (!this.sysUser.id) {
        this.saveUser()
      } else {
        this.update()
      }
    },

    //添加
    saveUser() {
      api.saveUser(this.sysUser).then(response => {
        this.$message.success('操作成功')
        this.dialogVisible = false
        this.getUserList(this.page)
      })
    },

    //更新
    update() {
      api.changeUser(this.sysUser).then(response => {
        this.$message.success(response.msg || '操作成功')
        this.dialogVisible = false
        this.getUserList(this.page)
      })
    },


    //展示分配角色
    showAssignRole(row) {
      this.sysUser = row
      this.dialogRoleVisible = true
      roleApi.getRolesByUserId(row.id).then(response => {

        // console.log('response', response)

        this.allRoles = response.allRoles
        this.userRoleIds = response.userRoleIds
        this.checkAll = this.userRoleIds.length === this.allRoles.length
        this.isIndeterminate = this.userRoleIds.length > 0 && this.userRoleIds.length < this.allRoles.length
      })
    },

    // 全选勾选状态发生改变的监听
    handleCheckAllChange(value) {
      // value 当前勾选状态true/false
      // 如果当前全选, userRoleIds就是所有角色id的数组, 否则是空数组
      this.userRoleIds = value ? this.allRoles.map(item => item.id) : []
      // 如果当前不是全选也不全不选时, 指定为false
      this.isIndeterminate = false
    },

    // 角色列表选中项发生改变的监听
    handleCheckedChange(value) {
      const {userRoleIds, allRoles} = this
      this.checkAll = userRoleIds.length === allRoles.length && allRoles.length > 0
      this.isIndeterminate = userRoleIds.length > 0 && userRoleIds.length < allRoles.length
    },

    //分配角色
    assignRole() {
      // 封装数据
      let ids = '';
      for (let i = 0; i < this.userRoleIds.length; i++) {
        ids += ',' + this.userRoleIds[i]
      }
      ids = ids.substring(1)

      if (ids == '') {
        ids = 0
      }

      api.assignRoles(this.sysUser.id, ids).then(response => {
        this.$message.success(response.msg || '分配角色成功')
        this.dialogRoleVisible = false
        this.getUserList(this.page)
      })
    },

    // 修改状态
    switchStatus(row) {
      let type = row.status == 1 ? 0 : 1
      // console.log(type)
      api.changeUser({id: row.id, status: type}).then(resp => {
        this.resetData()
      })
    }
  }
}
</script>


<style scoped>

</style>
