<template>
    <b-navbar toggleable="lg">
      <b-navbar-nav>
        <br> 
          <b-nav-item>
            <img src="@/assets/search.png" alt="Image"/>
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
  </style>