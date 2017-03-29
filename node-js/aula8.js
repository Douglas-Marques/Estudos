//utilizando o framework express.js

var express = require('express');
var app = express();

app.get('/', function (req, res) {
  res.send('Olá mundo!');
});

app.get('/contato', function(req, res){
	res.send("Página de contato");_
});

app.listen(3000, function () {
  console.log('Servidor Rodando!')
});

