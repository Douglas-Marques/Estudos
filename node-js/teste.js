var express = require('express');
var app = express();

var html = require('./arquivos/times.html');
var modulo = require('./module/mod3.js');

app.get('/', function(req, res){
	res.send('Página de times!!!');
});

app.listen(3000, function(){
	console.log('Servidor rodando');
})