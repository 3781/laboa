<template>
  <div class="e404">
    <div class = "e_404">
      4 <i class="el-icon-loading"></i> 4
      <br />
    </div>

    <div class = "e_content">
        对不起，您访问的页面可能搬家了...
    </div>
  </div>

</template>

<script>

</script>


<style lang="stylus">
    .e_404
      background-color #fff
      position relative
      text-align center
      font-size 800%
      font-family "Bitstream Charter"
      vertical-align middle
      font-weight bolder
      color: #8D8

    .e_content
      background-color #fff
      position relative
      text-align center
      font-size 400%
      font-family "Bitstream Charter"
      vertical-align middle
      color: #AAA

</style>

<!--<template>-->
<!--<div id="example">-->
  <!--<p>Original message: "{{ message }}"</p>-->
  <!--<p>Computed reversed message: "{{ reversedMessage }}"</p>-->
<!--</div>-->
<!--</template>-->


<!--<script>-->
<!--var vm = new Vue({-->
  <!--el: '#example',-->
  <!--data: {-->
  <!--message: 'Hello'-->
  <!--},-->
  <!--computed: {-->
    <!--// 计算属性的 getter-->
    <!--reversedMessage: function () {-->
      <!--// `this` 指向 vm 实例-->
    <!--return this.message.split('').reverse().join('')-->
    <!--}-->
  <!--}-->
<!--})-->
<!--</script>-->



<!--<template>-->
  <!--<div id="app">-->
    <!--<div v-bind:id="dynamicId">{{ e_404 }}</div>-->
    <!--4 <i class="el-icon-loading"></i> 4-->
    <!--<br />-->
    <!--<span>{{ e_content }}</span>-->
  <!--</div>-->
<!--</template>-->

<!--<script>-->
  <!--export default {-->
    <!--name: 'login',-->
    <!--data() {-->
      <!--return {-->
        <!--e_404: '4 <i class="el-icon-loading"></i> 4',-->
        <!--e_content: '对不起，您访问的页面可能搬家了...',-->
      <!--};-->
    <!--},-->
  <!--};-->

<!--</script>-->

<!--<style>-->

<!--</style>-->
