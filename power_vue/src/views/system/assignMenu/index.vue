<template>
  <div class="app-container">
    <div style="padding: 20px 20px 0 20px;">
      授权角色：{{ $route.query.roleName }}
    </div>
    <el-tree
      style="margin: 20px 0"
      ref="tree"
      :data="sysMenuList"
      node-key="id"
      show-checkbox
      default-expand-all
      :props="defaultProps"
    />
    <div style="padding: 20px 20px;">
      <el-button :loading="loading" type="primary" icon="el-icon-check" size="mini" @click="save">保存</el-button>
      <el-button @click="$router.push('/system/Role')" size="mini" icon="el-icon-refresh-right">返回</el-button>
    </div>
  </div>
</template>

<script>
import menuApi from '@/api/system/menu'
import roleApi from '@/api/system/role'

export default {
  name: "AssignMenu",
  data() {
    return {
      loading: false, // 用来标识是否正在保存请求中的标识, 防止重复提交
      sysMenuList: [], // 所有
      defaultProps: {
        children: 'children',
        label: 'name'
      },
    };
  },
  created() {
    this.fetchData()
  },

  methods: {
    /*
    初始化
    */
    async fetchData() {
      const roleId = this.$route.query.id
      await menuApi.getAssign(roleId).then(result => {

        this.sysMenuList = result.menuAllList
        this.$refs.tree.setCheckedKeys(result.roleMenuList)

        console.log(result)

      })
    },


    /*
    保存权限列表
    */
    save() {
      // // debugger
      //获取到当前子节点
      //获取到当前子节点及父节点
      const allCheckedNodes = this.$refs.tree.getCheckedNodes(false, false);
      let idList = allCheckedNodes.map(node => node.id);

      let ids = ''
      for (let i = 0; i < idList.length; i++) {
        ids += ',' + idList[i]
      }

      ids = ids.substring(1)
      if (ids == '') {
        ids = '0'
      }
      console.log(ids)

      this.loading = true
      roleApi.doAssign(this.$route.query.id, ids).then(result => {
        this.loading = false
        this.$message.success(result.msg || '分配权限成功')
        this.$router.push('/system/Role');
      })

      // //获取到当前子节点
      // //const checkedNodes = this.$refs.tree.getCheckedNodes()
      // //获取到当前子节点及父节点
      // const allCheckedNodes = this.$refs.tree.getCheckedNodes(false, false);
      // let idList = allCheckedNodes.map(node => node.id);
      // console.log(idList)
      // let assginMenuVo = {
      //   roleId: this.$route.query.id,
      //   menuIdList: idList
      // }
      // this.loading = true
      //
      // console.log(assginMenuVo)

    }
  }
}
</script>

<style scoped>

</style>
