var requestify = require('requestify');

var Slack = require('node-slack');
var slack = new Slack('https://hooks.slack.com/services/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');

var url = "https://slack.com/api/";

var service = {};

service.enviarMsgSlack = enviarMsgSlack;
module.exports = service;


function enviarMsgSlack(titulo, subtitulo, imagem){
  var texto = preparaMsgNovoLivro(titulo, subtitulo, imagem);
  slack.send({
    text: texto,
    channel: 'C5LL6K5PA',
    username: 'Biblioteca-DB'
  });
 // callback(texto);
  return texto;
}

function preparaMsgNovoLivro(titulo, subtitulo, imagem){
  var texto = imagem + '\nNovo livro:\n' + titulo;
  if(subtitulo){
    return texto + '\n' + subtitulo;
  }
  else{
    return texto;
  } 
}
