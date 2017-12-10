<template>
    <div class="main-container" v-loading.lock="loading" element-loading-text="正在申请中" style="height:100%">
    </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'toApply',
    data() {
      return {
        loading: false,
        cooperationId: null,
      };
    },
    created() {
      this.loading = true;
      this.doApply(this.$route.params.cooperationId).then((tips) => {
        this.loading = false;
        this.$confirm(tips, '提示', {
          confirmButtonText: '立即前往',
          cancelButtonText: '留在本页面',
          type: 'info',
        }).then(() => {
          this.$router.push(`/cooperation/${this.$route.params.cooperationId}`);
        });
      }).catch((errorMessage) => {
        this.$message.info(errorMessage);
        this.loading = false;
      });
    },
    methods: {
      ...mapActions(['doApply']),
    },
  };
</script>

<style scoped>

</style>
