import request from "@/utils/request";

const apiName = 'admin/sys/role'
export default {
  // 条件分页查询
  getRolePage(page, limit, roleQuery) {
    return request({
      url: `${apiName}/page/${page}/${limit}`,
      method: 'GET',
      params: roleQuery
    })
  },

  // 删除角色
  removeRole(ids) {
    return request({
      url: `${apiName}/remove/${ids}`,
      method: 'DELETE'
    })
  },

  // 添加角色
  saveRole(role) {
    return request({
      url: `${apiName}/saveRole`,
      method: 'post',
      data: role
    })
  },

  // 添加角色
  getRoleById(id) {
    return request({
      url: `${apiName}/getRoleById/${id}`,
      method: 'get'
    })
  },

  // 修改角色
  changeRole(role) {
    return request({
      url: `${apiName}/changeRole`,
      method: 'put',
      data: role
    })
  },

  //根据用户id查询用户已分配的角色
  getRolesByUserId(userId) {
    return request({
      url: `${apiName}/getRoleByUserId/${userId}`,
      method: 'get'
    })
  },


  // 给某个角色授权
  doAssign(roleId, menuIds) {
    return request({
      url: `${apiName}/doAssign/${roleId}/${menuIds}`,
      method: "post"
    })
  }

}
