<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app',
};
</script>

<style lang="stylus">
  html,body
    margin 0
    padding 0
    height 100vh
    #app
      height 100%
</style>
