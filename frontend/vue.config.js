// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true,
//   lintOnSave:false,
//   publicPath: '',
  
// //   devServer:{
// //   proxy:{
// //     "/api":{
// //       target: "http://localhost:8009"
// //     }
// //   }
// // }
// devServer:{
//     proxy: "http://localhost:8009"
//   }
// })

module.exports = {
  transpileDependencies: true,
  lintOnSave:false,
  publicPath: '',
  
  devServer:{
    proxy: "http://localhost:8009",
    // proxy: "https://j7b309.p.ssafy.io",
    historyApiFallback: true
  }
}