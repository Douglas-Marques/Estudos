var http = require('http');
var url = require('url');
var porta = process.argv[2];

function converterData(tempo) {
  // converter data para ISO
  return {
    "hour": tempo.getHours(),
    "minute": tempo.getMinutes(),
    "second": tempo.getSeconds()
  };
}

function unixtime(tempo) {
  // converter para unix time
  return {
    "unixtime": tempo.getTime()
  };
}


var server = http.createServer(function(req, res){
  var urlParseada = url.parse(req.url, true);
  var tempo = new Date(urlParseada.query.iso);
  var resultado;
  if (req.url.search('parsetime') !== -1) {
    resultado = converterData(tempo);
  } else if (req.url.search('unixtime') !== -1) {
    resultado = unixtime(tempo);
  }

  if (resultado) {
    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.end(JSON.stringify(resultado));
  } else {
    res.end();
  }
});

server.listen(porta);