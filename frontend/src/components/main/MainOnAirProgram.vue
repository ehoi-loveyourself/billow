<template>
  
<h2 style="color:white">지금 방송 중인 프로그램(ON AIR)</h2>

<div class="card" style="color:white">
  <img src="@/assets/image_1.png" alt="Image" style="width:70%">
  <div class="container">
    <h4><b>호버 했을 때 제목 등등</b></h4> 
    <p>카드 형식 사용</p>
  </div>
</div>

<h2 style="color:white">요즘 핫한 프로그램</h2>

<div class="card" style="color:white">
  <img src="@/assets/image_1.png" alt="Image" style="width:70%">
  <div class="container">
    <h4><b>카드 형식</b></h4> 
    <p>카드 형식 사용</p> 
  </div>
</div>
  
</template>


<style>
  .card {
    background-color: blue;
    box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
    transition: 0.3s;
    width: 40%;
  }
  
  .card:hover {
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  }
  
  .container {
    padding: 2px 16px;
  }
  </style>