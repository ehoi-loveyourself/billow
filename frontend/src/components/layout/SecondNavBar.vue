<template>
  <b-navbar toggleable="lg">
    <b-navbar-nav>
      <br> 
        <b-nav-item>
          <form action="" class="search-bar">
	<input type="search" name="search" placeholder="프로그램명 검색" pattern=".*\S.*" required>
	<button class="search-btn" type="submit">
		<span>Search</span>
	</button>
</form>
          <!-- <img src="@/assets/search.png" alt="Image"/> -->
        </b-nav-item>
        &nbsp;&nbsp;
        <b-nav-item class="profile">
          <img src="@/assets/toystory.png" alt="Image"/>
        </b-nav-item>
    </b-navbar-nav>
  </b-navbar>
</template>
<script>

  </script>
  
<style scoped>
  nav .navbar-nav a.router-link-active {
    color: white;
    font-weight: bold;
  }
  nav .navbar-nav a:hover {
    text-decoration: none;
    color: #E5E5E5;
  }
  .navbar {
    font-size: large;
    color: #a1a1a1;
    right: 3%;
    justify-content: flex-end;
    display: flex;
    flex-grow: 1;
    position: absolute;
    top: 0;
    align-items: center;
  }

  * {
	border: 0;
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}
:root {
	font-size: calc(16px + (24 - 16)*(100vw - 320px)/(1920 - 320));
}
body, button, input {
	font: 1em Hind, sans-serif;
	line-height: 1.5em;
}
body, input {
	color: #141414;
}
body, .search-bar {
	display: flex;
}
body {
	background: #f1f1f1;
	height: 100vh;
}
.search-bar input,
.search-btn, 
.search-btn:before, 
.search-btn:after {
	transition: all 0.25s ease-out;
}
.search-bar input,
.search-btn {
	width: 2em;
	height: 2em;
}
.search-bar input:invalid:not(:focus),
.search-btn {
	cursor: pointer;
}
.search-bar,
.search-bar input:focus,
.search-bar input:valid  {
	width: 100%;
}
.search-bar input:focus,
.search-bar input:not(:focus) + .search-btn:focus {
	outline: transparent;
}
.search-bar {
	margin: auto;
	/* padding: 1.5em; */
	justify-content: right;
	max-width: 30em;
  text-align: right;
}
.search-bar input {
	background: transparent;
	border-radius: 1.5em;
	box-shadow: 0 0 0 0.4em white inset;
	padding: 0.75em;
	transform: translate(0.5em,0.5em) scale(0.5);
	transform-origin: 100% 0;
	-webkit-appearance: none;
	-moz-appearance: none;
	appearance: none;
}
.search-bar input::-webkit-search-decoration {
	-webkit-appearance: none;
}
.search-bar input:focus,
.search-bar input:valid {
	background: #fff;
	border-radius: 0.375em 0 0 0.375em;
	box-shadow: 0 0 0 0.1em #d9d9d9 inset;
	transform: scale(1);
}
.search-btn {
	background: white;
	border-radius: 0 0.75em 0.75em 0 / 0 1.5em 1.5em 0;
	padding: 0.75em;
	position: relative;
	transform: translate(0.25em,0.25em) rotate(45deg) scale(0.25,0.125);
	transform-origin: 0 50%;
}
.search-btn:before, 
.search-btn:after {
	content: "";
	display: block;
	opacity: 0;
	position: absolute;
}
.search-btn:before {
	border-radius: 50%;
	box-shadow: 0 0 0 0.2em #f1f1f1 inset;
	top: 0.25em;
	left: 0.25em;
	width: 1.2em;
	height: 1.2em;
}
.search-btn:after {
	background: #f1f1f1;
	border-radius: 0 0.25em 0.25em 0;
	top: 51%;
	left: 51%;
	width: 0.75em;
	height: 0.25em;
	transform: translate(0.2em,0) rotate(45deg);
	transform-origin: 0 50%;
}
.search-btn span {
	display: inline-block;
	overflow: hidden;
	width: 1px;
	height: 1px;
}

/* Active state */
.search-bar input:focus + .search-btn,
.search-bar input:valid + .search-btn {
	background: white;
	border-radius: 0 0.375em 0.375em 0;
	transform: scale(1);
}
.search-bar input:focus + .search-btn:before, 
.search-bar input:focus + .search-btn:after,
.search-bar input:valid + .search-btn:before, 
.search-bar input:valid + .search-btn:after {
	opacity: 1;
}
.search-bar input:focus + .search-btn:hover,
.search-bar input:valid + .search-btn:hover,
.search-bar input:valid:not(:focus) + .search-btn:focus {
	background: #141414;
}
.search-bar input:focus + .search-btn:active,
.search-bar input:valid + .search-btn:active {
	transform: translateY(1px);
}

@media screen and (prefers-color-scheme: dark) {
	body, input {
		color: #f1f1f1;
	}
	body {
		background: white;
	}
	.search-bar input {
		box-shadow: 0 0 0 0.4em #f1f1f1 inset;
	}
	.search-bar input:focus,
	.search-bar input:valid {
		background: white;
		box-shadow: 0 0 0 0.1em white inset;
	}
	.search-btn {
		background: #f1f1f1;
	}
}
</style>