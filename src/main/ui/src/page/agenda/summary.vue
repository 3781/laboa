<template>
  <el-form :model="summaryForm" ref="summaryForm" :statusIcon="true" label-width="120px" size="small" v-loading.lock="loading">
    <el-form-item label="状态" prop="status"
                  :rules="[{ type:'string', required: true, message: '状态不能为空'}]">
      <el-radio-group v-model="summaryForm.status">
        <el-radio label="todo">待处理</el-radio>
        <el-radio label="finished">完成</el-radio>
        <el-radio label="unfinished">未完成</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="总结" prop="content">
      <mavon-editor v-model="summaryForm.content"></mavon-editor>
    </el-form-item>
  </el-form>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'agendaSummary',
    props: {
      summary: {
        type: Object,
        required: true,
      },
    },
    created() {
      this.initForm();
    },
    data() {
      return {
        loading: false,
        summaryForm: {
          summaryId: null,
          content: '',
          status: null,
        },
      };
    },
    watch: {
      summary() {
        this.initForm();
      },
    },
    methods: {
      ...mapActions(['summaryAgenda']),
      initForm() {
        this.summaryForm.summaryId = this.summary.summaryId;
        this.summaryForm.content = this.summary.content || '';
        this.summaryForm.status = this.summary.status;
      },
      doSummary() {
        this.loading = true;
        this.summaryAgenda(this.summaryForm).then(() => {
          this.$notify({
            message: '结项成功',
            type: 'success',
            position: 'bottom-right',
            offset: 40,
          });
          this.$emit('onSuccess', this.summaryForm);
          this.loading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
    },
  };
</script>

<style scoped>

</style>
