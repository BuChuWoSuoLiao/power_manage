import { logout, getInfo } from '@/api/system/account'
import { getToken, setToken, removeToken } from '@/utils/auth'
import router, { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    buttons: [],
    menus: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },
  // 新增
  SET_BUTTONS: (state, buttons) => {
    state.buttons = buttons
  },
  // 新增
  SET_MENUS: (state, menus) => {
    state.menus = menus
  }

}

const actions = {
  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // 删除token
        resetRouter() // 重置路由
        router.push('/login')
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取用户信息
  getInfo({ commit }) {
    return new Promise((resolve, reject) => {
      getInfo(localStorage.getItem('token')).then(response => {
        const { data } = response
        commit('SET_NAME', response.userInfo.name)
        commit('SET_AVATAR', response.userInfo.headUrl)
        commit('SET_BUTTONS', response.permsList)
        commit('SET_MENUS', response.routerVoList)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

