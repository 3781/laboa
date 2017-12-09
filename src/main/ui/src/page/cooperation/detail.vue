<template>
  <div class="main-container">
    <el-row >
      <el-col :span="6" >
        <el-tree :data="cooperationTreeData" :props="treeProps" :indent="8" :accordion="true" :highlight-current="true"
                 v-loading.lock="treeLoading" node-key="cooperationId" @current-change="currentChange"
                 :default-expanded-keys="this.currentCooperationId!=null?[this.currentCooperationId]:[]">
        </el-tree>
      </el-col>
      <el-col :span="18">
        <el-tabs v-model="activeTab" @tab-click="handleClick" v-show="currentCooperationId!=null">
          <el-tab-pane label="协作信息" name="info">
            <el-form v-show="cooperationInfo!=null" label-width="80px">
              <el-form-item label="所属协作" v-show="cooperationInfo!=null && cooperationInfo.parentName != null">
                <span>{{cooperationInfo!=null? cooperationInfo.parentName:'' }}</span>
              </el-form-item>
              <el-form-item label="协作ID" v-show="cooperationInfo!=null">
                <span>{{ cooperationInfo!=null?cooperationInfo.cooperationId:'' }}</span>
              </el-form-item>
              <el-form-item label="协作名" v-show="cooperationInfo!=null">
                <span>{{ cooperationInfo!=null?cooperationInfo.name:'' }}</span>
              </el-form-item>
              <el-form-item label="协作日期" v-show="cooperationInfo!=null">
                <span>
                  {{ cooperationInfo!=null&&cooperationInfo.beginDate!=null? cooperationInfo.beginDate:'-'}}
                  至
                  {{ cooperationInfo!=null&&cooperationInfo.endDate!=null? cooperationInfo.endDate:'-' }}
                </span>
              </el-form-item>
              <el-form-item label="创建者" v-show="cooperationInfo!=null">
                <span>{{ cooperationInfo!=null?cooperationInfo.ownerName:'' }}</span>
              </el-form-item>
              <el-form-item label="邀请链接" v-show="cooperationInfo!=null && cooperationInfo.invite">
                <span>http://localhost:3000/cooperation/invite/{{cooperationInfo!=null?cooperationInfo.cooperationId:''}}</span>
              </el-form-item>
              <el-form-item label="更新时间" v-show="cooperationInfo!=null && cooperationInfo.updateTime != null">
                <span>{{ cooperationInfo!=null?cooperationInfo.updateTime:'' }}</span>
              </el-form-item>
              <el-form-item label="创建时间"  v-show="cooperationInfo!=null">
                <span>{{ cooperationInfo!=null?cooperationInfo.createTime:'' }}</span>
              </el-form-item>
              <el-form-item  v-show="cooperationInfo!=null">
                <el-button  v-show="checkManage || checkOwn"
                  type="primary" @click="createDialogVisible = true">添加子协作</el-button>
                <el-button  v-show="checkOwn"
                  type="warning" @click="prepareUpdate">更新</el-button>
                <el-button v-show="checkOwn"
                           type="danger" @click="doDelete">删除</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
          <el-tab-pane label="协作说明" name="remark">
            <mavon-editor v-if="cooperationInfo!=null" :value="cooperationInfo.remark" :toolbarsFlag="false" :subfield="false" default_open="preview"></mavon-editor>
          </el-tab-pane>
          <el-tab-pane label="协作成员" name="member">
            协作成员
          </el-tab-pane>
          <el-tab-pane label="协作日程" name="agenda">
            协作日程
          </el-tab-pane>
          <el-tab-pane label="协作申请" name="apply"  :disabled="!(checkManage || checkOwn)">
            协作申请
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
    <el-dialog title="更新协作信息" :visible.sync="dialogVisible" :fullscreen="true">
      <el-form :model="cooperationForm" ref="cooperationForm" :statusIcon="true" label-width="120px" size="small">
        <el-form-item label="协作名" prop="name"
                      :rules="[{ type:'string', required: true, message: '协作名不能为空'}]">
          <el-input v-model.trim="cooperationForm.name" placeholder="请输入协作名" style="width:220px"></el-input>
        </el-form-item>
        <el-form-item label="时间" >
          <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="cooperationForm.beginDate"></el-date-picker>
          至
          <el-date-picker type="date" value-format="yyyy-MM-dd" placeholder="选择日期" v-model="cooperationForm.endDate"></el-date-picker>
        </el-form-item>
        <el-form-item label="申请链接开启">
          <el-switch
            v-model="cooperationForm.invite"
            active-color="#13ce66"
            inactive-color="#ff4949">
          </el-switch>
        </el-form-item>
        <el-form-item label="协作介绍">
          <mavon-editor v-model="cooperationForm.remark" style="min-height:290px"></mavon-editor>
        </el-form-item>
      </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="doUpdate" :loading="updateLoading">更新</el-button>
        </span>
    </el-dialog>
    <el-dialog title="添加子协作" :visible.sync="createDialogVisible" :fullscreen="true">
      <cooperationCreate :parent="this.cooperationInfo" @createSuccess="createSuccess"></cooperationCreate>
    </el-dialog>
  </div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex';
  import cooperationCreate from './create';

  export default {
    name: 'cooperationDetail',
    components: {
      cooperationCreate,
    },
    data() {
      return {
        activeTab: 'null',
        treeLoading: false,
        treeProps: {
          label: 'name',
          children: 'children',
        },
        cooperationTreeData: [],
        currentCooperationId: null,
        cooperationInfo: null,
        dialogVisible: false,
        updateLoading: false,
        cooperationForm: {
          cooperationId: null,
          name: null,
          beginDate: null,
          endDate: null,
          remark: '',
          invite: false,
        },
        createDialogVisible: false,
      };
    },
    created() {
      this.loadTreeData();
    },
    computed: {
      ...mapGetters(['getPermissions']),
      checkOwn() {
        return this.getPermissions.includes(`owner${this.currentCooperationId}`);
      },
      checkManage() {
        return this.getPermissions.includes(`manager${this.currentCooperationId}`);
      },
    },
    methods: {
      ...mapActions(['listCooperationTree', 'getCooperation', 'updateCooperation', 'deleteCooperation']),
      loadTreeData() {
        this.treeLoading = true;
        this.listCooperationTree(this.$route.params.cooperationId).then((info) => {
          if (info) {
            this.cooperationTreeData = [info];
          } else {
            this.cooperationTreeData = [];
          }
          this.treeLoading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.treeLoading = false;
        });
      },
      loadCooperationInfo() {
        if (this.cooperationInfo === null) {
          this.getCooperation(this.currentCooperationId).then((info) => {
            this.cooperationInfo = info;
          }).catch((errorMessage) => {
            this.$message.error(errorMessage);
          });
        }
      },
      handleClick(tab) {
        if (tab.name === 'info' || tab.name === 'remark') {
          this.loadCooperationInfo();
        }
      },
      currentChange(data) {
        this.activeTab = null;
        this.cooperationInfo = null;
        this.currentCooperationId = data.cooperationId;
      },
      prepareUpdate() {
        this.dialogVisible = true;
        this.cooperationForm.cooperationId = this.cooperationInfo.cooperationId;
        this.cooperationForm.name = this.cooperationInfo.name;
        this.cooperationForm.beginDate = this.cooperationInfo.beginDate;
        this.cooperationForm.endDate = this.cooperationInfo.endDate;
        this.cooperationForm.remark = this.cooperationInfo.remark;
        this.cooperationForm.invite = this.cooperationInfo.invite;
      },
      doUpdate() {
        this.updateLoading = true;
        this.$refs.cooperationForm.validate((valid) => {
          if (valid) {
            this.updateCooperation(this.cooperationForm).then(() => {
              this.$notify({
                message: `协作${this.cooperationForm.name}更新成功`,
                type: 'info',
                position: 'bottom-right',
                offset: 40,
              });
              this.updateLoading = false;
              this.dialogVisible = false;
              this.loadTreeData();
              this.cooperationInfo = null;
              this.loadCooperationInfo();
            }).catch((errorMessage) => {
              this.$message.error(errorMessage);
              this.updateLoading = false;
            });
          } else {
            this.updateLoading = false;
          }
        });
      },
      createSuccess(cooperationId) {
        this.createDialogVisible = false;
        this.currentCooperationId = cooperationId;
        this.cooperationInfo = null;
        this.activeTab = null;
        this.loadTreeData();
      },
      doDelete() {
        this.$confirm('此操作将永久删除协作, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'error',
        }).then(() => {
          this.deleteCooperation(this.currentCooperationId).then(() => {
            this.$notify({
              message: '删除成功',
              type: 'info',
              position: 'bottom-right',
              offset: 40,
            });
            this.currentCooperationId = null;
            this.cooperationInfo = null;
            this.loadTreeData();
          }).catch((errorMessage) => {
            this.$message.error(errorMessage);
            this.updateLoading = false;
          });
        });
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
