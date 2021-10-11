<template>
  <div id="authModal">
    <div class="image">
      <div class="image__logo">
        <div>
          <img src="https://static.velog.io/static/media/undraw_joyride_hnno.fae6b95e.svg" alt />
        </div>
        <h1>환영합니다!</h1>
      </div>
    </div>
    <div class="login_form">
      <div class="close">
        <i class="fas fa-times"></i>
      </div>
      <h3 class="title">로그인</h3>
      <input class="email" type="text" placeholder="이메일을 입력하세요" />
      <br />
      <input class="password" type="password" placeholder="비밀번호를 입력하세요" />
      <button class="login_btn">로그인</button>
      <div class="go__signup">
        <span>아직 회원이 아니신가요?</span>
        <a href="#">회원가입</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
#authModal {
  width: 620px;
  height: 480px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  box-shadow: rgb(0 0 0 / 0.2) 0px 2px 10px 0px;
  border-radius: 10px;
  display: flex;
  z-index: 2;
}

.image {
  width: 200px;
  padding: 1.5rem;
  background-color: rgb(241, 243, 245);
  display: flex;
  align-items: center;
  justify-content: center;
}

.image__logo {
  text-align: center;
  width: 100%;
}

.image__logo img {
  width: 100%;
  height: auto;
}

.login_form {
  flex-grow: 1;
  padding: 2rem;
  background-color: white;
  display: flex;
  flex-direction: column;
}

.close {
  text-align: right;
  margin-bottom: 50px;
}

.close i {
  font-size: 1.4rem;
  color: rgb(134, 142, 150);
  cursor: pointer;
}

.login_form .title {
  font-size: 1.5rem;
  letter-spacing: 2px;
}

.login_form input {
  padding: 5px 10px;
  width: 280px;
  height: 30px;
  border: 1px solid rgb(201, 199, 199);
}

.login_btn {
  margin-top: 20px;
  width: 302px;
  height: 40px;
  background-color: rgb(18, 184, 134);
  color: white;
  font-size: 1.2rem;
  font-weight: bold;
  letter-spacing: 2px;
  border: none;
  cursor: pointer;
}

.go__signup {
  margin-right: 20px;
  margin-bottom: 20px;
  position: absolute;
  bottom: 0;
  right: 0;
}

.go__signup span {
  margin-right: 6px;
}

.go__signup a {
  font-weight: bold;
  text-decoration: none;
  color: rgb(18, 184, 134);
}

.go__signup a:hover {
  text-decoration: underline;
}
</style>
