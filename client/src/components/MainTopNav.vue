<template>
  <nav id="mainNavTop">
    <div class="sort">
      <div class="favorite clicked">
        <div class="favorite__icon">
          <i class="fas fa-thumbs-up"></i>
        </div>
        <p>트랜딩</p>
      </div>
      <div class="new">
        <div class="new__icon">
          <i class="far fa-clock"></i>
        </div>
        <p>최신</p>
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
  margin-bottom: 40px;
}

.sort {
  display: flex;
  color: rgb(134, 142, 150);
}

.sort p {
  font-size: 1.2em;
}

.favorite, .new {
  width: 7rem;
  height: 45px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  cursor: pointer;
}

.favorite.clicked, .new.clicked {
  color: black;
  border-bottom: 2px solid black;
}

.favorite__icon, .new__icon {
  margin-right: 10px;
  font-size: 1.2em;
}
</style>