var express = require('express');
var path = require("path");
var mongoose = require('mongoose');
var routes  = require('./routes');

var app = express();

app.set('views', path.join(__dirname, 'views'));
app.use(express.static('public'));
app.use(express.static('files'));

//app.use('/static', express.static(__dirname + '/public'));
app.use(app.router);
app.use(express.static(path.join(__dirname, 'public')));

mongoose.connect('localhost:27017/projeto');
mongoose.Promise = global.Promise;

app.get('/', routes.index);

app.listen(3000, function () {
  console.log('Servidor Rodando!')
});