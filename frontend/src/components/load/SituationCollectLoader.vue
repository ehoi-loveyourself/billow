<template>
    <div class="page-loader" v-if="!isloaded">
        <div class="cube"></div>
        <div class="cube"></div>
        <div class="cube"></div>
        <div class="cube"></div>
    </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
$colors: #316b83, #6d8299, #8ca1a5, #d5bfbf;

// -----------------------------------------------------
.page-loader {
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background-color: #333;
    z-index: 999;
}

// -----------------------------------------------------
.cube {
    width: 50px;
    height: 50px;
    margin-right: 20px;

    @for $i from 1 through length($colors) {
        &:nth-child(#{$i}) {
            background-color: nth($colors, $i);
        }
    }

    &:first-child {
        animation: left 1s infinite;
    }

    &:last-child {
        animation: right 1s infinite .5s;
    }
}

// -----------------------------------------------------
@keyframes left {
    40% {
        transform: translateX(-60px);
    }

    50% {
        transform: translateX(0);
    }
}

@keyframes right {
    40% {
        transform: translateX(60px);
    }

    50% {
        transform: translateX(0);
    }
}
</style>