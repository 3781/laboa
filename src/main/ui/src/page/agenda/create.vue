<template>
  <el-form :model="agendaForm" ref="agendaForm" :statusIcon="true" label-width="120px" size="small">
    <el-form-item label="标题" prop="title"
      :rules="[{ type:'string', required: true, message: '标题不能为空'}]">
      <el-input v-model.trim="agendaForm.title" placeholder="请输入标题" style="width:220px"></el-input>
    </el-form-item>
    <el-form-item label="首次执行时间" prop="nextTime"
      :rules="[{ type:'string', required: true, message: '首次执行时间不能为空', trigger: 'change'}]">
      <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期" v-model="agendaForm.nextTime"></el-date-picker>
    </el-form-item>
    <el-form-item label="重复单位" prop="quantity"
                  :rules="[{ type:'number', required: true, message: '重复不能为空'}]">
      <el-input v-model.number="agendaForm.quantity" style="width:220px"></el-input>
      <el-select v-model="agendaForm.unit" placeholder="请选择"  style="width:80px">
        <el-option
          v-for="item in unitOption"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="开启" prop="open"
                  :rules="[{ type:'boolean', required: true, message: '重复不能为空'}]">
      <el-switch
        style="display: block"
        v-model="agendaForm.open"
        active-color="#13ce66"
        inactive-color="#ff4949">
      </el-switch>
    </el-form-item>
    <el-form-item label="说明">
      <mavon-editor v-model="agendaForm.remark"></mavon-editor>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="default" @click="doCreate">提交</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'agendaCreate',
    data() {
      return {
        agendaForm: {
          title: null,
          nextTime: null,
          remark: '',
          unit: 'day',
          quantity: null,
          open: true,
        },
        unitOption: [
          { label: '天', value: 'day' },
          { label: '周', value: 'week' },
          { label: '月', value: 'month' },
        ],
      };
    },
    methods: {
      ...mapActions(['saveAgenda']),
      doCreate() {
        this.loading = true;
        this.$refs.agendaForm.validate((valid) => {
          if (valid) {
            this.saveAgenda(this.agendaForm).then(() => {
              this.$notify({
                message: `日程${this.agendaForm.title}创建成功`,
                type: 'info',
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
