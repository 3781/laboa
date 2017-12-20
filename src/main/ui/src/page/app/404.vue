<!--&lt;!&ndash;<template>&ndash;&gt;-->
  <!--&lt;!&ndash;<div calss = "error">&ndash;&gt;-->
    <!--&lt;!&ndash;404&ndash;&gt;-->
  <!--&lt;!&ndash;</div>&ndash;&gt;-->
<!--&lt;!&ndash;</template>&ndash;&gt;-->

<!--&lt;!&ndash;<script>&ndash;&gt;-->


<!--&lt;!&ndash;</script>&ndash;&gt;-->

<!--&lt;!&ndash;<style lang="stylus" scoped>&ndash;&gt;-->
  <!--&lt;!&ndash;&ndash;&gt;-->
<!--&lt;!&ndash;</style>&ndash;&gt;-->


<!--&lt;!&ndash; 展示模板 &ndash;&gt;-->
<!--<template>-->
    <!--{{ message }}-->
<!--</template>-->

<!--<script>-->

<!--//  export defualt-->
<!--//  {-->
<!--//    data: function()-->
<!--//    {-->
<!--//      return-->
<!--//      {-->
<!--//        title: '我是頭部'-->
<!--//      }-->
<!--//    }-->
<!--//  }-->



  <!--new Vue({-->
    <!--el: '#app',-->
    <!--data: {-->
      <!--message: '<h1>菜鸟教程</h1>'-->
    <!--}-->
  <!--})-->

<!--</script>-->

<!--<style>-->
  <!--.header {-->
    <!--color: red;-->
  <!--}-->
<!--</style>-->
<template>
  <div class="error_404">
    <div class = "e404">
      4 <i class="el-icon-loading"></i> 4
      <br />

      <div class = "e_content">
        对不起，您访问的页面可能搬家了...
      </div>
    </div>


  </div>

</template>



<script>

</script>



<style lang="stylus">
    .e404
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
      font-size 60%
      font-family "Bitstream Charter"
      vertical-align middle
      color: #AAA
      /*font-weight bolder*/

</style>
