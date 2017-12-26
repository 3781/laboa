<template>
  <el-container class="main-container" style="min-height:100%" v-loading.fullscreen.lock="getMainLoading">
    <el-main>
      <el-card class="cardbox" :style="{marginTop:marginTop}">
        <div v-if="this.title" slot="header">
          <span>{{ this.title }}</span>
          <small v-if="this.hyperlink" style="float: right">
            <span>{{ this.hyperlink.tip }}</span>
            <router-link :to="this.hyperlink.path">{{ this.hyperlink.name }}</router-link>
          </small>
        </div>
        <slot></slot>
      </el-card>
    </el-main>
    <el-footer height="40px" style="line-height:40px; background-color: #304156; color:white;text-align: center">
      <web-footer></web-footer>
    </el-footer>
  </el-container>
</template>

<script>
  import { mapGetters } from 'vuex';
  import WebFooter from './WebFooter';

  export default {
    components: {
      WebFooter,
    },
    name: 'unAuthenticatedLayout',
    props: {
      title: {
        type: String,
      },
      marginTop: {
        type: String,
        default: '60px',
      },
      hyperlink: {
        type: Object,
      },
    },
    computed: {
      ...mapGetters(['getMainLoading']),
    },
  };
</script>

<style lang="stylus" scoped>
  .main-container
    background-color #2d3a4b
    height 100vh
    .cardbox
      position absolute
      left 0
      right 0
      width 400px
      margin auto
</style>
