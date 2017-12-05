<template>
  <div class="main-container">
    <el-form :model="fileSelectQuery.filterQuery" ref="filterForm" label-width="80px" size="mini" inline style="margin-bottom: 20px">
      <el-row>
        <el-col :span="6">
          <el-form-item label="文件ID" prop="userId" style="margin-right:0;">
            <el-input v-model.trim="fileSelectQuery.filterQuery.fileId" placeholder="请输入文件ID"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="用户名" prop="username" style="margin-right:0;">
            <el-input v-model.trim="fileSelectQuery.filterQuery.username" placeholder="请输入用户名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="文件名" prop="name" style="margin-right:0;">
            <el-input v-model.trim="fileSelectQuery.filterQuery.name" placeholder="请输入文件名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="备注" prop="remark" style="margin-right:0;">
            <el-input v-model.trim="fileSelectQuery.filterQuery.remark" placeholder="请输入备注"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="更新时间" style="margin-right:0;" prop="updateTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "fileSelectQuery.filterQuery.updateTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "fileSelectQuery.filterQuery.updateTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间" style="margin-right:0;" prop="createTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "fileSelectQuery.filterQuery.createTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "fileSelectQuery.filterQuery.createTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <div style="float:left">
          <el-button size="mini" :disabled="this.selectRows.length==0" :loading="loading" type="danger" @click="handDeleteFile">删除</el-button>
        </div>
        <div style="float:right">
          <el-button style="display:inline-block" icon="el-icon-refresh" type="primary" size="mini" @click="reset" :loading="loading">重置</el-button>
          <el-button style="display:inline-block" icon="el-icon-search" type="primary" size="mini" @click="getFilesData" :loading="loading">查询</el-button>
        </div>
      </el-row>
    </el-form>
    <el-table :data="files" v-loading="loading" size="small" :stripe="true" :border="true" ref="userTable"
              @sort-change="handleSortChange" @selection-change="handleSelectChange"
              :defaultSort="{prop:this.fileSelectQuery.orderQuery.field, order: this.fileSelectQuery.orderQuery.order+'ending'}">
      <el-table-column align="center" type="selection">
      </el-table-column>
      <el-table-column align="center" label="文件ID" column-key="fileId" prop="fileId"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="用户名" column-key="username" prop="username"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="文件名" column-key="name" prop="name"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="备注" column-key="remark" prop="remark"
                       sortable="custom" :resizable="true">
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
                   :page-size="fileSelectQuery.pageQuery.rows"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="totalSize">
    </el-pagination>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'fileAll',
    data() {
      return {
        selectRows: [],
        files: [],
        loading: false,
        totalSize: 0,
        fileSelectQuery: {
          filterQuery: {
            fileId: null,
            username: null,
            remark: null,
            name: null,
            updateTimeBegin: null,
            updateTimeEnd: null,
            createTimeBegin: null,
            createTimeEnd: null,
          },
          orderQuery: {
            field: 'fileId',
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
      this.getFilesData();
    },
    computed: {
      currentPage() {
        return this.fileSelectQuery.pageQuery.offset / this.fileSelectQuery.pageQuery.rows;
      },
      selectIds() {
        return this.selectRows.map(item => item.fileId);
      },
    },
    methods: {
      ...mapActions(['listAllFiles', 'delFile']),
      getFilesData() {
        this.loading = true;
        this.listAllFiles(this.fileSelectQuery).then((response) => {
          this.files = response.data;
          this.totalSize = response.totalSize;
          this.loading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
      reset() {
        this.$refs.filterForm.resetFields();
        const filterQuery = this.fileSelectQuery.filterQuery;
        filterQuery.updateTimeBegin = null;
        filterQuery.updateTimeEnd = null;
        filterQuery.createTimeBegin = null;
        filterQuery.createTimeEnd = null;
        this.getFilesData();
      },
      handleSizeChange(rows) {
        this.fileSelectQuery.pageQuery.rows = rows;
        this.getFilesData();
      },
      handleCurrentChange(currentPage) {
        const pageQuery = this.fileSelectQuery.pageQuery;
        pageQuery.offset = (currentPage - 1) * pageQuery.rows;
        this.getFilesData();
      },
      handleSortChange({ prop, order }) {
        const orderQuery = this.fileSelectQuery.orderQuery;
        orderQuery.field = prop;
        if (order) {
          orderQuery.order = order.replace('ending', '');
        } else {
          orderQuery.order = order;
        }

        this.getFilesData();
      },
      handleSelectChange(selection) {
        this.selectRows = selection;
      },
      handDeleteFile() {
        this.loading = true;
        this.delFile({ ids: this.selectIds }).then((updateCount) => {
          if (updateCount) {
            this.$notify({
              message: `成功删除${updateCount}份文件`,
              type: 'info',
              position: 'bottom-right',
              offset: 40,
            });
            this.getFilesData();
          } else {
            this.$notify({
              message: '没有文件被删除',
              type: 'info',
              position: 'bottom-right',
              offset: 40,
            });
          }
          this.loading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
