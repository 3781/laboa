<template>
  <div>
    <div class="block" style="float:right;margin-bottom: 40px">
      <span class="demonstration">跳到</span>
      <el-date-picker size="mini"
        type="month"
        v-model="jumpMonth" value-format="yyyy/MM"
        placeholder="选择月">
      </el-date-picker>
      <span class="demonstration">月</span>
      <el-button type="primary" size="mini" @click="doJump" :disabled="jumpMonth==null">确定</el-button>
    </div>
    <vue-event-calendar title="待办日程" :events="this.toDoList" @month-changed="handleMonthChanged" v-loading.lock="loading">
      <template slot-scope="props">
        <div v-for="(event, index) in props.showEvents" class="event-item">
          <div class="wrapper">
            <h3 class="title">{{index+1}}.
              <router-link tag="a" :to="`/agenda/${event.agendaId}`">{{event.title}}</router-link>
              <el-button type="success" size="mini" :round="true" plain @click="handleSummary(event.agendaSummary)">结项</el-button></h3>
            <p class="time">{{ event.summaryTime}}</p>
          </div>
        </div>
      </template>
    </vue-event-calendar>
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
  import { mapActions } from 'vuex';
  import VueEventCalendar from 'vue-event-calendar';
  import Vue from 'vue';
  import AgendaSummary from './summary';

  Vue.use(VueEventCalendar, { lang: 'en' });

  export default {
    components: {
      AgendaSummary,
    },
    name: 'agendaTodo',
    data() {
      return {
        jumpMonth: null,
        loading: false,
        toDoList: [],
        targetDate: null,
        summaryVisible: false,
        currentSummary: null,
      };
    },
    created() {
      this.getTodoList();
    },
    methods: {
      ...mapActions(['listToDoAgendas']),
      initToday() {
        const date = new Date();
        if (date.getDate() > 9) {
          this.targetDate = `${date.getFullYear()}年${date.getMonth() + 1}月${date.getDate()}`;
        } else {
          this.targetDate = `${date.getFullYear()}年${date.getMonth() + 1}月0${date.getDate()}`;
        }
      },
      getTodoList() {
        if (this.targetDate === null) {
          this.initToday();
        }

        this.loading = true;
        this.listToDoAgendas({ targetDate: this.targetDate.replace('年', '-').replace('月', '-') }).then((info) => {
          this.toDoList = info;
          this.loading = false;
        }).catch((errorMessage) => {
          this.$message.error(errorMessage);
          this.loading = false;
        });
      },
      handleMonthChanged(date) {
        this.targetDate = `${date}01`;
        this.getTodoList();
      },
      doJump() {
        this.$EventCalendar.toDate(`${this.jumpMonth}`);
        this.targetDate = `${this.jumpMonth.replace('/', '年')}月01`;
        this.getTodoList();
      },
      handleSummary(summary) {
        this.currentSummary = summary;
        this.summaryVisible = true;
      },
      summarySuccessCallback() {
        this.summaryVisible = false;
        this.getTodoList();
      },
      doSummary() {
        this.$refs.agendaSummaryForm.doSummary();
      },
    },
  };
</script>

<style lang="stylus" scoped>
  @import '~vue-event-calendar/dist/style.css'
</style>
