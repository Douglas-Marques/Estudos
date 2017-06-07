var testeService = require('../service/teste.service');
var express = require('express');
var controller = {};

controller.enviarMsgSlack = enviarMsgSlack;
controller.lerIsbn = lerIsbn;
module.exports = controller;

function lerIsbn(req, res, next){
  var numeroIsbn = req.body.isbn;
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
  testeService.enviarMsgSlack(texto, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }      
  })
}