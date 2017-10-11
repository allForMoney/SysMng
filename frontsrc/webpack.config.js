const webpack = require('atool-build/lib/webpack');
const pkg = require('./package.json');

// const ExtractTextPlugin = require("extract-text-webpack-plugin");
const path = require('path');
const glob = require('glob');

module.exports = function(webpackConfig, env) {
  const isDev = (env === 'development');

  webpackConfig.babel.plugins.push('transform-runtime');

  if (isDev) {
    // webpackConfig.devtool = 'cheap-eval-source-map';
    webpackConfig.devtool = 'cheap-module-eval-source-map';
    webpackConfig.babel.plugins.push('dva-hmr');
  } else {
    webpackConfig.babel.plugins.push('dev-expression');
  }

  // Don't extract common.js and common.css
  webpackConfig.plugins = webpackConfig.plugins.filter((plugin) => {
    return !(plugin instanceof webpack.optimize.CommonsChunkPlugin);
  });

  webpackConfig.babel.plugins.push(['import', {
    libraryName: 'antd',
    style: true,
  }]);

  // 添加打包时间戳
  webpackConfig.plugins.push(
    // eslint-disable-next-line
    new webpack.BannerPlugin(`/*! ${pkg.name} | v${pkg.version} | ${new Date().toString()} */`, {
      raw: true,
      entryOnly: true,
    })
  );

  // Support CSS Modules
  // https://github.com/css-modules/css-modules/pull/65
  // Parse all less files as css module.
  webpackConfig.module.loaders.forEach((loader, index) => {
    if (typeof loader.test === 'function' && loader.test.toString()
        .indexOf('\\.less$') > -1) {
      loader.include = /node_modules/;
      loader.test = /\.less$/;
    }

    if (loader.test.toString() === '/\\.module\\.less$/') {
      const localIdentName = isDev ?
        'localIdentName=[name]_[local]_[hash:base64:5]' :
        'localIdentName=lf__[hash:base64:5]';
      loader.exclude = /node_modules/;
      loader.test = /\.less$/;
      loader.loader = loader.loader.replace(
        'localIdentName=[local]___[hash:base64:5]', localIdentName);
    }

    if (typeof loader.test === 'function' && loader.test.toString()
        .indexOf('\\.css$') > -1) {
      loader.include = /node_modules/;
      loader.test = /\.css$/;
    }

    if (loader.test.toString() === '/\\.module\\.css$/') {
      loader.exclude = /node_modules/;
      loader.test = /\.css$/;
    }
  });
  webpackConfig.module.loaders.push({
    test: /\.(png|jpg)$/,
    loader: 'url-loader?limit=8192&name=images/[hash:8].[name].[ext]'
  });


  // if (!isDev) {
  //   webpackConfig.externals = {
  //     react: 'React',
  //     'react-dom': 'ReactDOM',
  //   };
  // }

  webpackConfig.externals = webpackConfig.externals || {};

  // 其他全局库
  // webpackConfig.externals.zepto = 'Zepto';

  // 自动注入 src 下目录的入口文件
  const files = glob.sync('./src/*-{index,entry}.js');

  const newEntries = files.reduce((cache, file) => {
    // console.log('--->', file, path.basename(file, '.js'));
    const name = file.replace('./src/', '').replace('-entry.js', '');
    cache[name] = file;
    return cache;
  }, {});

  webpackConfig.entry = Object.assign({}, webpackConfig.entry, newEntries);

   webpackConfig.output.path = path.join(__dirname, '../src/main/resources/static');

  return webpackConfig;
};
