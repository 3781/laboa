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
        <el-button type="primary" @click="doLogin()" style="width:100%">立即登录</el-button>
      </el-form-item>
    </el-form>
  </unauthenticated-layout>
</template>

<script>
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
            routerName: 'register',
          },
        },
        loginForm: {
          username: 'wumoetwrccv',
          password: 'password',
        },
        loading: false,
      };
    },
    methods: {
      doLogin() {
        this.loading = true;
        this.$refs.loginForm.validate((valid) => {
          if (valid) {
            this.$store.dispatch('login', this.loginForm).then(() => {
              this.$router.push('/');
            }).catch((errorMessage) => {
              console.debug(errorMessage);
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
