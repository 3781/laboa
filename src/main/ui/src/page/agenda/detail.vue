<template>
  <div class="main-container" v-loading.lock="loading">
    <template v-if="agendaDetail != null">
      <h1 style="text-align: center">
        {{ agendaDetail.title }}
        <el-tag type="primary" size="mini">{{ isPersonal ? '个人':'协作' }}</el-tag>
        <el-tag :type="agendaDetail.open?'success':'danger'" size="mini">{{ agendaDetail.open?'开启':'关闭' }}</el-tag>
      </h1>
      <div style="text-align:center">
        {{ `${agendaDetail.ownerName}  创建于  ${agendaDetail.createTime}` }}
        <template v-if="agendaDetail.updateTime!=null">
          {{ `  更新于  ${agendaDetail.updateTime}` }}
        </template>
      </div>
      <div style="text-align:center; margin-top: 10px" v-if="agendaDetail.open">
        <template v-if="agendaDetail.type!=='once'">
          {{ `每${agendaDetail.quantity}${typeLabel[agendaDetail.unit]}重复` }}
        </template>
        <template v-else>
          {{`${typeLabel[agendaDetail.unit]}`}}
        </template>
        {{ `     下次结项应于${agendaDetail.nextTime}` }}
      </div>
      <div class="content">
        <mavon-editor :value="agendaDetail.remark" :toolbarsFlag="false" :subfield="false" default_open="preview"
                      style="margin-top: 20px;min-height:auto"></mavon-editor>
      </div>
      <div class="summary" style="margin-top: 40px">
        <el-collapse v-if="agendaDetail.itemList!==null">
          <el-collapse-item v-for="item in agendaDetail.itemList" :key="item.itemId">
            <template slot="title">
              规定于{{item.summaryTime}}结项
            </template>
            <el-collapse v-if="item.summaryList!==null" style="margin-left:40px">
              <el-collapse-item v-for="summary in item.summaryList" :key="summary.summaryId">
                <template slot="title">
                  {{ summary.username }}
                  <el-tag size="mini" :type="summaryStatuses[summary.status].tagType">{{ summaryStatuses[summary.status].label }}</el-tag>
                  {{ summary.summaryTime }}
                  <el-button type="success" size="mini" v-if="getUsername === summary.username" @click.stop="handleSummary(summary)"
                  style="padding: 5px 5px">结项</el-button>
                </template>
                <mavon-editor :value="summary.content" :toolbarsFlag="false" :subfield="false" default_open="preview" style="min-height:auto"></mavon-editor>
              </el-collapse-item>
            </el-collapse>
          </el-collapse-item>
        </el-collapse>
      </div>
    </template>
    <el-dialog title="结项" :visible.sync="summaryVisible" :fullscreen="true">
      <agenda-summary ref="agendaSummaryForm" :summary="currentSummary" @onSuccess="summarySuccessCallback"></agenda-summary>
      <span slot="footer" class="dialog-footer">
        <el-button @click="summaryVisible = false">取 消</el-button>
        <el-button type="primary" @click="doSummary">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  import { mapGetters, mapActions } from 'vuex';
  import agendaSummary from './summary';

  export default {
    name: 'agendaDetail',
    components: {
      agendaSummary,
    },
    data() {
      return {
        summaryVisible: false,
        loading: false,
        agendaDetail: null,
        currentSummary: null,
      };
    },
    created() {
      this.doGetAgendaDetail();
    },
    computed: {
      ...mapGetters(['getUsername']),
      isPersonal() {
        return this.agendaDetail.type === 'personal';
      },
      typeLabel() {
        return {
          once: '单次',
          day: '天',
          week: '周',
          month: '月',
        };
      },
      summaryStatuses() {
        return {
          todo: {
            tagType: '',
            label: '待结项',
          },
          finished: {
            tagType: 'success',
            label: '完成',
          },
          unfinished: {
            tagType: 'danger',
            label: '未完成',
          },
        };
      },
    },
    methods: {
      ...mapActions(['getAgendaDetail']),
      doGetAgendaDetail() {
        this.loading = true;
        this.getAgendaDetail(this.$route.params.agendaId).then((info) => {
          this.agendaDetail = info;
          this.loading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
      handleSummary(summary) {
        this.currentSummary = summary;
        this.summaryVisible = true;
      },
      summarySuccessCallback() {
        this.summaryVisible = false;
        this.doGetAgendaDetail();
      },
      doSummary() {
        this.$refs.agendaSummaryForm.doSummary();
      },
    },
  };
</script>

<style scoped>

</style>
