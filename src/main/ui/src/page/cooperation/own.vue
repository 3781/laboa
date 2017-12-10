<template>
  <div class="main-container">
    <el-form :model="cooperationSelectQuery.filterQuery" ref="filterForm" label-width="80px" size="mini" inline style="margin-bottom: 20px">
      <el-row>
        <el-col :span="6">
          <el-form-item label="协作ID" prop="cooperationId" style="margin-right:0;"  >
            <el-input v-model.trim="cooperationSelectQuery.filterQuery.cooperationId" placeholder="请输入协作ID"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="协作名" prop="name" style="margin-right:0;">
            <el-input v-model.trim="cooperationSelectQuery.filterQuery.name" placeholder="协作名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="等级" prop="hasParent" style="margin-right:0;">
            <el-select v-model="cooperationSelectQuery.filterQuery.hasParent" placeholder="请选择">
              <el-option
                v-for="item in hasParentOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="邀请链接" prop="invite" style="margin-right:0;">
            <el-select v-model="cooperationSelectQuery.filterQuery.invite" placeholder="请选择">
              <el-option
                v-for="item in inviteOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="开始日期" style="margin-right:0;" prop="beginDate">
            <el-date-picker type="date" value-format="yyyy-MM-dd"
                            v-model = "cooperationSelectQuery.filterQuery.beginDateBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="date" value-format="yyyy-MM-dd"
                            v-model = "cooperationSelectQuery.filterQuery.beginDateEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="结束时间" style="margin-right:0;" prop="endDate">
            <el-date-picker type="date" value-format="yyyy-MM-dd"
                            v-model = "cooperationSelectQuery.filterQuery.endDateBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="date" value-format="yyyy-MM-dd"
                            v-model = "cooperationSelectQuery.filterQuery.endDateEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="更新时间" style="margin-right:0;" prop="updateTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "cooperationSelectQuery.filterQuery.updateTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "cooperationSelectQuery.filterQuery.updateTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间" style="margin-right:0;" prop="createTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "cooperationSelectQuery.filterQuery.createTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "cooperationSelectQuery.filterQuery.createTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <div style="float:right">
          <el-button style="display:inline-block" icon="el-icon-refresh" type="primary" size="mini" @click="reset" :loading="loading">重置</el-button>
          <el-button style="display:inline-block" icon="el-icon-search" type="primary" size="mini" @click="getCooperationData" :loading="loading">查询</el-button>
        </div>
      </el-row>
    </el-form>
    <el-table :data="cooperations" v-loading="loading" size="small" :stripe="true" :border="true" ref="cooperationTable"
              @sort-change="handleSortChange" @filter-change="handleFilterChange"
              :defaultSort="{prop:this.cooperationSelectQuery.orderQuery.field, order: this.cooperationSelectQuery.orderQuery.order+'ending'}">
      <el-table-column type="expand" >
        <template slot-scope="scope">
          <mavon-editor v-model="scope.row.remark" :toolbarsFlag="false" :subfield="false" default_open="preview"></mavon-editor>
        </template>
      </el-table-column>
      <el-table-column align="center" type="selection">
      </el-table-column>
      <el-table-column align="center" label="协作ID" column-key="cooperationId" prop="cooperationId" width="100px"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="名称" column-key="name" prop="name"
                       sortable="custom" :resizable="true" width="80px">
        <template slot-scope="scope">
          <router-link :to="'/cooperation/' + scope.row.cooperationId">{{ scope.row.name }}</router-link>
        </template>
      </el-table-column>
      <el-table-column align="center" label="等级" column-key="parentName" prop="parentName"
                       sortable="custom" :resizable="true"  width="80px">
        <template slot-scope="scope">
          {{ scope.row.parentName == null ? '顶级':'非顶级' }}
        </template>
      </el-table-column>
      <!--<el-table-column align="center" label="创建者" column-key="ownerName" prop="ownerName"-->
                       <!--sortable="custom" :resizable="true">-->
      <!--</el-table-column>-->
      <el-table-column align="center" label="邀请链接" column-key="name" prop="name"
                       sortable="custom" :resizable="true" width="100px">
        <template slot-scope="scope">
          {{ scope.row.invite ? '开启':'关闭' }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="开始日期" column-key="beginDate" prop="beginDate"
                       sortable="custom" :resizable="true"  width="100px">
      </el-table-column>
      <el-table-column align="center" label="结束日期" column-key="endDate" prop="endDate"
                       sortable="custom" :resizable="true"  width="100px">
      </el-table-column>
      <el-table-column align="center" label="更新时间" column-key="updateTime" prop="updateTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="创建时间" column-key="createTime" prop="createTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
    </el-table>
    <el-pagination background style="float:right;margin-top:20px"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="currentPage + 1"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="cooperationSelectQuery.pageQuery.rows"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="totalSize">
    </el-pagination>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'cooperationOwn',
    data() {
      return {
        cooperations: [],
        hasParentOptions: [
          { label: '全部', value: null },
          { label: '顶级', value: false },
          { label: '非顶级', value: true },
        ],
        inviteOptions: [
          { label: '全部', value: null },
          { label: '开启', value: true },
          { label: '关闭', value: false },
        ],
        loading: false,
        totalSize: 0,
        cooperationSelectQuery: {
          filterQuery: {
            cooperationId: null,
            hasParent: null,
            invite: null,
            remark: null,
            name: null,
            ownerName: null,
            beginDateBegin: null,
            endDateBegin: null,
            beginDateEnd: null,
            endDateEnd: null,
            updateTimeBegin: null,
            createTimeBegin: null,
            updateTimeEnd: null,
            createTimeEnd: null,
          },
          orderQuery: {
            field: 'createTime',
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
        return this.cooperationSelectQuery.pageQuery.offset
          / this.cooperationSelectQuery.pageQuery.rows;
      },
    },
    methods: {
      ...mapActions(['listOwnCooperation']),
      getCooperationData() {
        this.loading = true;
        this.listOwnCooperation(this.cooperationSelectQuery).then((response) => {
          this.cooperations = response.data;
          this.totalSize = response.totalSize;
          this.loading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
      reset() {
        this.$refs.filterForm.resetFields();
        const filterQuery = this.cooperationSelectQuery.filterQuery;
        filterQuery.beginDateBegin = null;
        filterQuery.beginDateEnd = null;
        filterQuery.endDateBegin = null;
        filterQuery.endDateEnd = null;
        filterQuery.updateTimeBegin = null;
        filterQuery.updateTimeEnd = null;
        filterQuery.createTimeBegin = null;
        filterQuery.createTimeEnd = null;
        this.$refs.cooperationTable.clearFilter();
        this.handleFilterChange({ units: [] });
      },
      handleSizeChange(rows) {
        this.cooperationSelectQuery.pageQuery.rows = rows;
        this.getCooperationData();
      },
      handleCurrentChange(currentPage) {
        const pageQuery = this.cooperationSelectQuery.pageQuery;
        pageQuery.offset = (currentPage - 1) * pageQuery.rows;
        this.getCooperationData();
      },
      handleSortChange({ prop, order }) {
        const orderQuery = this.cooperationSelectQuery.orderQuery;
        orderQuery.field = prop;
        if (order) {
          orderQuery.order = order.replace('ending', '');
        } else {
          orderQuery.order = order;
        }

        this.getCooperationData();
      },
      handleFilterChange(filters) {
        Object.keys(filters).forEach((key) => {
          this.cooperationSelectQuery.filterQuery[key] = filters[key];
        });
        this.getCooperationData();
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
