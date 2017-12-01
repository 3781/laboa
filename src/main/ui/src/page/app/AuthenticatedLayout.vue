<template>
  <el-container style="height:100%">
    <el-aside width="auto">
      <sidebar :sidebarOpen="sidebarOpen" :menus="sidebarMenu"></sidebar>
    </el-aside>
    <el-container>
      <el-header height="50px" style="line-height:50px;border-bottom: 1px solid #e6e6e6;margin:0;padding-left:0;">
        <nav-bar :sidebarOpen="sidebarOpen" :toggleSidebar="toggleSidebar" :userMenu="userMenu"></nav-bar>
      </el-header>
      <el-main>
        <router-view></router-view>
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
    },
    computed: {
      ...mapGetters(['getUsername']),
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
          subMenus: [
            { name: '日程创建', path: '/create' },
            { name: '待办日程', path: '/todo' },
            { name: '个人日程', path: '/own' },
            { name: '协作日程', path: '/cooperation' },
          ],
        }, {
          name: '协作管理',
          iconName: 'oa-cooperation',
          path: '/cooperation',
          subMenus: [
            { name: '协作创建', path: '/create' },
            { name: '我的协作', path: '/own' },
            { name: '负责协作', path: '/manage' },
            { name: '参与协作', path: '/join' },
          ],
        }, {
          name: '文件管理',
          iconName: 'oa-file',
          path: '/file',
          subMenus: [
            { name: '文件上传', path: '/create' },
            { name: '我的文件', path: '/own' },
            { name: '所有文件', path: '/all' },
          ],
        }, {
          name: '用户管理',
          iconName: 'oa-admin',
          path: '/user',
          subMenus: [
            { name: '用户查看', path: '/all' },
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
