<template>
  <nav id="nav">
    <div>
      <a class="logo" href="#">velog</a>
    </div>
    <div>
      <i class="search fas fa-search"></i>
      <button class="login__btn">로그인</button>
    </div>
  </nav>
</template>

<script>
export default {

}
</script>

<style>
#nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 4rem;
  margin-bottom: 30px;
}

.logo {
  width: 71px;
  height: 24px;
  font-family: "Fira Mono", monospace;
  font-size: 1.6rem;
  font-weight: 500;
  color: rgb(52, 58, 64);
  text-decoration: none;
}

.search {
  margin-right: 30px;
  font-size: 20px;
  cursor: pointer;
}

.login__btn {
  width: 80px;
  height: 35px;
  padding: 5px;
  background-color: rgb(52, 58, 64);
  border: none;
  border-radius: 30px;
  color: white;
  font-weight: bold;
  font-size: 15px;
  cursor: pointer;
}
</style>