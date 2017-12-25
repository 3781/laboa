<template>
  <div class="main-container">
    <el-form :model="fileSelectQuery.filterQuery" ref="filterForm" label-width="80px" size="mini" inline style="margin-bottom: 20px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="文件ID" prop="userId" style="margin-right:0;">
            <el-input v-model.trim="fileSelectQuery.filterQuery.fileId" placeholder="请输入文件ID"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="文件名" prop="name" style="margin-right:0;">
            <el-input v-model.trim="fileSelectQuery.filterQuery.name" placeholder="请输入文件名"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
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
      <el-table-column align="center" label="文件名" column-key="name" prop="name"
                       sortable="custom" :resizable="true">
        <template slot-scope="scope">
          <a :href="`${urlPrefix}/api/file/${scope.row.fileId}`">{{ scope.row.name }}</a>
        </template>
      </el-table-column>
      <el-table-column align="center" label="备注" column-key="remark" prop="remark"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="链接" column-key="link" prop="link"
                       sortable="custom" :resizable="true">
        <template slot-scope="scope">
          <span style="text-overflow: ellipsis;white-space:nowrap;overflow:hidden;">{{urlPrefix}}/api/file/{{scope.row.fileId}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间" column-key="updateTime" prop="updateTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="创建时间" column-key="createTime" prop="createTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="操作" width="270px">
        <template slot-scope="scope">
          <el-button-group>
            <el-upload
              ref="upload"
              :action="`/api/file/${scope.row.fileId}`"
              :with-credentials="true"
              :on-success="handleSuccessUpload"
              :on-error="handleErrorUpload"
            :show-file-list="false">
              <el-button slot="trigger" size="mini" type="primary">更新文件</el-button>
              <el-button size="mini" type="warning" @click="handleUpdateRemark(scope.row)">更新备注</el-button>
              <el-button size="mini" type="success" v-clipboard:copy="`${urlPrefix}/api/file/${scope.row.fileId}`" v-clipboard:success="copySuccess">复制链接</el-button>
            </el-upload>
          </el-button-group>
        </template>
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
    <el-dialog title="备注更新" :visible.sync="showUpdateFileForm" v-loading="loading">
      <div style="text-align: center">
        <el-form ref="form" :model="updateForm" label-width="80px">

        <el-form-item label="备注" prop="remark">
          <el-input type="textarea" v-model="updateForm.remark" style="min-height:100px"></el-input>
        </el-form-item>
        </el-form>
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button @click="showUpdateFileForm = false" >取 消</el-button>
        <el-button type="primary" @click="doUpdateRemark">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'fileOwn',
    data() {
      return {
        showUpdateFileForm: false,
        updateForm: {
          fileId: null,
          remark: '',
        },
        selectRows: [],
        files: [],
        loading: false,
        totalSize: 0,
        fileSelectQuery: {
          filterQuery: {
            fileId: null,
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
    computed: {
      currentPage() {
        return this.fileSelectQuery.pageQuery.offset / this.fileSelectQuery.pageQuery.rows;
      },
      selectIds() {
        return this.selectRows.map(item => item.fileId);
      },
      urlPrefix() {
        return `http://${window.location.host}`;
      },
    },
    methods: {
      ...mapActions(['listFiles', 'delFile', 'updateFile']),
      getFilesData() {
        this.loading = true;
        this.listFiles(this.fileSelectQuery).then((response) => {
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
      handleUpdateRemark(fileInfo) {
        this.updateForm.fileId = fileInfo.fileId;
        this.updateForm.remark = fileInfo.remark === null ? '' : fileInfo.remark;
        this.showUpdateFileForm = true;
      },
      doUpdateRemark() {
        this.updateFile(this.updateForm).then(() => {
          this.$notify({
            message: '更新成功',
            type: 'info',
            position: 'bottom-right',
            offset: 40,
          });
          this.showUpdateFileForm = false;
          this.getFilesData();
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
        });
      },
      handleSuccessUpload(response) {
        if (response.success) {
          this.$notify({
            message: '更新成功',
            type: 'info',
            position: 'bottom-right',
            offset: 40,
          });
          this.getFilesData();
        } else {
          this.$notify({
            message: response.info,
            type: 'error',
            position: 'bottom-right',
            offset: 40,
          });
        }
      },
      handleErrorUpload() {
        this.$notify({
          message: '更新失败，请稍后再试',
          type: 'error',
          position: 'bottom-right',
          offset: 40,
        });
      },
      copySuccess() {
        this.$notify({
          message: '复制链接成功',
          type: 'info',
          position: 'bottom-right',
          offset: 40,
        });
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
