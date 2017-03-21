var http = require('http');
let arquivo = require('fs');

var server = http.createServer(function(request, response){
	response.writeHead(200, {"Content-Type": "text/html"});
	response.write("<h1>Olá mundo!</h1>");

	var txt = arquivo.createWriteStream('./arquivos/logs.txt', {flags: 'a'});
	txt.write(request.url+'\n');
	
	response.end();
});

//{flags: 'w'} -> apenas sobrescreve
//{flags: 'a'} -> 

//server.listen(3000); // vai rodar na porta 3000

server.listen(3000, function(){
	console.log('Servidor está rodando');
})