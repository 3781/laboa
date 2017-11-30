<template>
  <authenticated-layout>
    welcome to system! {{ this.getUsername }}
    last login at {{ this.getLastLoginTime }}
  </authenticated-layout>
</template>

<script>
  import { mapGetters } from 'vuex';
  import AuthenticatedLayout from './AuthenticatedLayout';

  export default {
    name: 'home',
    components: {
      AuthenticatedLayout,
    },
    computed: {
      ...mapGetters(['getUsername', 'getLastLoginTime']),
    },
  };
</script>
