import axios from 'axios';
import Vue from 'vue';
import store from '../store';

const httpUtil = axios.create({
  baseURL: '/api',
  timeout: 4000,
});

httpUtil.interceptors.response.use(response => response, (response) => {
  switch (response.data.code) {
    case 400:
      Vue.$message({
        message: '请求不存在，请联系管理员',
        type: 'error',
      });
      break;
    case 401:
      Vue.$confirm('您已登出，请重新登录', '重新登录', {
        confirmButtonText: '重新登录',
        cancelButtonText: '留在该页面',
        type: 'info',
      }).then(() => {
        store.dispatch('logout');
      });
      break;
    case 403:
      Vue.$message({
        message: '没有权限访问，请联系管理员',
        type: 'error',
      });
      break;
    case 500:
      Vue.$message({
        message: '服务器出错，请联系管理员',
        type: 'error',
      });
      break;
    default:
      Vue.$message({
        message: '未知错误，请联系管理员',
        type: 'error',
      });
      break;
  }
});

export default httpUtil;
