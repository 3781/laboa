<template>
  <div class="main-containner">
    <component :is=""></component>
  </div>
</template>

<script>
  import Login from 'Login';
  import Register from 'Register';

  export default {
    name: 'unAuthenticatedLayout',
    components: {
      Login,
      Register,
    }
  }
</script>
