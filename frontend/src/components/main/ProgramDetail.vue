<template>
    <b-row>
        <b-col cols="8">
            <header-bar />
        </b-col>
        <b-col cols="4">
            <SecondBar />
        </b-col>
    </b-row>
    <div style="padding-left: 70px; padding-right: 70px;">
        <br />
        <div class="container">
            <section>
                <div class="column-1">
                    <div style="padding-bottom: 4%;">
                        <button @click="addToFavorites" v-show="!isFavorite"
                            style="border:none; background-color: #141414;"><img
                                src="@/assets/white_heart.png" /></button>
                        <button @click="deleteFromFavorites" v-show="isFavorite"
                            style="background:none; border:none;"><img src="@/assets/red_heart.png" /></button>
                    </div>
                    <img src="@/assets/hyori.png" />
                </div>
                <div class="column-2"></div>
                <div class="column-3">

                    <span class="title">효리네 민박</span><span>&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="wrap-star">
                            <span class="star-rating">
                                <span style="width:76%"></span>
                            </span>
                        </span>
                    </span>&nbsp;&nbsp;<span>7.6</span>&nbsp;&nbsp;&nbsp;

                    <div class="box"><span class="box_design">15+</span><span class="box_design">금 오후7:00</span><span
                            class="box_design">예능</span><span class="box_design">tvN</span></div>
                    <div class="genre"><span>#리얼리티</span></div>
                    <div class="blabla">′이효리, 이상순 부부′ 두 사람이 제주도에서 부부 민박집을 운영하는 프로그램</div>
                    <div class="talent"><span class="talent_label">출연</span>&nbsp;&nbsp;&nbsp;&nbsp;이효리, 이상순</div>
                </div>
                <div class="column-4">
                    <b-card style="background-color: #ffffff; color:#141414; width:fit-content;">
                        <div style="padding-bottom:6%">이 프로그램을 보셨다면?</div><span class="inner">
                            <span>
                                <span v-for="index in 5" :key="index" @click="check(index)">
                                    <span v-if="index < score"><img src="@/assets/blue_star_big.png" /></span>
                                    <span v-if="index >= score"><img src="@/assets/grey_star_big.png" /></span>
                                </span>
                            </span>
                        </span>
                    </b-card>
                </div>
            </section>
            <br /><br /><br />

            <b-tabs id="tabs" style="align-items:center">
                <b-tab title="SCHEDULE" active>
                    <Schedule />
                </b-tab>
                <b-tab title="TALK">
                    <OnAirTalk />
                </b-tab>
                <b-tab title="REVIEW">
                    <Review />
                </b-tab>
                <!-- <b-tab title="RELATED">
                    <LikedProgram />
                </b-tab> -->
            </b-tabs>
        </div>
        <br /><br /><br />
    </div>
</template>

<script>
import HeaderBar from "@/components/layout/HeaderNavBar.vue";
import SecondBar from "@/components/layout/SecondNavBar.vue";
import LikedProgram from "@/components/like/LikedProgram.vue"
import Review from "@/components/main/ProgramReview.vue"
import OnAirTalk from "@/components/main/OnAirTalk.vue"
import Schedule from "@/components/main/ProgramSchedule.vue"

export default {
    // name: "App",
    name: "Star",
    data() {
        return {
            isFavorite: false,
            score: 0,
        }
    },
    methods: {
        check(index) {
            this.score = index + 1;
        },
        addToFavorites() {
            this.isFavorite = true;
        },
        deleteFromFavorites() {
            this.isFavorite = false;
        }
    },
    components: {
        HeaderBar,
        SecondBar,
        LikedProgram,
        Review,
        OnAirTalk,
        Schedule
    },
}
</script>

<style scoped>
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

.container {
    color: white;
    margin: 0;
    padding: 0
}

.column-1 {
    float: left;
    width: 17%;
}

.column-2 {
    float: inline-block;
    width: 3%
}

.column-3 {
    float: right;
    width: 63%;
}

.column-4 {
    float: center;
    width: 17%;
}

section {
    align-items: flex-end;
    display: flex;
}

.title {
    font-size: 60px;
    margin-bottom: 10px;
}

.box {
    font-size: 12px;
}

.blabla {
    margin-top: 8px;
    color: white;
    font-family: Noto Sans KR, -apple-system, BlinkMacSystemFont, Roboto, Segoe UI, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;
}

.box_design {
    display: inline-block;
    background: rgb(46, 47, 49);
    color: rgb(255, 255, 255);
    font-family: "Watcha Sans", Roboto, "Noto Sans KR", "Apple SD Gothic Neo", "Nanum Gothic", "Malgun Gothic", sans-serif;
    font-size: 12px;
    font-weight: 900;
    vertical-align: top;
    line-height: 18px;
    height: 20px;
    padding: 1px 5px;
    border-radius: 3px;
    margin: 0px 10px 0px 0px;
}

.genre {
    color: #a1a1a1;
    font-size: 17px;
    font-family: Noto Sans KR, -apple-system, BlinkMacSystemFont, Roboto, Segoe UI, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;
    margin-top: 8px;
}

.talent {
    color: white;
    font-family: Noto Sans KR, -apple-system, BlinkMacSystemFont, Roboto, Segoe UI, Oxygen, Ubuntu, Cantarell, Open Sans, Helvetica Neue, sans-serif;
    margin-top: 4px;
}

.talent_label {
    color: #a2a2a2;
}

.favoriting {
    display: inline-block
}

.favorite__heart {
    display: inline-block;
    padding: 3px;
    vertical-align: middle;
    line-height: 1;
    font-size: 16px;
    color: #ABABAB;
    cursor: pointer;
    -webkit-transition: color .2s ease-out;
    transition: color .2s ease-out;
}

.favorite__heart.is-disabled:hover {
    cursor: default;
}

.favorite__checkbox {
    position: absolute;
    overflow: hidden;
    clip: rect(0 0 0 0);
    height: 1px;
    width: 1px;
    margin: -1px;
    padding: 0;
    border: 0;
}

.favorite__heart__selected {
    color: #df470b;
}
</style>

<style>
#tabs .nav-link {
    color: #a1a1a1;
    background: none;
    border: none;
}

#tabs .nav-tabs .nav-link.active,
.nav-tabs .nav-item.show .nav-link {
    color: #ffffff;
}
</style>
