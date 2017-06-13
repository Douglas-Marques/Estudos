var testeService = require('../service/teste.service');
var express = require('express');
var controller = {};

controller.enviarMsgSlack = enviarMsgSlack;
controller.lerIsbn = lerIsbn;
controller.concetarFirebase = concetarFirebase;
controller.sendEmail = sendEmail;
controller.salvarLivro = salvarLivro;
controller.obterTodosLivros = obterTodosLivros;
module.exports = controller;

function lerIsbn(req, res, next){
  var numeroIsbn = req.params.isbn;
  testeService.lerIsbn(numeroIsbn, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  })
}

function enviarMsgSlack(req, res, next){
  var texto = req.body.texto;
  var icon = req.body.icon_url;
  testeService.enviarMsgSlack(texto, icon, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  });
}

function concetarFirebase(req, res, next){
  var token = req.body.token;
  testeService.concetarFirebase(token, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  })
}

function sendEmail(req, res, next){
  var emailDestinatario = req.body.email;
  var titulo = req.body.titulo;
  var msg = req.body.msg;
  testeService.sendEmail(emailDestinatario, titulo, msg, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  })
}

function salvarLivro(req, res, next){
   testeService.salvarLivro(req.body, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  });
}

function obterTodosLivros(req, res, next){
    testeService.obterTodosLivros(function(response){
      if(response){
        res.json(response);
        res.status(200);
      }
      else{
        res.json('Bad request');
        res.status(400);
      }
    }); 
}