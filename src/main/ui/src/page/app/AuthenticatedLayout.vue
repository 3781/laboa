<template>
  <el-container style="height:100%">
    <el-aside width="180px" style="background-color: #304156; color:white;">
      sidarbar
    </el-aside>
    <el-container>
      <el-header height="50px" style="line-height:50px;border-bottom: 1px solid #e6e6e6;
      background-color: #fff;list-style:none;position: relative;margin:0;padding-left:0;">
          <div style="line-height: 58px; height: 50px;float: left; padding: 0 10px;">
            <i class="el-icon-oa-sidebar sidebar-toggle" :class="{active:sidebarOpen}" @click="toggleSidebar"></i>
          </div>
      </el-header>
      <el-main>
        <slot></slot>
      </el-main>
      <el-footer height="40px" style="line-height:40px; background-color: #304156; color:white;text-align: center">
        Copyright © 2017-2018 oh!a team. All rights reserved.
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
  export default {
    name: 'authenticatedLayout',
    data() {
      return {
        sidebarOpen: true,
      };
    },
    methods: {
      toggleSidebar() {
        this.sidebarOpen = !this.sidebarOpen;
      },
    },
  };
</script>

<style lang="stylus" scoped>
  .sidebar-toggle {
    display inline-block
    cursor pointer
    font-size 30px
    transform rotate(-90deg)
    transition .38s
    transform-origin 50% 50%
  }
  .sidebar-toggle.active {
    transform rotate(0deg)
  }
</style>
