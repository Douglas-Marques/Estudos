//Abrir requisição para File System
var arquivo = require('fs');
let path = './arquivos/brasil.txt'


//Ler arquivo
arquivo.readFile('./arquivos/clubes.txt', function(err, data){
	if(err){
		throw err;
	}
	console.log(data.toString());
});


arquivo.exists(path, function(resultado){
	if(!resultado){
		arquivo.writeFile(path, 'Grêmio \nInter', function(err){
		if(err)throw err;
		console.log('Arquivo criado com sucesso');
	});		
	}else{
		console.log('Arquivo já existe!');
	}
});