<template>
  <div>
    Copyright © 2017-2018 Oh!A team. All rights reserved.
    <span style="margin-left:10px;cursor: pointer" @click="openICP">闽ICP备17033965号</span>
  </div>
</template>

<script>
  export default {
    name: 'webFooter',
    methods: {
      openICP() {
        window.open('http://www.miitbeian.gov.cn/');
      },
    },
  };
</script>
