<template>
  <div class="member-container">
    <el-form :model="memberSelectQuery.filterQuery" ref="filterForm" label-width="80px" size="mini" inline
             style="margin-bottom: 20px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="成员ID" prop="memberId" style="margin-right:0;">
            <el-input v-model.trim="memberSelectQuery.filterQuery.memberId" placeholder="请输入成员ID"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="用户名" prop="username" style="margin-right:0;">
            <el-input v-model.trim="memberSelectQuery.filterQuery.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="姓名" prop="name" style="margin-right:0;">
            <el-input v-model.trim="memberSelectQuery.filterQuery.name" placeholder="请输入姓名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="工号" prop="employeeNumber" style="margin-right:0;">
            <el-input v-model.trim="memberSelectQuery.filterQuery.employeeNumber" placeholder="请输入工号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="qq" prop="qq" style="margin-right:0;">
            <el-input v-model.trim="memberSelectQuery.filterQuery.qq" placeholder="请输入qq"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系方式" prop="phone" style="margin-right:0;">
            <el-input v-model.trim="memberSelectQuery.filterQuery.phone" placeholder="请输入联系方式"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email" style="margin-right:0;">
            <el-input v-model.trim="memberSelectQuery.filterQuery.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="角色" style="margin-right:0;" prop="roles">
            <el-checkbox-group v-model="memberSelectQuery.filterQuery.roles">
              <el-checkbox label="member">参与者</el-checkbox>
              <el-checkbox label="manager">管理者</el-checkbox>
              <el-checkbox label="owner">创建者</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="加入时间" style="margin-right:0;" prop="joinTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model="memberSelectQuery.filterQuery.joinTimeBegin"
                            placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model="memberSelectQuery.filterQuery.joinTimeEnd"
                            placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <div style="float:left">
          <el-button style="display:inline-block" icon="el-icon-plus" type="primary" size="mini" v-if="checkOwn || checkManage"
                     @click="dialogVisible=true">添加
          </el-button>
        </div>
        <div style="float:right">
          <el-button style="display:inline-block" icon="el-icon-refresh" type="primary" size="mini" @click="reset"
                     :loading="loading">重置
          </el-button>
          <el-button style="display:inline-block" icon="el-icon-search" type="primary" size="mini"
                     @click="getMemberData" :loading="loading">查询
          </el-button>
        </div>
      </el-row>
    </el-form>
    <el-table :data="members" v-loading="loading" size="small" :stripe="true" :border="true" ref="memberTable"
              @sort-change="handleSortChange"
              :defaultSort="{prop:this.memberSelectQuery.orderQuery.field, order: this.memberSelectQuery.orderQuery.order+'ending'}">
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-form label-position="left" inline class="table-expand">
            <el-form-item label="成员ID">
              <span>{{ scope.row.memberId }}</span>
            </el-form-item>
            <el-form-item label="qq">
              <span>{{ scope.row.qq }}</span>
            </el-form-item>
            <el-form-item label="邮箱">
              <span>{{ scope.row.email }}</span>
            </el-form-item>
            <el-form-item label="联系方式">
              <span>{{ scope.row.phone }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" column-key="username" prop="username"
                       sortable="custom" :resizable="true" width="100px">
      </el-table-column>
      <el-table-column align="center" label="姓名" column-key="name" prop="name"
                       sortable="custom" :resizable="true" width="80px">
      </el-table-column>
      <el-table-column align="center" label="工号" column-key="employeeNumber" prop="employeeNumber"
                       sortable="custom" :resizable="true" width="100px">
      </el-table-column>
      <el-table-column align="center" label="角色" column-key="role" prop="role"
                       sortable="custom" :resizable="true" width="80px">
        <template slot-scope="scope">
          <el-tag :type="roleFormatter[scope.row.role].type">{{ roleFormatter[scope.row.role].label }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="加入时间" column-key="joinTime" prop="joinTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="操作" :resizable="true" v-if="checkOwn || checkManage">
        <template slot-scope="scope">
          <el-button type="warning" size="mini" v-show=" scope.row.role!='owner' && checkOwn"
                     @click="doChangeRole({memberId:scope.row.memberId, role: scope.row.role==='member'?'manager':'member'})">修改角色</el-button>
          <el-button type="danger" size="mini" v-show="getUsername!=scope.row.username && scope.row.role!='owner'" @click="doRemove(scope.row.memberId)">移出协作</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination background style="float:right;margin-top:20px"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="currentPage + 1"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="memberSelectQuery.pageQuery.rows"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="totalSize">
    </el-pagination>
    <el-dialog title="添加成员" :visible.sync="dialogVisible" width="30%" :showClose="false">
      <el-form :model="addForm" ref="addForm" :statusIcon="true" label-width="80px" size="mini" inline
               style="margin-bottom: 20px">
        <el-form-item label="用户" prop="userId" :rules="[{ type: 'number', required: true, message: '用户不能为空'}]">
          <el-select :filterable="true" :remote="true" @focus="searchUsers('')" :remote-method="searchUsers" :loading="searching"
                     v-model.number="addForm.userId">
            <el-option v-for="item in searchUserData" :key="item.userId" :label="item.username" :value="item.userId">
              <span>{{ item.username }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色" prop="role" :rules="[{ type:'string', required: true, message: '角色不能为空'}]">
          <el-select v-model="addForm.role">
            <el-option v-for="(value, key) of roleFormatter" :key="key" :label="value.label" :value="key"
                       v-if="key != 'owner'">
              <span>{{ value.label }}</span>
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" :loading="addLoading">取 消</el-button>
        <el-button type="primary" @click="doAddMember" :loading="addLoading">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex';

  export default {
    name: 'cooperationMember',
    props: {
      cooperationId: {
        type: Number,
        default: null,
      },
      parentId: {
        type: Number,
        default: null,
      },
      isLoad: {
        type: Boolean,
        default: false,
      },
    },
    data() {
      return {
        addLoading: false,
        addForm: {
          cooperationId: null,
          userId: null,
          role: 'member',
        },
        searchUserData: [],
        searching: false,
        dialogVisible: false,
        loading: false,
        members: [],
        totalSize: 0,
        memberSelectQuery: {
          filterQuery: {
            cooperationId: null,
            memberId: null,
            username: null,
            name: null,
            employeeNumber: null,
            qq: null,
            phone: null,
            email: null,
            roles: [],
            joinTimeBegin: null,
            joinTimeEnd: null,
          },
          orderQuery: {
            field: 'joinTime',
            order: 'desc',
          },
          pageQuery: {
            offset: 0,
            rows: 10,
          },
        },
      };
    },
    computed: {
      ...mapGetters(['getPermissions', 'getUsername']),
      currentPage() {
        return this.memberSelectQuery.pageQuery.offset
          / this.memberSelectQuery.pageQuery.rows;
      },
      roleFormatter() {
        return {
          owner: {
            type: 'danger',
            label: '创建者',
          },
          manager: {
            type: 'warning',
            label: '管理者',
          },
          member: {
            type: '',
            label: '参与者',
          },
        };
      },
      checkOwn() {
        return this.getPermissions.includes(`cooperation:owner:${this.cooperationId}`);
      },
      checkManage() {
        return this.getPermissions.includes(`cooperation:manager:${this.cooperationId}`);
      },
    },
    watch: {
      isLoad() {
        if (this.isLoad) {
          this.getMemberData();
        }
      },
    },
    methods: {
      ...mapActions(['listMembers',
        'getAvailableUsers',
        'saveMember',
        'changeMemberRole',
        'deleteMember']),
      getMemberData() {
        this.loading = true;
        if (this.cooperationId != null) {
          this.memberSelectQuery.filterQuery.cooperationId = this.cooperationId;
          this.listMembers(this.memberSelectQuery).then((response) => {
            this.members = response.data;
            this.totalSize = response.totalSize;
            this.loading = false;
          }).catch((errorMessage) => {
            this.$message.error(errorMessage);
            this.loading = false;
          });
        }
      },
      reset() {
        this.$refs.filterForm.resetFields();
        const filterQuery = this.memberSelectQuery.filterQuery;
        filterQuery.joinTimeBegin = null;
        filterQuery.joinTimeEnd = null;
        this.getMemberData();
      },
      handleSizeChange(rows) {
        this.memberSelectQuery.pageQuery.rows = rows;
        this.getMemberData();
      },
      handleCurrentChange(currentPage) {
        const pageQuery = this.memberSelectQuery.pageQuery;
        pageQuery.offset = (currentPage - 1) * pageQuery.rows;
        this.getMemberData();
      },
      handleSortChange({ prop, order }) {
        const orderQuery = this.memberSelectQuery.orderQuery;
        orderQuery.field = prop;
        if (order) {
          orderQuery.order = order.replace('ending', '');
        } else {
          orderQuery.order = order;
        }
        this.getMemberData();
      },
      searchUsers(query) {
        this.searching = true;
        this.getAvailableUsers({
          username: query,
          parentId: this.parentId,
          cooperationId: this.cooperationId,
        })
        .then((users) => {
          this.searchUserData = users;
          this.searching = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.searching = false;
        });
      },
      doAddMember() {
        this.addForm.cooperationId = this.cooperationId;
        if (this.addForm.cooperationId != null) {
          this.addLoading = true;
          this.$refs.addForm.validate((valid) => {
            if (valid) {
              this.saveMember(this.addForm).then((memberId) => {
                if (memberId) {
                  this.$notify({
                    message: '添加成功',
                    type: 'info',
                    position: 'bottom-right',
                    offset: 40,
                  });
                }
                this.addLoading = false;
                this.$refs.addForm.resetFields();
                this.getMemberData();
              }).catch((errorMessage) => {
                this.$message.error(errorMessage);
                this.addLoading = false;
              });
            } else {
              this.addLoading = false;
            }
          });
        }
      },
      doChangeRole(formData) {
        this.changeMemberRole(formData).then((updateSize) => {
          if (updateSize) {
            this.$notify({
              message: '修改成功',
              type: 'info',
              position: 'bottom-right',
              offset: 40,
            });
          }
          this.getMemberData();
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
        });
      },
      doRemove(memberId) {
        this.$confirm('确认移除该成员', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
        }).then(() => {
          this.deleteMember(memberId).then((updateSize) => {
            if (updateSize) {
              this.$notify({
                message: '移出成功',
                type: 'info',
                position: 'bottom-right',
                offset: 40,
              });
            }
            this.getMemberData();
          }).catch((errorMessage) => {
            this.$message.error(errorMessage);
          });
        });
      },
    },
  };
</script>

<style lang="stylus">
  .table-expand
    font-size 0
    .el-form-item
      margin-right 0
      margin-bottom 0
      width 50%
      label
        width 90px
        color #99a9bf
</style>
