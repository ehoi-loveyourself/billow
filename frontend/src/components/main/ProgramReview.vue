<template>
    <div style="padding-left: 70px; margin-top: 10px;">
        <br />
        <b-row style="margin-top: 20px;">
            <b-col cols="2">
                <span class="inner">
                    <span>
                        <span v-for="index in 5" :key="index" @click="check(index)">
                            <span v-if="index < score"><img src="@/assets/blue_star_big.png" /></span>
                            <span v-if="index >= score"><img src="@/assets/grey_star_big.png" /></span>
                        </span>
                    </span>
                </span>
            </b-col>
            <b-col cols="9">
                <b-form-input v-model="message" placeholder="리뷰를 작성해주세요." required style="border-color: #A48282;">
                </b-form-input>
            </b-col>
            <b-col cols="1">
                <b-button size="md" type="submit">
                    <span>등록</span>
                </b-button>
            </b-col>
        </b-row>
        <br />
        <section>
            <article class="review_set">
                <article class="reviews">
                    <h2>걷지말고뛰어라&nbsp;&nbsp;
                        <span class="wrap-star">
                            <span class="star-rating">
                                <span style="width:100%"></span>
                            </span>
                        </span>

                    </h2>
                    <p>너무 재밌어요!</p>
                </article>
                <article class="reviews">
                    <h2>뛰지말고누워라&nbsp;&nbsp;<span class="wrap-star">
                            <span class="star-rating">
                                <span style="width:40%"></span>
                            </span>
                        </span>
                    </h2>
                    <p>노잼..</p>
                </article>
                <article class="reviews">
                    <h2>눕지마라&nbsp;&nbsp;
                        <span class="wrap-star">
                            <span class="star-rating">
                                <span style="width:80%"></span>
                            </span>
                        </span>
                    </h2>
                    <p>재밌네요</p>
                </article>
            </article>
        </section>
        <br />
    </div>
</template>
    
<script>
export default {
    name: "Star",
    data() {
        return {
            score: 0,
        };
    },
    methods: {
        check(index) {
            this.score = index + 1;
        },
    },
};
</script>
    
<style scoped>
.reviews {
    background: left/contain content-box border-box no-repeat url('@/assets/toystory.png') #141414;
    margin-bottom: 1.5rem;
}

.reviews>h2 {
    padding-left: 4.5rem;
    font-weight: 600;
    font-size: 0.9rem;
    margin: 0;
}

.reviews>p {
    padding-left: 4.5rem;
    font-size: 0.9rem;
    color: #84868D;
}

.star-rating {
    width: 100px;
}

.star-rating,
.star-rating span {
    display: inline-block;
    height: 18px;
    overflow: hidden;
    background: url(@/assets/star_1.png)no-repeat;
}

.star-rating span {
    background-position: left bottom;
    line-height: 0;
    vertical-align: top;
}
</style>