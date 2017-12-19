<template>
  <el-row>
    <el-form :model="cooperationForm" ref="cooperationForm" :statusIcon="true" label-width="120px" size="small">
      <el-form-item v-if="parent != null" label="所属协作">
        <span v-text="parent.name">  </span>
      </el-form-item>
      <el-form-item label="协作名" prop="name"
                    :rules="[{ type:'string', required: true, message: '协作名不能为空'}]">
        <el-input v-model.trim="cooperationForm.name" placeholder="请输入协作名" style="width:220px"></el-input>
      </el-form-item>
      <el-form-item label="时间" >
        <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="cooperationForm.beginDate"></el-date-picker>
        至
        <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="cooperationForm.endDate"></el-date-picker>
      </el-form-item>
      <el-form-item label="申请链接开启" prop="invite">
        <el-switch
          v-model="cooperationForm.invite"
          active-color="#13ce66"
          inactive-color="#ff4949">
        </el-switch>
      </el-form-item>
      <el-form-item label="协作介绍" prop="remark">
        <mavon-editor v-model="cooperationForm.remark" style="min-height:290px"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="default" @click="doCreate" :loading="loading">提交</el-button>
      </el-form-item>
    </el-form>
  </el-row>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'cooperationCreate',
    props: {
      parent: {
        type: Object,
        default: null,
      },
    },
    data() {
      return {
        loading: false,
        cooperationForm: {
          parentId: null,
          name: null,
          beginDate: null,
          endDate: null,
          remark: '',
          invite: false,
        },
      };
    },
    methods: {
      ...mapActions(['saveCooperation']),
      doCreate() {
        if (this.parent != null) {
          this.cooperationForm.parentId = this.parent.cooperationId;
        }

        this.loading = true;
        this.$refs.cooperationForm.validate((valid) => {
          if (valid) {
            this.saveCooperation(this.cooperationForm).then((cooperationId) => {
              this.$notify({
                message: `协作${this.cooperationForm.name}创建成功`,
                type: 'info',
                position: 'bottom-right',
                offset: 40,
              });

              this.loading = false;
              this.cooperationForm.beginDate = null;
              this.cooperationForm.endDate = null;
              this.$refs.cooperationForm.resetFields();
              this.$emit('createSuccess', cooperationId);
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
