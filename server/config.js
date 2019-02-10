require('babel-register')({
  presets: ['env', "es2015", "stage-0"],
  plugins: [
    [
      "transform-runtime",
      {
        "polyfill": false,
        "regenerator": true
      }
    ]
  ]
})

module.exports = require('./index.js')