const TokenKey = 'token'

export function getToken() {
  return localStorage.getItem(TokenKey)
}

export function setToken(token) {
  return localStorage.setItem('token', token)
}

export function removeToken() {
  return localStorage.removeItem(TokenKey)
}
