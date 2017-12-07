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
            <h3 class="title">{{index+1}}. {{event.title}} <el-button type="primary" size="mini" :round="true" plain>详情</el-button></h3>
            <p class="time">{{ event.summaryTime}}</p>
          </div>
        </div>
      </template>
    </vue-event-calendar>
  </div>
</template>

<script>
  import { mapActions } from 'vuex';
  import VueEventCalendar from 'vue-event-calendar';
  import Vue from 'vue';

  Vue.use(VueEventCalendar, { lang: 'en' });

  export default {
    name: 'agendaTodo',
    data() {
      return {
        jumpMonth: null,
        loading: false,
        toDoList: [],
        targetDate: null,
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
    },
  };
</script>

<style lang="stylus" scoped>
  @import '~vue-event-calendar/dist/style.css'
</style>
