import axios from 'axios';
import { Message, MessageBox } from 'element-ui';
import store from '../store';

const httpUtil = axios.create({
  baseURL: '/api',
  timeout: 4000,
});

httpUtil.interceptors.response.use(response => response, (response) => {
  switch (response.status) {
    case 400:
      Message({
        message: '请求不存在，请联系管理员',
        type: 'error',
      });
      break;
    case 401:
      MessageBox.confirm('您已登出，请重新登录', '重新登录', {
        confirmButtonText: '重新登录',
        cancelButtonText: '留在该页面',
        type: 'info',
      }).then(() => {
        store.dispatch('logoutLocal');
      });
      break;
    case 403:
      Message({
        message: '没有权限访问，请联系管理员',
        type: 'error',
      });
      break;
    case 500:
      Message({
        message: '服务器出错，请联系管理员',
        type: 'error',
      });
      break;
    default:
      Message({
        message: '未知错误，请联系管理员',
        type: 'error',
      });
      break;
  }
});

export default httpUtil;
