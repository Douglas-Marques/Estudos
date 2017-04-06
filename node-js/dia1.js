const express = require('express');
const bodyParser= require('body-parser');
const MongoClient = require('mongodb').MongoClient
const app = express();

var db

MongoClient.connect('mongodb://EduardoDornelRibas:ribas94015985@ds155160.mlab.com:55160/star-wars-quote', function(err, database) {
  if (err) return console.log(err)
  db = database
  app.listen(3000, function() {
    console.log('Servidor está rodando!');
  });
});

app.use(bodyParser.urlencoded({extended: true}))
// All your handlers here...

app.get('/', function(req, res){
    res.sendFile(__dirname + '/views/index.html');
});

/* mesma coisa que de cima, porém usando arrow function
app.get('/', (req, res) => {
  res.send('Olá mundo')
})
*/ 
app.post('/quotes', function(req, res){
  db.collection('quotes'.save(req.body, function(err, result){
    if(err) return console.log(err);
    
    console.log('saved to database');
    res.redirect('/');
  }));
});

