<template>
  <div class="nav-bar">
    <div class="sidebar-container" v-if="sidebarOpen != null">
      <i class="el-icon-oa-sidebar sidebar-toggle" :class="{active:sidebarOpen}" @click="toggleSidebar"></i>
    </div>
    <el-dropdown class="user" v-if="userMenu">
      <span class="el-dropdown-link">
        {{ userMenu.username }}
        <i class="el-icon-arrow-down el-icon--right"></i>
      </span>
      <el-dropdown-menu slot="dropdown" v-if="this.userMenu.subMenus">
        <el-dropdown-item v-for="subMenu in this.userMenu.subMenus" :key="subMenu.label">
          <router-link :to="subMenu.router" tag="div">{{ subMenu.label }}</router-link>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
  export default {
    name: 'navBar',
    props: {
      sidebarOpen: {
        type: Boolean,
        default: null,
      },
      toggleSidebar: {
        type: Function,
        default: null,
      },
      userMenu: {
        default: null,
      },
    },
  };
</script>

<style lang="stylus">
  .nav-bar
    background-color #fff
    list-style none
    position relative
    .sidebar-container
      line-height 58px
      height 50px
      float left
      padding 0 10px
      .sidebar-toggle
        display inline-block
        cursor pointer
        font-size 30px
        transform rotate(-90deg)
        transition .38s
        transform-origin 50% 50%
        &.active
          transform rotate(0deg)
    .user
      cursor pointer
      height 50px
      position absolute
      right 35px
</style>
