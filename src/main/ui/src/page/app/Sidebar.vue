<template>
  <el-menu class="sidebar" style="min-height: 100%" mode="vertical" :unique-opened="true" :default-active="$route.path" :collapse="!sidebarOpen" background-color="#304156" text-color="#fff" active-text-color="#409EFF">
    <el-submenu v-for="menu in menus" :index="menu.path" :key="menu.path">
      <template slot="title">
        <i :class="`el-icon-${menu.iconName}`"></i>
        <span v-text="menu.name"></span>
      </template>
      <el-menu-item v-for="menuItem in menu.subMenus" :index="`${menu.path}${menuItem.path}`" :key="`${menu.path}${menuItem.path}`">
        <router-link :to="`${menu.path}${menuItem.path}`" tag="div"> {{ menuItem.name }} </router-link>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
  export default {
    name: 'sidebar',
    props: {
      sidebarOpen: {
        type: Boolean,
        required: true,
      },
      menus: {
        type: Array,
        default: [],
      },
    },
  };
</script>

<style lang="stylus">
  .sidebar
    &:not(.el-menu--collapse)
      width 200px
</style>
