<template>
  <div class="main-containner">
    <el-card class="cardbox" :style="{marginTop:marginTop}">
      <div v-if="this.title" slot="header">
        <span>{{ this.title }}</span>
        <small v-if="this.hyperlink" style="float: right">
          <span>{{ this.hyperlink.tip }}</span>
          <router-link :to="{name: this.hyperlink.routerName}">{{ this.hyperlink.name }}</router-link>
        </small>
      </div>
      <slot></slot>
    </el-card>
  </div>
</template>

<script>
  export default {
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
  };
</script>

<style lang="stylus" scoped>
  .main-containner
    background-color #2d3a4b
    height 100%
    .cardbox
      position absolute
      left 0
      right 0
      width 400px
      margin auto
</style>
