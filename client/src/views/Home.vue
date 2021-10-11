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
  name: "Home",
  components: {
    TopNav,
    Main,
  },
};
</script>

<style scoped>
.home {
  width: 1728px;
  margin-right: auto;
  margin-left: auto;
}

@media (max-width: 1919px) {
  .home {
    width: 1376px;
  }
}

@media (max-width: 1440px) {
  .home {
    width: 1024px;
  }
}

@media (max-width: 1056px) {
  .home {
    width: calc(100% - 2rem);
  }
}
</style>
