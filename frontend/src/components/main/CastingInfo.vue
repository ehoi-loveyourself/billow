<template>
    <div style="margin-top: 10px">
        <br />
        <div>
            <div class="flex">
                <b-card v-for="cast in castInfoList" style="
            width: 10%;
            height: 13vw;
            background-color: #1f1f1f;
            text-align: center;
            margin-bottom: 1%;
            margin-right: 1%;
            border-radius: 1vw;
          ">
                    <img :src="cast.castImageUrl" />
                    <div class="actorName">{{ cast.actorName }}</div>
                    <div class="playerName">{{ cast.playName }}</div>
                </b-card>
            </div>
        </div>
    </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    // name: "App",
    name: "Star",
    computed: {
        ...mapState(["castInfoList"]),
    },
    data() {
        return {};
    },
    methods: {},
};
</script>

<style scoped>
.actorName {
    color: #ffffff;
    font-size: 1vw;
}

.playerName {
    color: #a1a1a1;
    font-size: 0.9vw;
}

.flex {
    display: flex;
    /* height: 600px; */
    flex-wrap: wrap;
    align-content: stretch;
}

img {
    width: 6rem;
    margin-bottom: 7%;
    border-radius: 1vw;

}
</style>
