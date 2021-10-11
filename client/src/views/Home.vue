<template>
  <div class="home">
    <TopNav />
    <Main />
  </div>
</template>

<script>
import TopNav from "@/components/TopNav.vue";
import Main from "@/components/Main.vue";

export default {
  name: 'Home',
  components: {
    TopNav,
    Main,
  }
}
</script>

<style scoped>
.home {
  width: 1376px;
  margin-right: auto;
  margin-left: auto;
}
</style>
