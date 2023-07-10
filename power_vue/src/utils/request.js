import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'

// 创建 Axios 实例
const service = axios.create({
  baseURL: 'api/', // url = base url + request url
  timeout: 5000 // 请求超时
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 在发送请求之前执行某些操作
    if (config.url.substr(-6) != '/login') {
      config.headers.token = localStorage.getItem('token')
    }
    return config
  },
  error => {
    // 对请求错误执行某些操作
    console.log(error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  /**
   * 如果要获取标头或状态等 http 信息
   * 请返回响应 =>响应
   */

  /**
   * 通过自定义代码确定请求状态
   */
  response => {
    const res = response.data
    // 请求成功.
    if (res.code !== 200) {
      Message({
        message: res.msg || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008: 非法token判断;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.alert('您的身份已过期，请重新登录', '重新登录', {
          confirmButtonText: '确定'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  }
)

export default service
