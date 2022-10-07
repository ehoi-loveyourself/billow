module.exports = {
  transpileDependencies: true,
  lintOnSave: false,
  publicPath: "",

  devServer: {
    proxy: "https://j7b309.p.ssafy.io",
    historyApiFallback: true,
  },
};
