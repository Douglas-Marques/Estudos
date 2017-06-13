var Livros = require('../model/livros.schema');
var requestify = require('requestify');
var isbn = require('node-isbn');
var nodemailer = require('nodemailer');
var FCM = require('fcm-node');

var serverKey = 'AAAAtXA-06c:APA91bFWbFxd1_jYJDQWeD-eWpEUNFYKXYqsvCdi-ccRUDpn6ANVWeESiyLyEUpfeaT0_3rvMUWYgrvqLhxcief8d5e9aujGSP2aPtf9hplxAyR3KCleen2kuV-lnkFkDolRPIyp4SWK'; //put your server key here
var fcm = new FCM(serverKey);

var userEmail = "testedbteste@gmail.com";
var transporte = nodemailer.createTransport({
  service: 'Gmail', // vamos usar o Gmail
  auth: {
    user: userEmail, // Basta dizer qual o nosso usuário
    pass: 'testedbteste123' // e a senha da nossa conta
  }
});

var url = "https://slack.com/api/";

var service = {};

service.enviarMsgSlack = enviarMsgSlack;
service.lerIsbn = lerIsbn;
service.concetarFirebase = concetarFirebase;
service.sendEmail = sendEmail;
service.salvarLivro = salvarLivro;
service.obterTodosLivros = obterTodosLivros;
module.exports = service;

function lerIsbn(numeroIsbn, callback){
  isbn.resolve(numeroIsbn, function (err, book) {
    if (err) {
      callback('Book not found', err);
    } else {
      var livro = prepararLivro(book, numeroIsbn);
      callback(livro);
    }
  });
}

function sendEmail(emailDestinatario, titulo, msg, callback){
  var email = {
    from: userEmail, // Quem enviou este e-mail
    to: emailDestinatario, // Quem receberá
    subject: titulo,  // Um assunto bacana :-)
    html: msg // O conteúdo do e-mail
  };
  transporte.sendMail(email, function(err, info){
    if(err){
      callback('Book not found', err);
    }else{
      callback(info)
      console.log('Email enviado! Leia as informações adicionais: ', info);
    }
  });
}

function enviarMsgSlack(texto, icon, callback){
  requestify.request(url + "chat.postMessage", {
    method: 'POST',
    body: {
      token: 'xoxp-62107491250-177744055633-194375438240-254e324d79410f1dd0b028769dd0a518',
      channel: 'C5LL6K5PA',
      text: texto,
      icon_url: icon
    },
    dataType: 'form-url-encoded'
  })
  .then(function(response) {
    // get the response body
    //callback(response.getBody().message.text);
    callback(response.getBody());
    // get the response headers
    //  response.getHeaders();

    // get specific response header
    //  response.getHeader('Accept');

    // get the code
    // response.getCode();

    // Get the response raw body
    //  response.body;
  });
}

function concetarFirebase(tokenAndroid, callback){
  console.log(tokenAndroid);

  var message = { //this may vary according to the message type (single recipient, multicast, topic, et cetera)
    to: tokenAndroid,

    notification: {
      title: 'Titulo',
      body: 'Primeira notificação'
    }
  };

  fcm.send(message, function(err, response){
    if (err) {
      callback("Algo de errado não está certo");
    } else {
      callback("Sucesso obtendo a resposta: ", response);
    }
  });
}

function salvarLivro(livro, callback){
    var newLivro = new Livros({
                  'titulo': livro.titulo,
                  'subtitulo': livro.subtitulo,
                  'autores': livro.autores,
                  'descricao': livro.descricao,
                  'icone': livro.icone,
                  'idioma': livro.idioma,
                  'avaliacao': livro.avaliacao,
                  'qtdPaginas': livro.qtdPaginas,
                  'categorias': livro.categorias,
                  'isbn': livro.isbn
    });
    newLivro.save(function(err, response){
      if(err){
        callback("Erro ao salvar livro");
      }
      else{
        callback("Livro cadastrado com sucesso");
      }       
  });
}

function obterTodosLivros(callback){
  Livros.find({}, function(err, livros){
    if(err){
      callback (err);
    }
    else if(livros.length === 0){
      callback('Nenhum livro encontrado');      
    }
    callback(livros);
  })
}

function prepararLivro(book, numeroIsbn){
  var livro = { 
                  'titulo': book.title,
                  'subtitulo': book.subtitle,
                  'autores': book.authors,
                  'descricao': book.description,
                  'icone': book.imageLinks.thumbnail,
                  'idioma': book.language,
                  'avaliacao': book.averageRating,
                  'qtdPaginas': book.pageCount,
                  'categorias': book.categories,
                  'isbn': numeroIsbn
            }
  return livro;               
}