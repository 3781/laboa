<template>
  <div class="main-container">
    <el-form :model="applySelectQuery.filterQuery" ref="filterForm" label-width="80px" size="mini" inline
             style="margin-bottom: 20px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="申请ID" prop="applyId" style="margin-right:0;">
            <el-input v-model.trim="applySelectQuery.filterQuery.applyId" placeholder="请输入申请ID"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="用户名" prop="username" style="margin-right:0;">
            <el-input v-model.trim="applySelectQuery.filterQuery.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="姓名" prop="name" style="margin-right:0;">
            <el-input v-model.trim="applySelectQuery.filterQuery.name" placeholder="请输入姓名"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="工号" prop="employeeNumber" style="margin-right:0;">
            <el-input v-model.trim="applySelectQuery.filterQuery.employeeNumber" placeholder="请输入工号"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="qq" prop="qq" style="margin-right:0;">
            <el-input v-model.trim="applySelectQuery.filterQuery.qq" placeholder="请输入qq"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="联系方式" prop="phone" style="margin-right:0;">
            <el-input v-model.trim="applySelectQuery.filterQuery.phone" placeholder="请输入联系方式"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="8">
          <el-form-item label="邮箱" prop="email" style="margin-right:0;">
            <el-input v-model.trim="applySelectQuery.filterQuery.email" placeholder="请输入邮箱"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="状态" style="margin-right:0;" prop="statuses">
            <el-select v-model="applySelectQuery.filterQuery.statuses" :multiple="true" placeholder="请选择状态">
              <el-option
                v-for="(value, key) in statusOptions"
                :key="key"
                :label="value"
                :value="key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="处理时间" style="margin-right:0;" prop="updateTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model="applySelectQuery.filterQuery.updateTimeBegin"
                            placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model="applySelectQuery.filterQuery.updateTimeEnd"
                            placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="申请时间" style="margin-right:0;" prop="applyTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model="applySelectQuery.filterQuery.applyTimeBegin"
                            placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model="applySelectQuery.filterQuery.applyTimeEnd"
                            placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <div style="float:right">
          <el-button style="display:inline-block" icon="el-icon-refresh" type="primary" size="mini" @click="reset"
                     :loading="loading">重置
          </el-button>
          <el-button style="display:inline-block" icon="el-icon-search" type="primary" size="mini"
                     @click="getApplyData" :loading="loading">查询
          </el-button>
        </div>
      </el-row>
    </el-form>
    <el-table :data="applys" v-loading="loading" size="small" :stripe="true" :border="true" ref="applyTable"
              @sort-change="handleSortChange"
              :defaultSort="{prop:this.applySelectQuery.orderQuery.field, order: this.applySelectQuery.orderQuery.order+'ending'}">
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-form label-position="left" inline class="table-expand">
            <el-form-item label="申请ID">
              <span>{{ scope.row.applyId }}</span>
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
      <el-table-column align="center" label="状态" column-key="status" prop="status"
                       sortable="custom" :resizable="true" width="80px">
        <template slot-scope="scope">
          {{ statusOptions[scope.row.status] }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="处理时间" column-key="updateTime" prop="updateTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="申请时间" column-key="applyTime" prop="applyTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
    </el-table>
    <el-pagination background style="float:right;margin-top:20px"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="currentPage + 1"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="applySelectQuery.pageQuery.rows"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="totalSize">
    </el-pagination>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'cooperationApply',
    props: {
      cooperationId: {
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
        loading: false,
        applys: [],
        totalSize: 0,
        applySelectQuery: {
          filterQuery: {
            cooperationId: null,
            applyId: null,
            username: null,
            name: null,
            employeeNumber: null,
            qq: null,
            phone: null,
            email: null,
            statuses: [],
            updateTimeBegin: null,
            updateTimeEnd: null,
            applyTimeBegin: null,
            applyTimeEnd: null,
          },
          orderQuery: {
            field: 'applyTime',
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
      currentPage() {
        return this.applySelectQuery.pageQuery.offset
          / this.applySelectQuery.pageQuery.rows;
      },
      statusOptions() {
        return {
          todo: '待处理',
          pass: '通过',
          reject: '拒绝',
        };
      },
    },
    watch: {
      isLoad() {
        if (this.isLoad) {
          this.getApplyData();
        }
      },
    },
    methods: {
      ...mapActions(['listApplys']),
      getApplyData() {
        this.loading = true;
        if (this.cooperationId != null) {
          this.applySelectQuery.filterQuery.cooperationId = this.cooperationId;
          this.listApplys(this.applySelectQuery).then((response) => {
            this.applys = response.data;
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
        const filterQuery = this.applySelectQuery.filterQuery;
        filterQuery.updateTimeBegin = null;
        filterQuery.updateTimeEnd = null;
        filterQuery.applyTimeBegin = null;
        filterQuery.applyTimeEnd = null;
        this.getApplyData();
      },
      handleSizeChange(rows) {
        this.applySelectQuery.pageQuery.rows = rows;
        this.getApplyData();
      },
      handleCurrentChange(currentPage) {
        const pageQuery = this.applySelectQuery.pageQuery;
        pageQuery.offset = (currentPage - 1) * pageQuery.rows;
        this.getApplyData();
      },
      handleSortChange({ prop, order }) {
        const orderQuery = this.applySelectQuery.orderQuery;
        orderQuery.field = prop;
        if (order) {
          orderQuery.order = order.replace('ending', '');
        } else {
          orderQuery.order = order;
        }
        this.getApplyData();
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
