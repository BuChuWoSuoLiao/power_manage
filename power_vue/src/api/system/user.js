import request from "@/utils/request";

const apiName = 'admin/sys/user'
export default {
  // 条件分页查询
  getUserPage(page, limit, userQuery) {
    return request({
      url: `${apiName}/page/${page}/${limit}`,
      method: 'GET',
      params: userQuery
    })
  },

  // 删除角色
  removeUser(ids) {
    return request({
      url: `${apiName}/remove/${ids}`,
      method: 'DELETE'
    })
  },

  // 添加角色
  saveUser(user) {
    return request({
      url: `${apiName}/saveUser`,
      method: 'post',
      data: user
    })
  },

  // 根据id获取用户
  getUserById(id) {
    return request({
      url: `${apiName}/getUserById/${id}`,
      method: 'get'
    })
  },

  // 修改用户
  changeUser(user) {
    return request({
      url: `${apiName}/changeUser`,
      method: 'put',
      data: user
    })
  },

  //分配角色
  assignRoles(userId, roleIds) {
    return request({
      url: `${apiName}/saveRoleByUserId/${userId}/${roleIds}`,
      method: 'post'
    })
  }
}
