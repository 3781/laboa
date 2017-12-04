<template>
  <unauthenticated-layout :title="header.title" :hyperlink="header.registerLink" marginTop="140px">
    <el-form :model="loginForm" ref="loginForm" :statusIcon="true" label-width="80px" size="small" style="padding-right:20px">
      <el-form-item
        label="用户名" prop="username"
        :rules="[{ type:'string', required: true, message: '用户名不能为空'}]">
        <el-input v-model.trim="loginForm.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item
        label="密码" prop="password"
        :rules="[{ type:'string', required: true, message: '密码不能为空'}]">
        <el-input type="password" v-model.trim="loginForm.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doLogin()" style="width:100%" :loading="loading">立即登录</el-button>
      </el-form-item>
    </el-form>
  </unauthenticated-layout>
</template>

<script>
  import { mapActions } from 'vuex';
  import UnauthenticatedLayout from './UnauthenticatedLayout';

  export default {
    name: 'login',
    components: {
      UnauthenticatedLayout,
    },
    data() {
      return {
        header: {
          title: '用户登录',
          registerLink: {
            name: '注册',
            tip: '没有帐号？',
            path: '/register',
          },
        },
        loginForm: {
          username: 'username',
          password: 'password',
        },
        loading: false,
      };
    },
    methods: {
      ...mapActions(['login']),
      doLogin() {
        this.loading = true;
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.login(this.loginForm).then((lastLoginTime) => {
              this.$router.push('/');
              if (lastLoginTime) {
                this.$notify({
                  message: `上次登陆于${lastLoginTime}`,
                  type: 'info',
                  position: 'bottom-right',
                  offset: 40,
                });
              }
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
