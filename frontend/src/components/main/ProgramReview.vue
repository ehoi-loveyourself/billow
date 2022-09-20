<template>
<div style="padding-left: 70px; margin-top: 10px;">
    <br/>
    <div style="text-align:center">
    <svg width="61" height="24" fill="none" xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink">
            <path fill="url(#a)" d="M0 0h61v24H0z" />
            <defs>
                <pattern id="a" patternContentUnits="objectBoundingBox" width="1" height="1">
                    <use xlink:href="#b" transform="scale(.0164 .04167)" />
                </pattern>
                <image id="b" width="61" height="24"
                    xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD0AAAAYCAYAAABJA/VsAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFhSURBVFhHY2RgYPgPxCMKMEHpEQWI9vSeCUpgTC0wkOYR5Wl7A24UTCkYaPOI8nRtgjiUhcomFwy0eQQ9jR56xIYmLjAYzMPraXkJNqwhR27sDBbz4FUWyIB4D0EQk8GOiNACgQcvfjE8fPEbTB+68JXhIBA/BLJBYDCbB/d0HNDAuRUyICbZILnjCcOiHe/B7MFsHjOQ3QASuHjnBzAEGIkKQWxgIdCw5gWvoLzBbR7c0yAAin5yDAYZmAIMRXQwWM1D8TQIkGowKPSKpzyH8jDBYDQPZ9sb1LohZDDIAS4F96A8/GAwmYezypKXYIWycANi1MDAYDIPp6cVgFUENcFgMg+rp7ElG1Bdhw5AFhOTtwabeVg9DWoIwADIMFBhoBpxk0EFiGHFPilgMJoHKshQcF2C+P/fB3TBNDZ5oKUE1SDjQWgehgBYEzZxdDxU1Y0OF40UMOrpkQJGoKcZGABqIX9owqCb9AAAAABJRU5ErkJggg==" />
            </defs>
        </svg>
    </div>
    <b-row style="margin-top: 20px;">
        <!-- <b-col cols="1"><div></div></b-col> -->
      <b-col cols="11">
        <b-form-input v-model="message" placeholder="리뷰를 작성해주세요." required style="border-color: #A48282;">
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
        <!-- <ul>
            <li> -->
        <!-- <b-avatar class="avatar" size="3rem" variant="info" src="https://placekitten.com/300/300"></b-avatar> -->
        <article class="review_set">
    <article class="reviews">
        <h2>걷지말고뛰어라&nbsp;&nbsp;<svg width="61" height="24" fill="none" xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink">
            <path fill="url(#a)" d="M0 0h61v24H0z" />
            <defs>
                <pattern id="a" patternContentUnits="objectBoundingBox" width="1" height="1">
                    <use xlink:href="#b" transform="scale(.0164 .04167)" />
                </pattern>
                <image id="b" width="61" height="24"
                    xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD0AAAAYCAYAAABJA/VsAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFhSURBVFhHY2RgYPgPxCMKMEHpEQWI9vSeCUpgTC0wkOYR5Wl7A24UTCkYaPOI8nRtgjiUhcomFwy0eQQ9jR56xIYmLjAYzMPraXkJNqwhR27sDBbz4FUWyIB4D0EQk8GOiNACgQcvfjE8fPEbTB+68JXhIBA/BLJBYDCbB/d0HNDAuRUyICbZILnjCcOiHe/B7MFsHjOQ3QASuHjnBzAEGIkKQWxgIdCw5gWvoLzBbR7c0yAAin5yDAYZmAIMRXQwWM1D8TQIkGowKPSKpzyH8jDBYDQPZ9sb1LohZDDIAS4F96A8/GAwmYezypKXYIWycANi1MDAYDIPp6cVgFUENcFgMg+rp7ElG1Bdhw5AFhOTtwabeVg9DWoIwADIMFBhoBpxk0EFiGHFPilgMJoHKshQcF2C+P/fB3TBNDZ5oKUE1SDjQWgehgBYEzZxdDxU1Y0OF40UMOrpkQJGoKcZGABqIX9owqCb9AAAAABJRU5ErkJggg==" />
            </defs>
        </svg></h2>
        <p>너무 재밌어요!</p>
    </article>
    <article class="reviews">
        <h2>뛰지말고누워라&nbsp;&nbsp;<svg width="61" height="24" fill="none" xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink">
            <path fill="url(#a)" d="M0 0h61v24H0z" />
            <defs>
                <pattern id="a" patternContentUnits="objectBoundingBox" width="1" height="1">
                    <use xlink:href="#b" transform="scale(.0164 .04167)" />
                </pattern>
                <image id="b" width="61" height="24"
                    xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD0AAAAYCAYAAABJA/VsAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFhSURBVFhHY2RgYPgPxCMKMEHpEQWI9vSeCUpgTC0wkOYR5Wl7A24UTCkYaPOI8nRtgjiUhcomFwy0eQQ9jR56xIYmLjAYzMPraXkJNqwhR27sDBbz4FUWyIB4D0EQk8GOiNACgQcvfjE8fPEbTB+68JXhIBA/BLJBYDCbB/d0HNDAuRUyICbZILnjCcOiHe/B7MFsHjOQ3QASuHjnBzAEGIkKQWxgIdCw5gWvoLzBbR7c0yAAin5yDAYZmAIMRXQwWM1D8TQIkGowKPSKpzyH8jDBYDQPZ9sb1LohZDDIAS4F96A8/GAwmYezypKXYIWycANi1MDAYDIPp6cVgFUENcFgMg+rp7ElG1Bdhw5AFhOTtwabeVg9DWoIwADIMFBhoBpxk0EFiGHFPilgMJoHKshQcF2C+P/fB3TBNDZ5oKUE1SDjQWgehgBYEzZxdDxU1Y0OF40UMOrpkQJGoKcZGABqIX9owqCb9AAAAABJRU5ErkJggg==" />
            </defs>
        </svg></h2>
        <p>노잼..</p>
    </article>
    <article class="reviews">
        <h2>눕지마라&nbsp;&nbsp;<svg width="61" height="24" fill="none" xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink">
            <path fill="url(#a)" d="M0 0h61v24H0z" />
            <defs>
                <pattern id="a" patternContentUnits="objectBoundingBox" width="1" height="1">
                    <use xlink:href="#b" transform="scale(.0164 .04167)" />
                </pattern>
                <image id="b" width="61" height="24"
                    xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD0AAAAYCAYAAABJA/VsAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFhSURBVFhHY2RgYPgPxCMKMEHpEQWI9vSeCUpgTC0wkOYR5Wl7A24UTCkYaPOI8nRtgjiUhcomFwy0eQQ9jR56xIYmLjAYzMPraXkJNqwhR27sDBbz4FUWyIB4D0EQk8GOiNACgQcvfjE8fPEbTB+68JXhIBA/BLJBYDCbB/d0HNDAuRUyICbZILnjCcOiHe/B7MFsHjOQ3QASuHjnBzAEGIkKQWxgIdCw5gWvoLzBbR7c0yAAin5yDAYZmAIMRXQwWM1D8TQIkGowKPSKpzyH8jDBYDQPZ9sb1LohZDDIAS4F96A8/GAwmYezypKXYIWycANi1MDAYDIPp6cVgFUENcFgMg+rp7ElG1Bdhw5AFhOTtwabeVg9DWoIwADIMFBhoBpxk0EFiGHFPilgMJoHKshQcF2C+P/fB3TBNDZ5oKUE1SDjQWgehgBYEzZxdDxU1Y0OF40UMOrpkQJGoKcZGABqIX9owqCb9AAAAABJRU5ErkJggg==" />
            </defs>
        </svg></h2>
        <p>재밌네요</p>
    </article>
</article>

<!-- </li>
</ul> -->
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
}

.reviews > h2{
    padding-left: 4.5rem;
    font-weight: 600;
    font-size: 0.9rem;
    margin: 0;
}

.reviews > p {
    padding-left: 4.5rem;
    font-size: 0.9rem;
    color:#84868D;
}
    </style>