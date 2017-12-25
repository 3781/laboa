<template>
  <el-form :model="agendaForm" ref="agendaForm" :statusIcon="true" label-width="120px" size="small" v-loading.lock="loading">
    <el-form-item label="标题" prop="title"
      :rules="[{ type:'string', required: true, message: '标题不能为空'}]">
      <el-input v-model.trim="agendaForm.title" placeholder="请输入标题" style="width:220px"></el-input>
    </el-form-item>
    <el-form-item label="首次执行时间" prop="nextTime"
      :rules="[{validator:validateNextTime, required:true}]">
      <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss" placeholder="选择日期" v-model="agendaForm.nextTime"></el-date-picker>
    </el-form-item>
    <el-form-item label="重复" prop="quantity"
                  :rules="[{ type:'integer', required: true, message: '数量为大于1的整数', min: 1}]">
      <el-input v-model.number="agendaForm.quantity" style="width:220px" v-bind:disabled="disabledQuantity"></el-input>
      <el-select v-model="agendaForm.unit" placeholder="请选择"  style="width:100px" @change="isOnceSelected">
        <el-option
          v-for="item in unitOption"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item v-if="agendaForm.cooperationId!=null" label="参与成员" prop="memberIds">
      <el-select v-model="agendaForm.memberIds" placeholder="请选择" :loading="searching" style="width: 100%"
                @focus="searchParticipant('')" :multiple="true" :filterable="true" :remote="true" :remote-method="searchParticipant">
        <el-option
          v-for="item in members"
          :key="item.memberId"
          :label="item.username"
          :value="item.memberId">
          <span>{{ item.username}}
            <el-tag>姓名: {{ item.name}}</el-tag>
            <el-tag>工号: {{ item.employeeNumber}}</el-tag>
          </span>
        </el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="说明" prop="remark">
      <mavon-editor ref="mdEditor" @imgAdd="imgAdd" v-model="agendaForm.remark" style="min-height:290px"></mavon-editor>
    </el-form-item>
    <el-form-item v-if="showSubmitButton">
      <el-button type="primary" size="default" @click="doSummit">提交</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
  import { mapActions } from 'vuex';
  import validate from '../../util/validateDate';
  import formatDate from '../../util/formatDate';

  export default {
    name: 'agendaCreate',
    props: {
      memberList: {
        type: Array,
        default: () => null,
        required: false,
      },
      showSubmitButton: {
        type: Boolean,
        default: true,
      },
      agendaFormData: {
        type: Object,
        default: () => ({
          agendaId: null,
          title: null,
          nextTime: null,
          remark: '',
          unit: 'day',
          quantity: null,

          cooperationId: null,
          memberIds: [],
        }),
      },
    },
    data() {
      return {
        agendaForm: this.agendaFormData,
        unitOption: [
          { label: '一次', value: 'once' },
          { label: '天', value: 'day' },
          { label: '周', value: 'week' },
          { label: '月', value: 'month' },
        ],
        members: this.memberList,
        searching: false,
        loading: false,
        disabledQuantity: false,
      };
    },
    methods: {
      ...mapActions(['saveAgenda', 'updateAgenda', 'getAvailableParticipants', 'addFile']),
      doSummit() {
        if (this.agendaForm.agendaId == null) {
          this.doCreate();
        } else {
          this.doUpdate();
        }
      },
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
              this.$emit('successSubmit', this.agendaForm);
              this.$refs.agendaForm.resetFields();
            }).catch((errorMessage) => {
              this.$message.error(errorMessage);
              this.loading = false;
            });
          } else {
            this.loading = false;
          }
        });
      },
      doUpdate() {
        this.loading = true;
        this.$refs.agendaForm.validate((valid) => {
          if (valid) {
            this.updateAgenda(this.agendaForm).then(() => {
              this.$notify({
                message: `日程${this.agendaForm.title}更新成功`,
                type: 'info',
                position: 'bottom-right',
                offset: 40,
              });
              this.loading = false;
              this.$emit('successSubmit', this.agendaForm);
            }).catch((errorMessage) => {
              this.$message.error(errorMessage);
              this.loading = false;
            });
          } else {
            this.loading = false;
          }
        });
      },
      searchParticipant(query) {
        this.getAvailableParticipants({
          cooperationId: this.agendaForm.cooperationId,
          username: query,
        }).then((members) => {
          this.members = members;
          this.searching = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.members = [];
          this.searching = false;
        });
      },
      validateNextTime(rule, value, callback) {
        if (this.agendaFormData.nextTime == null) {
          callback(new Error('首次执行时间不能为空'));
        } else {
          const currentDate = new Date();
          const valid = validate(formatDate(currentDate, 'yyyy-MM-dd hh:mm:ss'), this.agendaFormData.nextTime);
          if (!valid) {
            callback(new Error('首次执行时间不能早于当前时间'));
          } else {
            callback();
          }
        }
      },
      isOnceSelected(value) {
        if (value === 'once') {
          this.agendaForm.quantity = 1;
          this.disabledQuantity = true;
        } else {
          this.disabledQuantity = false;
        }
      },
      imgAdd(pos, file) {
        const formData = new FormData();
        formData.append('file', file);
        this.addFile(formData).then((info) => {
          this.$notify({
            message: `文件${file.name}上传成功`,
            type: 'info',
            position: 'bottom-right',
            offset: 40,
          });
          this.$refs.mdEditor.$img2Url(pos, `/api/file/${info}`);
          this.$refs.mdEditor.$refs.toolbar_left.$imgDelByFilename(pos);
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
        });
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
