<template>
  <div
    class="
      jumbotron
      min-vh-100
      text-center
      m-0
      bg-info
      d-flex
      flex-column
      justify-content-center
    "
  >
    <div style="text-align: center; color: white; font-size: 36px">
      <br /><br /><br /><br />
      누구와 함께 있으신가요?<br />
      오늘 기분은 어떠신가요?<br />
      상황에 맞게 TV 프로그램을 추천해드릴게요.
      <br /><br />
    </div>

    <div style="text-align: center">
      <a class="startButton" href="#" role="button" style="border-radius: 15px">
        <router-link
          :to="{ name: 'situationselect' }"
          class="nav-link"
          style="font-size: 20px; padding: 10px; color: black"
        >
          상황별 추천 받기
        </router-link>
      </a>
    </div>

    <br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br />
  </div>
</template>
  
<script>
</script>

<style scoped>
.jumbotron {
  background: url("@/assets/before_login.svg") no-repeat center center fixed;
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
}

.jumbotron-fluid {
  padding-right: 0;
  padding-left: 0;
  border-radius: 0;
}

.startButton {
  background-color: #ffffff;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  opacity: 60%;
}
</style>
  