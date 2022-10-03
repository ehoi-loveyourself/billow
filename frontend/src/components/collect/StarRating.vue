<template>
    <img class="poster" :src="random.posterImg" alt="Image" />
    <br />
    <div class="star-rating space-x-4 mx-auto">
        <input type="radio" id="5-stars" name="rating" value="10" v-model="ratings"
            @click="setRating(random.programId)" />
        <label for="5-stars" class="star pr-4">★</label>
        <input type="radio" id="4-stars" name="rating" value="8" v-model="ratings"
            @click="setRating(random.programId)" />
        <label for="4-stars" class="star">★</label>
        <input type="radio" id="3-stars" name="rating" value="6" v-model="ratings"
            @click="setRating(random.programId)" />
        <label for="3-stars" class="star">★</label>
        <input type="radio" id="2-stars" name="rating" value="4" v-model="ratings"
            @click="setRating(random.programId)" />
        <label for="2-stars" class="star">★</label>
        <input type="radio" id="1-star" name="rating" value="2" v-model="ratings"
            @click="setRating(random.programId)" />
        <label for="1-star" class="star">★</label>
    </div>
</template>

<script>
import { reactive } from "@vue/reactivity";
import { mapState, mapActions } from "vuex";
import axios from "axios";
export default {
    data() {
        return {
            ratings: 0
        }
    },
    props: ["random"],
    computed: {
        ...mapState(["programId"]),
    },
    setup() {
        const state = reactive({
            ratingList: [],
        });
        axios.get(`/api/users/rating`).then((res) => {
            //평점 데이터 GET
            console.log(res.data);
            state.ratingList = res.data;
        });
        return { state };
    },
    methods: {
        ...mapActions([
            "sendRating",
        ]),
        setRating(programId) {
            this.ratings = value;
            this.sendRating(programId);
        }
    }
}
</script>

<style>
.star-rating {
    display: flex;
    flex-direction: row-reverse;
    font-size: 1.8rem;
    line-height: 2.5rem;
    justify-content: space-around;
    padding: 0 0.2em;
    text-align: center;
    width: 5em;
}

.star-rating input {
    display: none;
}

.star-rating label {
    -webkit-text-fill-color: transparent;
    /* Will override color (regardless of order) */
    -webkit-text-stroke-width: 0.1vw;
    -webkit-text-stroke-color: #2b2a29;
    cursor: pointer;
}

.star-rating :checked~label {
    -webkit-text-fill-color: gold;
}

.star-rating label:hover,
.star-rating label:hover~label {
    -webkit-text-fill-color: #fff58c;
}


.poster {
    width: 14vw;
    height: 21vw;
    padding-right: 0.5%;
    padding-bottom: 0.5%;
}

#Img {
    text-align: center;
    padding-right: 0.5%;
    padding-bottom: 2%;
    position: relative;
}

.flex {
    display: flex;
    flex-wrap: wrap;
    align-content: stretch;
}
</style>