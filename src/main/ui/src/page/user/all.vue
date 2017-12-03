<template>
  <div class="main-container">
    <el-table :data="users" v-loading="loading" size="small" @sort-change="handleSortChange"
      :defaultSort="{prop:this.userSelectQuery.orderQuery.field, order: this.userSelectQuery.orderQuery.order+'ending'}">
      <el-table-column align="center" label="选择" type="selection">
      </el-table-column>
      <!--<el-table-column align="center" label="用户ID" column-key="userId" prop="userId"-->
                       <!--sortable="custom" :resizable="true">-->
      <!--</el-table-column>-->
      <el-table-column align="center" label="用户名" column-key="username" prop="username"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="姓名" column-key="name" prop="name"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="工号" column-key="employeeNumber" prop="employeeNumber"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <!--<el-table-column align="center" label="qq" column-key="qq" prop="qq"-->
                       <!--sortable="custom" :resizable="true">-->
      <!--</el-table-column>-->
      <!--<el-table-column align="center" label="邮箱" column-key="email" prop="email"-->
                       <!--sortable="custom" :resizable="true">-->
      <!--</el-table-column>-->
      <!--<el-table-column align="center" label="联系方式" column-key="phone" prop="phone"-->
                       <!--sortable="custom" :resizable="true">-->
      <!--</el-table-column>-->
      <el-table-column align="center" label="角色" column-key="role" prop="role"
                       sortable="custom" :resizable="true">
      </el-table-column>
      <el-table-column align="center" label="状态" column-key="status" prop="status"
                       sortable="custom" :resizable="true">
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
        });
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
    },
  };
</script>

<style lang="stylus" scoped>

</style>
