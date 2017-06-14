var nodemailer = require('nodemailer');

var userEmail = "testedbteste@gmail.com";
var transporte = nodemailer.createTransport({
  service: 'Gmail', // vamos usar o Gmail
  auth: {
    user: userEmail, // Basta dizer qual o nosso usuário
    pass: 'testedbteste123' // e a senha da nossa conta
  }
});

var service = {};

service.sendEmail = sendEmail;
module.exports = service;

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