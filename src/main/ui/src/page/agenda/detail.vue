<template>
  <div>
    {{ this.$route.params.agendaId }}
  </div>
</template>

<script>
  import { mapActions } from 'vuex';

  export default {
    name: 'agendaDetail',
    data() {
      return {
        loading: false,
        agendaDetail: null,
      };
    },
    created() {

    },
    methods: {
      ...mapActions(['getAgendaDetail']),
      doGetAgendaDetail() {

      },
    },
  };
</script>

<style scoped>

</style>
