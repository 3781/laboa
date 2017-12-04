<template>
  <el-container style="min-height:100%" >
    <el-aside width="auto" >
      <sidebar :sidebarOpen="sidebarOpen" :menus="sidebarMenu"></sidebar>
    </el-aside>
    <el-container>
      <el-header height="50px" style="line-height:50px;border-bottom: 1px solid #e6e6e6;margin:0;padding-left:0;">
        <nav-bar :sidebarOpen="sidebarOpen" :toggleSidebar="toggleSidebar" :userMenu="userMenu"></nav-bar>
      </el-header>
      <el-main>
        <router-view v-loading.lock="getMainLoading"></router-view>
      </el-main>
      <el-footer height="40px" style="line-height:40px; background-color: #304156; color:white;text-align: center">
        Copyright © 2017-2018 oh!a team. All rights reserved.
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
  import { mapGetters } from 'vuex';
  import NavBar from './NavBar';
  import Sidebar from './Sidebar';

  export default {
    name: 'authenticatedLayout',
    data() {
      return {
        sidebarOpen: true,
      };
    },
    components: {
      NavBar,
      Sidebar,
    },
    methods: {
      toggleSidebar() {
        this.sidebarOpen = !this.sidebarOpen;
      },
      isShow(requiredRole) {
        const getRoleLevel = (role) => {
          let roleLevel;
          switch (role) {
            case 'enduser':
              roleLevel = 1;
              break;
            case 'admin':
              roleLevel = 2;
              break;
            case 'superAdmin':
              roleLevel = 3;
              break;
            default:
          }
          return roleLevel;
        };
        return getRoleLevel(this.getRole) >= getRoleLevel(requiredRole);
      },
    },
    computed: {
      ...mapGetters(['getUsername', 'getMainLoading', 'getRole']),
      userMenu() {
        return {
          username: this.getUsername,
          subMenus: [{
            label: '个人信息',
            router: '/user/info',
          }, {
            label: '修改密码',
            router: '/user/password',
          }, {
            label: '退出登录',
            router: '/logout',
          }],
        };
      },
      sidebarMenu() {
        return [{
          name: '日程管理',
          iconName: 'oa-agenda',
          path: '/agenda',
          show: this.isShow('enduser'),
          subMenus: [
            { name: '日程创建', path: '/create', show: this.isShow('enduser') },
            { name: '待办日程', path: '/todo', show: this.isShow('enduser') },
            { name: '个人日程', path: '/own', show: this.isShow('enduser') },
            { name: '协作日程', path: '/cooperation', show: this.isShow('enduser') },
          ],
        }, {
          name: '协作管理',
          iconName: 'oa-cooperation',
          path: '/cooperation',
          show: this.isShow('enduser'),
          subMenus: [
            { name: '协作创建', path: '/create', show: this.isShow('enduser') },
            { name: '我的协作', path: '/own', show: this.isShow('enduser') },
            { name: '负责协作', path: '/manage', show: this.isShow('enduser') },
            { name: '参与协作', path: '/join', show: this.isShow('enduser') },
          ],
        }, {
          name: '文件管理',
          iconName: 'oa-file',
          path: '/file',
          show: this.isShow('enduser'),
          subMenus: [
            { name: '文件上传', path: '/create', show: this.isShow('enduser') },
            { name: '我的文件', path: '/own', show: this.isShow('enduser') },
            { name: '所有文件', path: '/all', show: this.isShow('admin') },
          ],
        }, {
          name: '用户管理',
          iconName: 'oa-admin',
          path: '/user',
          show: this.isShow('admin'),
          subMenus: [
            { name: '用户查看', path: '/all', show: this.isShow('admin') },
          ],
        }];
      },
    },
  };
</script>

<style lang="stylus" scoped>
  .el-aside
  .el-main
    overflow visible
</style>
