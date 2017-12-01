<template>
  <el-row type="flex" justify="center" :gutter="10">
    <el-col :xs="22" :sm="16" :md="10" :lg="8" :xl="6">
      <el-form :model="passwordForm" ref="passwordForm" label-width="80px">
        <el-form-item label="旧密码" prop="oldPassword"
                      :rules="[{type: 'string', required:true, message: '旧密码不能为空'}]">
          <el-input type="password"  v-model.trim="passwordForm.oldPassword"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword"
                      :rules="[{ validator:this.validatePassword, required:true}]">
          <el-input type="password" v-model.trim="passwordForm.newPassword"></el-input>
        </el-form-item>
        <el-form-item label="密码确认" prop="confirmPassword"
                      :rules="[{ validator:this.validateConfirmPassword, required:true , trigger: 'blur'}]">
          <el-input type="password" v-model.trim="passwordForm.confirmPassword"></el-input>
        </el-form-item>
        <el-form-item>
          <el-row type="flex" justify="space-between" :gutter="40">
            <el-col :span="12">
              <el-button type="warning" @click="doReset" style="width:100%">重置</el-button>
            </el-col>
            <el-col :span="12">
              <el-button type="primary" @click="doChangePassword" style="width:100%" :loading="loading">提交</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </el-col>
  </el-row>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'info',
    data() {
      return {
        show: true,
        passwordForm: {
          oldPassword: '',
          newPassword: '',
          confirmPassword: '',
        },
        loading: false,
      };
    },
    methods: {
      ...mapActions(['changePassword']),
      doReset() {
        this.$refs.passwordForm.resetFields();
      },
      doChangePassword() {
        this.loading = true;
        this.$refs.passwordForm.validate((valid) => {
          if (valid) {
            this.changePassword(this.passwordForm).then(() => {
              this.$notify({
                message: '修改成功',
                type: 'success',
                position: 'bottom-right',
                offset: 40,
              });
              this.doReset();
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
      validatePassword(rule, value, callback) {
        if (value === '') {
          callback(new Error('密码不能为空'));
        } else {
          if (this.passwordForm.confirmPassword !== '') {
            this.$refs.passwordForm.validateField('confirmPassword');
          }
          callback();
        }
      },
      validateConfirmPassword(rule, value, callback) {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.passwordForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
