var http = require('http');
var map = require('through2-map');

var porta = process.argv[2];

var server = http.createServer(function (req, res){
    
    if (req.method !== 'POST'){
      console.log('Não é um post');
    }
    else{
        req.pipe(map(function(chunk){
            return chunk.toString().toUpperCase()
        })).pipe(res);
    }
});

server.listen(porta);