import request from "@/utils/request";

const apiName = 'admin/sys/menu'
export default {

  // 查询菜单
  getMenuList() {
    return request({
      url: `${apiName}/getMenuList`,
      method: 'GET'
    })
  },

  // 删除菜单
  removeMenuByIds(ids) {
    return request({
      url: `${apiName}/remove/${ids}`,
      method: 'DELETE'
    })
  },

  // 添加菜单
  saveMenu(menu) {
    return request({
      url: `${apiName}/saveMenu`,
      method: 'post',
      data: menu
    })
  },

  // 修改菜单
  changeMenu(menu) {
    return request({
      url: `${apiName}/changeMenu`,
      method: 'put',
      data: menu
    })
  },

  // 查看某个角色的权限列表
  getAssign(roleId) {
    return request({
      url: `${apiName}/getAssign/${roleId}`,
      method: 'get'
    })
  },


}
