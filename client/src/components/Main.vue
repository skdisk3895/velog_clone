<template>
  <div>
    <MainTopNav />
    <PostList />
  </div>
</template>

<script>
import MainTopNav from "@/components/MainTopNav.vue";
import PostList from "@/components/PostList.vue";

export default {
    components: {
        MainTopNav,
        PostList,
    }
}
</script>

<style>

</style>