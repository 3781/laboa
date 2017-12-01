<template>
  <el-row type="flex" justify="center" :gutter="10">
    <el-col :xs="22" :sm="16" :md="10" :lg="8" :xl="6">
      <el-form :model="infoForm" ref="infoForm" label-width="80px">
        <el-form-item label="工号" prop="employeeNumber">
          <el-input  v-model.trim="infoForm.employeeNumber"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model.trim="infoForm.name"></el-input>
        </el-form-item>
        <el-form-item label="qq" prop="qq">
          <el-input v-model.trim="infoForm.qq"></el-input>
        </el-form-item>
        <el-form-item
          label="邮箱" prop="email"
          :rules="[{ type:'email', message: '邮箱格式不正确', trigger: 'blur'}]">
          <el-input v-model.trim="infoForm.email"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="phone">
          <el-input v-model.trim="infoForm.phone"></el-input>
        </el-form-item>
        <el-form-item>
          <el-row type="flex" justify="space-between" :gutter="40">
            <el-col :span="12">
              <el-button type="warning" @click="doReset" style="width:100%">信息重置</el-button>
            </el-col>
            <el-col :span="12">
              <el-button type="primary" @click="doUpdateInfo" style="width:100%" :loading="loading">提交修改</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
  import { Message } from 'element-ui';
  import { mapActions } from 'vuex';
  import state from '../../store/index';

  export default {
    name: 'info',
    data() {
      return {
        infoForm: this.$route.meta.infoForm,
        loading: false,
      };
    },
    beforeRouteEnter(to, from, next) {
      state.dispatch('getInfo').then((info) => {
        const meta = to.meta;
        meta.infoForm = info;
        next();
      }).catch((errorMessage) => {
        Message.error(errorMessage);
      });
    },
    methods: {
      ...mapActions(['updateInfo']),
      doReset() {
        this.$refs.infoForm.resetFields();
      },
      doUpdateInfo() {
        this.loading = true;

        this.$refs.infoForm.validate((valid) => {
          if (valid) {
            this.updateInfo(this.infoForm).then(() => {
              this.$notify({
                message: '更新成功',
                type: 'success',
                position: 'bottom-right',
                offset: 40,
              });
              this.loading = false;
            }).catch((errorMessage) => {
              this.$message.error(errorMessage);
              this.loading = false;
            });
          } else {
            this.loading = false;
          }
        });
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
