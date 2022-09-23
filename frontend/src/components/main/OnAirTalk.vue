<template>
<div style="padding-left: 70px; margin-top: 10px;">
    <br/>
    <div>
    3개의 TALK
    </div>
    <b-row style="margin-top: 20px;">
      <b-col cols="11">
        <b-form-input v-model="message" placeholder="주제와 무관한 톡은 삭제될 수 있습니다." required style="border-color: #A48282;">
        </b-form-input>
      </b-col>
      <b-col cols="1">
        <b-button size="md" type="submit">
          <span>등록</span>
        </b-button>
      </b-col>
    </b-row>
    <br/>
    <section>
        <article class="review_set">
    <article class="reviews"><span class="username">
        걷지말고뛰어라&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </span><span class="time">1시간 전</span>
        <p>너무 재밌어요!</p>
    </article>
    <article class="reviews"><span class="username">
        뛰지말고누워라&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </span><span class="time">2시간 전</span>
        <p>노잼..</p>
    </article>
    <article class="reviews"><span class="username">
        눕지마라&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </span><span class="time">3시간 전</span>
        <p>재밌네용</p>
    </article>
</article>
    </section>
  <br/>
</div>
</template>
        
<script>
    
        </script>
        
<style scoped>
    .reviews {
        background: left/contain content-box border-box no-repeat
            url('@/assets/toystory.png') #141414;
            margin-bottom: 1.5rem;
            background-size:auto;
    }
    .reviews > .username{
        padding-left: 3.5rem;
        font-weight: 600;
        font-size: 0.9rem;
        margin: 0;
    }
    .reviews > .time{
        font-size: 0.8rem;
        color:#a1a1a1;
    }
    .reviews > p {
        margin-left: 3.5rem;
        font-size: 0.9rem;
        color:#141414;
        background-color: #E6EDEB; border-radius: 0px 20px 20px 20px; max-width: 300px;
        width: fit-content;
        padding:0.4rem 0.7rem 0.4rem 0.7rem;
    }
        </style>