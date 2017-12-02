<template>
  <div class="nav-bar">
    <div class="sidebar-container" v-if="sidebarOpen != null">
      <i class="el-icon-oa-sidebar sidebar-toggle" :class="{active:sidebarOpen}" @click="toggleSidebar"></i>
    </div>
    <div class="user">
      <el-dropdown v-if="userMenu">
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
      <el-button icon="el-icon-refresh" style="padding: 0" type="text" title="刷新登录信息"
                 @click="refreshLoginInfo" :loading="refreshing"></el-button>
    </div>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'navBar',
    data() {
      return {
        refreshing: false,
      };
    },
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
    methods: {
      ...mapActions(['getLoginInfo']),
      refreshLoginInfo() {
        this.refreshing = true;
        this.getLoginInfo().then((lastLoginTime) => {
          this.$notify({
            message: `刷新成功,,登陆于${lastLoginTime}`,
            type: 'info',
            position: 'bottom-right',
            offset: 40,
          });
          this.refreshing = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.refreshing = false;
        });
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
