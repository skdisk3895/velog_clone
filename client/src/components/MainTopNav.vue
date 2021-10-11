<template>
  <nav id="mainNavTop">
    <div class="sort">
      <div class="favorite">
        <div class="favorite__icon">
          <i class="fas fa-thumbs-up"></i>
        </div>
        <p>트랜딩</p>
      </div>
      <div>
        <p class="new">최신</p>
      </div>
    </div>
  </nav>
</template>

<script>
export default {

}
</script>

<style>
#mainNavTop {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.sort {
  display: flex;
  color: rgb(134, 142, 150);
}

.sort p {
  padding: 10px;
  font-size: 1.2em;
  cursor: pointer;
}

.sort p.clicked {
  border-bottom: 2px solid black;
}

.favorite {
  display: flex;
  align-items: center;
  position: relative;
}

.favorite__icon {
  width: 7px;
}
</style>