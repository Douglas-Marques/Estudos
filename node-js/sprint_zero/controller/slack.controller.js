var slackService = require('../service/slack.service');
var express = require('express');
var controller = {};

controller.enviarMsgSlack = enviarMsgSlack;
module.exports = controller;

function enviarMsgSlack(req, res, next){
  var titulo = req.body.titulo;
  var subtitulo = req.body.subtitulo;
  var imagem = req.body.imagem;

  slackService.enviarMsgSlack(titulo, subtitulo, imagem, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  });  
}

//NÃO ESTÁ SENDO UTILIZADO NENHUM MÉTODO DESTE ARQUIVO POR ENQUANTO