'use strict';

var pkg     = require('./package.json');
var config  = require('./config/config');
var express = require('./config/express');

var app = express();

app.listen(config.server.port, function () {
  console.log('Servidor rodando!');
});
