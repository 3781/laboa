<template>
  <div class="main-container">
    <el-form :model="userSelectQuery.filterQuery" ref="filterForm" label-width="80px" size="mini" inline style="margin-bottom: 20px">
      <el-row>
        <el-col :span="6">
          <el-form-item label="用户ID" prop="userId" style="margin-right:0;">
            <el-input v-model.trim="userSelectQuery.filterQuery.userId" placeholder="请输入用户ID"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="用户名" prop="username" style="margin-right:0;">
            <el-input v-model.trim="userSelectQuery.filterQuery.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="姓名" prop="name" style="margin-right:0;">
            <el-input v-model.trim="userSelectQuery.filterQuery.name" placeholder="请输入姓名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="工号" prop="employeeNumber" style="margin-right:0;">
            <el-input v-model.trim="userSelectQuery.filterQuery.employeeNumber" placeholder="请输入工号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="qq" prop="qq" style="margin-right:0;">
            <el-input v-model.trim="userSelectQuery.filterQuery.qq" placeholder="请输入qq"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="邮箱" prop="email" style="margin-right:0;">
            <el-input v-model.trim="userSelectQuery.filterQuery.email" placeholder="请输入用户邮箱"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="联系方式" prop="phone" style="margin-right:0;">
            <el-input v-model.trim="userSelectQuery.filterQuery.phone" placeholder="请输入联系方式"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="登录时间" style="margin-right:0;" prop="loginTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "userSelectQuery.filterQuery.loginTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "userSelectQuery.filterQuery.loginTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="注册时间" style="margin-right:0;" prop="registerTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "userSelectQuery.filterQuery.registerTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "userSelectQuery.filterQuery.registerTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <div style="float:left">

        </div>
        <div style="float:right">
          <el-button style="display:inline-block" icon="el-icon-refresh" type="primary" size="mini" @click="reset" :loading="loading">重置</el-button>
          <el-button style="display:inline-block" icon="el-icon-search" type="primary" size="mini" @click="getUsersData" :loading="loading">查询</el-button>
        </div>
      </el-row>
    </el-form>
    <el-table :data="users" v-loading="loading" size="small" :stripe="true" :border="true" ref="userTable"
              @sort-change="handleSortChange" @filter-change="handleFilterChange"
              :defaultSort="{prop:this.userSelectQuery.orderQuery.field, order: this.userSelectQuery.orderQuery.order+'ending'}">
      <el-table-column type="expand" >
        <template slot-scope="scope">
          <el-form label-position="left" inline class="table-expand">
            <el-form-item label="用户ID">
              <span>{{ scope.row.userId }}</span>
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
      <el-table-column align="center" type="selection">
      </el-table-column>
      <el-table-column align="center" label="用户名" column-key="username" prop="username"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="姓名" column-key="name" prop="name"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="工号" column-key="employeeNumber" prop="employeeNumber"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="角色" column-key="roles" prop="role"
                       sortable="custom" :resizable="true" filter-placement="top"
                       :filters="[{text: '用户',value: 'enduser'},{text: '管理员',value: 'admin'},{text: '超级管理员',value: 'superAdmin'}]">
        <template slot-scope="scope">
          <el-tag>{{ roleFormatter(scope.row.role) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" column-key="statuses" prop="status"
                       sortable="custom" :resizable="true" filter-placement="top"
                       :filters="[{text: '锁定',value: 'locked'},{text: '正常',value: 'normal'},{text: '禁用',value: 'disable'}]">
        <template slot-scope="scope">
          <el-tag :type="statusType(scope.row.status)">{{ statusFormatter(scope.row.status) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="最后登录时间" column-key="loginTime" prop="loginTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="注册时间" column-key="registerTime" prop="registerTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
    </el-table>
    <el-pagination background style="float:right;margin-top:20px"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage + 1"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="userSelectQuery.pageQuery.rows"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalSize">
    </el-pagination>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'userAll',
    data() {
      return {
        users: [],
        loading: false,
        totalSize: 0,
        userSelectQuery: {
          filterQuery: {
            userId: null,
            username: null,
            roles: [],
            statuses: [],
            loginTimeBegin: null,
            loginTimeEnd: null,
            registerTimeBegin: null,
            registerTimeEnd: null,
            name: null,
            employeeNumber: null,
            qq: null,
            email: null,
            phone: null,
          },
          orderQuery: {
            field: 'userId',
            order: 'desc',
          },
          pageQuery: {
            offset: 0,
            rows: 10,
          },
        },
      };
    },
    created() {
      this.getUsersData();
    },
    computed: {
      currentPage() {
        return this.userSelectQuery.pageQuery.offset / this.userSelectQuery.pageQuery.rows;
      },
    },
    methods: {
      ...mapActions(['listUsers']),
      getUsersData() {
        this.loading = true;
        this.listUsers(this.userSelectQuery).then((response) => {
          this.users = response.data;
          this.totalSize = response.totalSize;
          this.loading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
      reset() {
        this.$refs.filterForm.resetFields();
        const filterQuery = this.userSelectQuery.filterQuery;
        filterQuery.loginTimeBegin = null;
        filterQuery.loginTimeEnd = null;
        filterQuery.registerTimeBegin = null;
        filterQuery.registerTimeEnd = null;
        this.$refs.userTable.clearFilter();
        this.handleFilterChange({ roles: [], statuses: [] });
      },
      handleSizeChange(rows) {
        this.userSelectQuery.pageQuery.rows = rows;
        this.getUsersData();
      },
      handleCurrentChange(currentPage) {
        const pageQuery = this.userSelectQuery.pageQuery;
        pageQuery.offset = (currentPage - 1) * pageQuery.rows;
        this.getUsersData();
      },
      handleSortChange({ prop, order }) {
        const orderQuery = this.userSelectQuery.orderQuery;
        orderQuery.field = prop;
        if (order) {
          orderQuery.order = order.replace('ending', '');
        } else {
          orderQuery.order = order;
        }

        this.getUsersData();
      },
      handleFilterChange(filters) {
        Object.keys(filters).forEach((key) => {
          this.userSelectQuery.filterQuery[key] = filters[key];
        });
        this.getUsersData();
      },
      statusFormatter(status) {
        let formatterStatus;
        switch (status) {
          case 'locked': formatterStatus = '锁定'; break;
          case 'disable': formatterStatus = '禁用'; break;
          case 'normal': formatterStatus = '正常'; break;
          default: formatterStatus = '-';
        }
        return formatterStatus;
      },
      statusType(status) {
        let type;
        switch (status) {
          case 'locked': type = 'warning'; break;
          case 'disable': type = 'danger'; break;
          case 'normal': type = 'success'; break;
          default: type = '';
        }
        return type;
      },
      roleFormatter(role) {
        let formatterRole;
        switch (role) {
          case 'enduser': formatterRole = '用户'; break;
          case 'admin': formatterRole = '管理员'; break;
          case 'superAdmin': formatterRole = '超级管理员'; break;
          default: formatterRole = '-';
        }
        return formatterRole;
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
      width 25%
      label
        width 90px
        color #99a9bf
</style>
