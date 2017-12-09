<template>
  <div>
    {{ this.cooperationId }}
  </div>
</template>

<script>
    export default {
      name: 'cooperationDetail',
      data() {
        return {
          cooperationId: this.$route.params.cooperationId,
        };
      },
    };
</script>

<style lang="stylus" scoped>

</style>
