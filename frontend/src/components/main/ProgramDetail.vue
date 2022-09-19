<template>
<header-bar />
<div style="margin-top: 10px; padding-left: 70px; padding-right: 70px;">
    <br/><br/>
    <div class="container">
        <section>
        <div class="column-left"><img src="@/assets/hyori.png"/></div>
        <div class="column-center"></div>
   <div class="column-right">
    <div><span class="title">효리네 민박</span><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<svg width="61" height="24" fill="none" xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink">
                <path fill="url(#a)" d="M0 0h61v24H0z" />
                <defs>
                    <pattern id="a" patternContentUnits="objectBoundingBox" width="1" height="1">
                        <use xlink:href="#b" transform="scale(.0164 .04167)" />
                    </pattern>
                    <image id="b" width="61" height="24"
                        xlink:href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAD0AAAAYCAYAAABJA/VsAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAAAFhSURBVFhHY2RgYPgPxCMKMEHpEQWI9vSeCUpgTC0wkOYR5Wl7A24UTCkYaPOI8nRtgjiUhcomFwy0eQQ9jR56xIYmLjAYzMPraXkJNqwhR27sDBbz4FUWyIB4D0EQk8GOiNACgQcvfjE8fPEbTB+68JXhIBA/BLJBYDCbB/d0HNDAuRUyICbZILnjCcOiHe/B7MFsHjOQ3QASuHjnBzAEGIkKQWxgIdCw5gWvoLzBbR7c0yAAin5yDAYZmAIMRXQwWM1D8TQIkGowKPSKpzyH8jDBYDQPZ9sb1LohZDDIAS4F96A8/GAwmYezypKXYIWycANi1MDAYDIPp6cVgFUENcFgMg+rp7ElG1Bdhw5AFhOTtwabeVg9DWoIwADIMFBhoBpxk0EFiGHFPilgMJoHKshQcF2C+P/fB3TBNDZ5oKUE1SDjQWgehgBYEzZxdDxU1Y0OF40UMOrpkQJGoKcZGABqIX9owqCb9AAAAABJRU5ErkJggg==" />
                </defs>
            </svg></span>&nbsp;&nbsp;<span style="vertical-align:27%">3.0</span></div>
    <div class="box"><span class="box_design">15+</span><span class="box_design">금 오후7:00</span><span class="box_design">드라마</span><span class="box_design">tvN</span></div>
    <div class="genre"><span>리얼리티</span></div>
    <div class="blabla">′이효리, 이상순 부부′ 두 사람이 제주도에서 부부 민박집을 운영하는 프로그램</div>
    <div class="talent">출연&nbsp;&nbsp;&nbsp;&nbsp;이효리, 이상순</div>
   </div>
   </section>
   <br/><br/><br/>
    <!-- <ul class="css-1wh24pi-ContentPageStickyTabs">
        <li>
            <button type="button" class="css-1gh3q9p-StyledTab">REVIEW</button>
        </li>
        <li>
            <button type="button" class="css-1gh3q9p-StyledTab">SCHEDULE</button>
        </li>
        <li>
            <button type="button" class="css-1gh3q9p-StyledTab">TALK</button>
        </li>
        <li>
            <button type="button" class="css-1gh3q9p-StyledTab">RELATED</button>
        </li>
    </ul> -->
    
</div>
<br/><br/><br/><br/><br/>
</div>
</template>

<script>
  import HeaderBar from "@/components/layout/HeaderNavBar.vue";

export default{
  name:"App",
  components:{
    HeaderBar,
  },
}
</script>

<style scoped>
.container{
    color:white;
    margin:0;
}
.column-left{ float: left; width: 17%; }
.column-center{float:inline-block; width:3%}
.column-right{ float: right; width: 80%; }
section{
    align-items: flex-end;
    display: flex;
}
.title{
    font-size: 60px;
    margin-bottom: 10px;
}
.box{
    font-size: 12px;
}
.blabla{
    margin-top: 8px;
    color:white;
    font-family: Noto Sans KR,-apple-system,BlinkMacSystemFont,Roboto,Segoe UI,Oxygen,Ubuntu,Cantarell,Open Sans,Helvetica Neue,sans-serif;
}
.box_design{
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
.genre{
    color:#FFFFFF;
    font-size: 17px;
    font-family: Noto Sans KR,-apple-system,BlinkMacSystemFont,Roboto,Segoe UI,Oxygen,Ubuntu,Cantarell,Open Sans,Helvetica Neue,sans-serif;
    margin-top: 8px;
}
.talent{
    color:white;
    font-family: Noto Sans KR,-apple-system,BlinkMacSystemFont,Roboto,Segoe UI,Oxygen,Ubuntu,Cantarell,Open Sans,Helvetica Neue,sans-serif;
    margin-top: 4px;
}
/* .css-1gh3q9p-StyledTab {
    display: inline-block;
    position: relative;
    white-space: nowrap;
    color: #141414;
    font-size: 15px;
    font-weight: 500;
    letter-spacing: 0px;
    line-height: 20px;
    padding: 14px 20px;
}
ol, ul {
    padding: 0px;
    list-style-type: none;
    margin-block: 0px;
    margin-inline: 0px;
    padding-inline-start: 0px;
}
.css-1wh24pi-ContentPageStickyTabs {
    display: flex;
    -webkit-box-pack: center;
    justify-content: center;
    padding-right: 40px;
    padding-left: 40px;
    position: sticky;
    background: #141414;
    margin: 0px 0px 24px;
}
button, select {
    text-transform: none;
}
button, input {
    overflow: visible;
}
button, input, optgroup, select, textarea {
    font-family: inherit;
    margin: 0;
} */
</style>