<template>
  <unauthenticated-layout :title="header.title" :hyperlink="header.loginLink" marginTop="60px">
    <el-form :model="registerForm" ref="registerForm" label-width="80px" size="small" style="padding-right:20px">
      <el-form-item
        label="用户名" prop="username"
        :rules="[{ type:'string', required: true, message: '用户名不能为空'}]">
        <el-input v-model.trim="registerForm.username"></el-input>
      </el-form-item>
      <el-form-item label="工号">
        <el-input v-model.trim="registerForm.employeeNumber"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model.trim="registerForm.name"></el-input>
      </el-form-item>
      <el-form-item label="qq">
        <el-input v-model.trim="registerForm.qq"></el-input>
      </el-form-item>
      <el-form-item
        label="邮箱" prop="email"
        :rules="[{ type:'email', message: '邮箱格式不正确', trigger: 'blur'}]">
        <el-input v-model.trim="registerForm.email"></el-input>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model.trim="registerForm.phone"></el-input>
      </el-form-item>
      <el-form-item
        label="密码" prop="password"
        :rules="[{ validator:this.validatePassword, required:true}]">
        <el-input type="password" v-model.trim="registerForm.password"></el-input>
      </el-form-item>
      <el-form-item
        label="确认密码" prop="confirmPassword"
        :rules="[{ validator:this.validateConfirmPassword, required:true , trigger: 'blur'}]">
        <el-input type="password" v-model.trim="registerForm.confirmPassword"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="doRegister" style="width:100%">立即注册</el-button>
      </el-form-item>
    </el-form>
  </unauthenticated-layout>
</template>

<script>
  import UnauthenticatedLayout from './UnauthenticatedLayout';

  export default {
    name: 'register',
    components: {
      UnauthenticatedLayout,
    },
    data() {
      return {
        header: {
          title: '用户注册',
          loginLink: {
            name: '登录',
            tip: '已有帐号？',
            routerName: 'login',
          },
        },
        registerForm: {
          username: '',
          employeeNumber: '',
          name: '',
          qq: '',
          email: '',
          phone: '',
          password: '',
          confirmPassword: '',
        },
        loading: false,
      };
    },
    methods: {
      doRegister() {
        this.loading = true;
        this.$refs.registerForm.validate((valid) => {
          if (valid) {
            this.$store.dispatch('register', this.registerForm).then(() => {
              this.$message.success('注册成功');
              this.$router.push('/login');
            }).catch((errorMessage) => {
              this.$message.error(errorMessage);
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
          if (this.registerForm.confirmPassword !== '') {
            this.$refs.registerForm.validateField('confirmPassword');
          }
          callback();
        }
      },
      validateConfirmPassword(rule, value, callback) {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.registerForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      },
    },
  };
</script>
