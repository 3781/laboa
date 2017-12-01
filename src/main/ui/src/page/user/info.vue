<template>
  <el-form :model="infoForm" ref="infoForm" label-width="80px">
    <el-form-item label="工号">
      <el-input v-model.trim="infoForm.employeeNumber"></el-input>
    </el-form-item>
    <el-form-item label="姓名">
      <el-input v-model.trim="infoForm.name"></el-input>
    </el-form-item>
    <el-form-item label="qq">
      <el-input v-model.trim="infoForm.qq"></el-input>
    </el-form-item>
    <el-form-item
      label="邮箱" prop="email"
      :rules="[{ type:'email', message: '邮箱格式不正确', trigger: 'blur'}]">
      <el-input v-model.trim="infoForm.email"></el-input>
    </el-form-item>
    <el-form-item label="联系方式">
      <el-input v-model.trim="infoForm.phone"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="doUpdateInfo" style="width:100%">提交修改</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import { Message } from 'element-ui';
  import state from '../../store/index';

  export default {
    name: 'info',
    beforeRouteEnter(to, from, next) {
      state.dispatch('getInfo').then((info) => {
        next((vm) => {
          const infoForm = vm.infoForm;
          infoForm.employeeNumber = info.employeeNumber;
          infoForm.name = info.name;
          infoForm.qq = info.qq;
          infoForm.email = info.email;
          infoForm.phone = info.phone;
        });
      }).catch((errorMessage) => {
        Message.error(errorMessage);
      });
    },
    data() {
      return {
        infoForm: {
          employeeNumber: '',
          name: '',
          qq: '',
          email: '',
          phone: '',
        },
        loading: false,
      };
    },
    methods: {
      doUpdateInfo() {
        this.loading = true;
        this.$refs.infoForm.validate((valid) => {
          if (valid) {
            this.$store.dispatch('updateInfo', this.infoForm).then(() => {
              this.$notify({
                message: '更新成功',
                type: 'success',
                position: 'bottom-right',
                offset: 40,
              });
            }).catch((errorMessage) => {
              this.$message.error(errorMessage);
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
