const express = require('express');
const app = express();

app.get('/', function(req, res){
    res.sendFile(__dirname + '/views/index.html');
});

/* mesma coisa que de cima, porém usando arrow function
app.get('/', (req, res) => {
  res.send('Olá mundo')
})
*/ 

app.listen(3000, function() {
  console.log('Servidor está rodando');
});