<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
body {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  background-color: rgb(248, 249, 250);
  font-family: "Noto Sans KR", sans-serif;
}

#app {
  margin: 0 auto;
}
</style>
