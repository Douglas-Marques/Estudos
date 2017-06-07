var config  = require('./config/config');
var express = require('./config/express');
  
var app = express.init();

app.listen(config.server.port, function () {
    console.log('Servidor rodando!');
});