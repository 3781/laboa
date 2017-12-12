<template>
  <div class="main-container">
    <el-form :model="agendaSelectQuery.filterQuery" ref="filterForm" label-width="80px" size="mini" inline style="margin-bottom: 20px">
      <el-row>
        <el-col :span="6">
          <el-form-item label="日程ID" prop="agendaId" style="margin-right:0;"  >
            <el-input v-model.trim="agendaSelectQuery.filterQuery.agendaId" placeholder="请输入用户ID"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="标题" prop="title" style="margin-right:0;">
            <el-input v-model.trim="agendaSelectQuery.filterQuery.title" placeholder="请输入标题"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="重复数量" prop="quantity" style="margin-right:0;">
            <el-input v-model.number="agendaSelectQuery.filterQuery.quantity" placeholder="请输入重复数量"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="状态" prop="open" style="margin-right:0;">
            <el-select v-model="agendaSelectQuery.filterQuery.open" placeholder="请选择状态">
              <el-option
                v-for="item in openOptions"
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
          <el-form-item label="说明" prop="remark" style="margin-right:0;">
            <el-input v-model="agendaSelectQuery.filterQuery.remark" placeholder="请输入说明" style="width: 400px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="执行时间" style="margin-right:0;" prop="nextTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "agendaSelectQuery.filterQuery.nextTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "agendaSelectQuery.filterQuery.nextTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="更新时间" style="margin-right:0;" prop="updateTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "agendaSelectQuery.filterQuery.updateTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "agendaSelectQuery.filterQuery.updateTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间" style="margin-right:0;" prop="createTime">
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "agendaSelectQuery.filterQuery.createTimeBegin" placeholder="请选择起始日期"></el-date-picker>
            至
            <el-date-picker type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                            v-model = "agendaSelectQuery.filterQuery.createTimeEnd" placeholder="请选择终止日期"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <div style="float:left">
          <el-button-group>
            <el-button size="mini" :disabled="this.selectRows.length==0" :loading="loading" type="danger" @click="handleDelete">删除</el-button>
            <el-button size="mini" :disabled="this.selectRows.length==0" :loading="loading" type="warning" @click="handleClose">关闭</el-button>
            <el-button size="mini" :disabled="this.selectRows.length==0" :loading="loading" type="success" @click="handleOpen">开启</el-button>
          </el-button-group>
        </div>
        <div style="float:right">
          <el-button style="display:inline-block" icon="el-icon-refresh" type="primary" size="mini" @click="reset" :loading="loading">重置</el-button>
          <el-button style="display:inline-block" icon="el-icon-search" type="primary" size="mini" @click="getAgendasData" :loading="loading">查询</el-button>
        </div>
      </el-row>
    </el-form>
    <el-table :data="agendas" v-loading="loading" size="small" :stripe="true" :border="true" ref="agendaTable"
              @sort-change="handleSortChange" @filter-change="handleFilterChange" @selection-change="handleSelectChange"
              :defaultSort="{prop:this.agendaSelectQuery.orderQuery.field, order: this.agendaSelectQuery.orderQuery.order+'ending'}">
      <el-table-column type="expand" >
        <template slot-scope="scope">
          <mavon-editor v-model="scope.row.remark" :toolbarsFlag="false" :subfield="false" default_open="preview"></mavon-editor>
        </template>
      </el-table-column>
      <el-table-column align="center" type="selection">
      </el-table-column>
      <el-table-column align="center" label="日程ID" column-key="agendaId" prop="agendaId" width="100px"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="标题" column-key="title" prop="title"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="下次执行时间" column-key="nextTime" prop="nextTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="重复" column-key="quantity" prop="quantity"
                       sortable="custom" :resizable="true" width="80px">
      </el-table-column>
      <el-table-column align="center" label="单位" column-key="units" prop="unit"
                       sortable="custom" :resizable="true" filter-placement="top" width="100px"
                       :filters="[{text: '一次',value: 'once'},{text: '天',value: 'day'},{text: '周',value: 'week'},{text: '月',value: 'month'}]">
        <template slot-scope="scope">
          {{ unitFormatter(scope.row.unit) }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" column-key="opens" prop="open"
                       sortable="custom" :resizable="true"  width="80px">
        <template slot-scope="scope">
          {{ scope.row.open ? '开启' : '关闭'}}
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间" column-key="updateTime" prop="updateTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="创建时间" column-key="createTime" prop="createTime"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="操作" width="140px">
          <template slot-scope="scope">
            <el-button-group>
              <el-button size="mini" type="warning" @click="handleUpdateAgenda(scope.row)">更新</el-button>
              <router-link :to="`/agenda/${scope.row.agendaId}`" tag="span">
                <el-button size="mini" type="info">详情</el-button>
              </router-link>
            </el-button-group>
          </template>
      </el-table-column>
    </el-table>
    <el-pagination background style="float:right;margin-top:20px"
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="currentPage + 1"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="agendaSelectQuery.pageQuery.rows"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="totalSize">
    </el-pagination>

    <el-dialog title="日程更新" :visible.sync="showUpdateAgendaForm" :fullscreen="true" v-loading="loading">
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
                      :rules="[{ type:'integer', min: 1, required: true, message: '数量为大于1的整数'}]">
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
        <el-form-item label="说明">
          <mavon-editor v-model="agendaForm.remark" style="min-height:290px"></mavon-editor>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showUpdateAgendaForm = false" >取 消</el-button>
        <el-button type="primary" @click="doUpdateAgenda">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'agendaOwn',
    data() {
      return {
        showUpdateAgendaForm: false,
        agendaForm: {
          agendaId: null,
          title: null,
          nextTime: null,
          quantity: null,
          unit: null,
          remark: null,
        },
        unitOption: [
          { label: '一次', value: 'once' },
          { label: '天', value: 'day' },
          { label: '周', value: 'week' },
          { label: '月', value: 'month' },
        ],
        selectRows: [],
        agendas: [],
        openOptions: [
          { label: '全部', value: null },
          { label: '开启', value: true },
          { label: '关闭', value: false },
        ],
        loading: false,
        totalSize: 0,
        agendaSelectQuery: {
          filterQuery: {
            agendaId: null,
            title: null,
            nextTimeBegin: null,
            nextTimeEnd: null,
            quantity: null,
            unit: null,
            updateTimeBegin: null,
            updateTimeEnd: null,
            createTimeBegin: null,
            createTimeEnd: null,
          },
          orderQuery: {
            field: 'nextTime',
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
        return this.agendaSelectQuery.pageQuery.offset / this.agendaSelectQuery.pageQuery.rows;
      },
      selectIds() {
        return this.selectRows.map(item => item.agendaId);
      },
    },
    methods: {
      ...mapActions(['listOwnAgendas', 'closeAgenda', 'openAgenda', 'updateAgenda', 'deleteAgenda']),
      getAgendasData() {
        this.loading = true;
        this.listOwnAgendas(this.agendaSelectQuery).then((response) => {
          this.agendas = response.data;
          this.totalSize = response.totalSize;
          this.loading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
      reset() {
        this.$refs.filterForm.resetFields();
        const filterQuery = this.agendaSelectQuery.filterQuery;
        filterQuery.updateTimeBegin = null;
        filterQuery.updateTimeEnd = null;
        filterQuery.createTimeBegin = null;
        filterQuery.createTimeEnd = null;
        filterQuery.nextTimeBegin = null;
        filterQuery.nextTimeEnd = null;
        this.$refs.agendaTable.clearFilter();
        this.handleFilterChange({ units: [] });
      },
      handleSizeChange(rows) {
        this.agendaSelectQuery.pageQuery.rows = rows;
        this.getAgendasData();
      },
      handleCurrentChange(currentPage) {
        const pageQuery = this.agendaSelectQuery.pageQuery;
        pageQuery.offset = (currentPage - 1) * pageQuery.rows;
        this.getAgendasData();
      },
      handleSortChange({ prop, order }) {
        const orderQuery = this.agendaSelectQuery.orderQuery;
        orderQuery.field = prop;
        if (order) {
          orderQuery.order = order.replace('ending', '');
        } else {
          orderQuery.order = order;
        }

        this.getAgendasData();
      },
      handleFilterChange(filters) {
        Object.keys(filters).forEach((key) => {
          this.agendaSelectQuery.filterQuery[key] = filters[key];
        });
        this.getAgendasData();
      },
      handleSelectChange(selection) {
        this.selectRows = selection;
      },
      handleUpdateAgenda(rowData) {
        this.agendaForm.agendaId = rowData.agendaId;
        this.agendaForm.title = rowData.title;
        this.agendaForm.nextTime = rowData.nextTime;
        this.agendaForm.quantity = rowData.quantity;
        this.agendaForm.unit = rowData.unit;
        this.agendaForm.open = rowData.open;
        this.agendaForm.remark = rowData.remark;
        this.showUpdateAgendaForm = true;
      },
      doUpdateAgenda() {
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
              this.showUpdateAgendaForm = false;
              this.getAgendasData();
            }).catch((errorMessage) => {
              this.$message.error(errorMessage);
              this.loading = false;
            });
          } else {
            this.loading = false;
          }
        });
      },
      handleClose() {
        this.loading = true;
        this.closeAgenda({ agendaIds: this.selectIds }).then((updateCount) => {
          if (updateCount) {
            this.$notify({
              message: `成功关闭${updateCount}条日程`,
              type: 'info',
              position: 'bottom-right',
              offset: 40,
            });
            this.getAgendasData();
          } else {
            this.$notify({
              message: '没有日程被关闭',
              type: 'warning',
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
      handleOpen() {
        this.loading = true;
        this.openAgenda({ agendaIds: this.selectIds }).then((updateCount) => {
          if (updateCount) {
            this.$notify({
              message: `成功开启${updateCount}条日程`,
              type: 'info',
              position: 'bottom-right',
              offset: 40,
            });
            this.getAgendasData();
          } else {
            this.$notify({
              message: '没有日程被开启，日程下次时间需要在今天后',
              type: 'warning',
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
      handleDelete() {
        this.loading = true;
        this.deleteAgenda({ agendaIds: this.selectIds }).then((updateCount) => {
          if (updateCount) {
            this.$notify({
              message: `成功删除${updateCount}条日程`,
              type: 'info',
              position: 'bottom-right',
              offset: 40,
            });
            this.getAgendasData();
          } else {
            this.$notify({
              message: '没有日程被删除',
              type: 'warning',
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
      unitFormatter(unit) {
        let formatterRole;
        switch (unit) {
          case 'day': formatterRole = '天'; break;
          case 'week': formatterRole = '周'; break;
          case 'month': formatterRole = '月'; break;
          default: formatterRole = '-';
        }
        return formatterRole;
      },
    },
  };
</script>

<style lang="stylus" scoped>

</style>
