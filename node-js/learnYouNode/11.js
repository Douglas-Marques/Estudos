var http = require('http');
var fs = require('fs');

var porta = process.argv[2];
var caminhoDocumento = process.argv[3];

 var server = http.createServer(function (req, res) {  
    var src = fs.createReadStream(caminhoDocumento);
    //O método readable.pipe () retorna uma referência ao fluxo de destino,
    // tornando possível configurar cadeias de fluxos de canal:
    src.pipe(res);
 })  
 server.listen(porta);