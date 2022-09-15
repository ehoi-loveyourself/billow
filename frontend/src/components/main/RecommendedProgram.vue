<template>
<h2 style="color:white">지금 방송 중인 프로그램(ON AIR)</h2>
<!-- <div class="card" style="color:white"> -->
  <img src="@/assets/runningman.png" alt="Image">
  <!-- <div class="container">
    <h4><b>호버 했을 때 제목 등등</b></h4> 
    <p>카드 형식 사용</p>
  </div> -->
<!-- </div> -->

<h2 style="color:white">요즘 핫한 프로그램</h2>
  <img src="@/assets/thatman.png" alt="Image">

  <h2 style="color:white">재현님에게 추천드려요!</h2>
  <img src="@/assets/nangman.png" alt="Image">

  <h2 style="color:white">20대 남자에게 인기 많은 프로그램</h2>
  <img src="@/assets/jugun.png" alt="Image">


<h2 style="color:white">혹시 이병헌님에게 관심이 있으신가요?</h2>
<!-- <div class="card" style="color:white"> -->
  <img src="@/assets/image_2.png" alt="Image">
  <!-- <div class="container">
    <h4><b>카드 형식</b></h4> 
    <p>카드 형식 사용</p> 
  </div> -->
<!-- </div> -->


  
</template>


<style scoped>
  .card {
    background-color: #a1a1a1;
    box-shadow: 0 4px 6px 0 rgba(0, 0, 0, 0.2);
    transition: 0.3s;
    width: 30%;
    padding-left: 70px;
  }
  
  .card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  }
  
  .container {
    padding: 2px 16px;
  }

  h2{
    padding-left: 70px;
    font-weight: 400;
    margin-bottom:10px;
  }

  img{
    padding-left:70px;
    margin-bottom: 20px;
    width:14.2%;
  }
  </style>