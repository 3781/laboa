<template>
  <div class="login-container">
    <el-form class="card-box login-form" autoComplete="on" :model="loginForm" ref="loginForm" label-position="left">
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <span class="svg-container svg-container_login">
        </span>
        <el-input name="username" type="text" v-model="loginForm.username" autoComplete="on" placeholder="用户名" />
      </el-form-item>
      <el-form-item prop="password">
        <span class="svg-container">
        </span>
        <el-input name="password" :type="pwdType" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on"
                  placeholder="密码" />
        <span class='show-pwd' @click='showPwd'></span>
      </el-form-item>
      <el-button type="primary" style="width:100%;margin-bottom:30px;" :loading="loading" @click.native.prevent="handleLogin">登录</el-button>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: 'login',
    data() {
      return {
        loginForm: {
          username: '',
          password: '',
          successCallback: this.successCallback,
          failCallback: this.failCallback,
        },
        pwdType: 'password',
        loading: false,
      };
    },
    methods: {
      showPwd() {
        if (this.pwdType === 'password') {
          this.pwdType = '';
        } else {
          this.pwdType = 'password';
        }
      },
      handleLogin() {
        this.loading = true;
        this.$store.dispatch('login', this.loginForm);
      },
      successCallback(userInfo) {
        this.$message(`用户${userInfo.username}最后于${userInfo.lastLoginTime}登录`);
        this.loading = false;
      },
      failCallback(errorMessage) {
        this.$message(errorMessage);
        this.loading = false;
      },
    },
  };
</script>
