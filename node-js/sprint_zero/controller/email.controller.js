var emailService = require('../service/email.service');
var express = require('express');
var controller = {};

controller.sendEmail = sendEmail;
module.exports = controller;

function sendEmail(req, res, next){
  var emailDestinatario = req.body.email;
  var titulo = req.body.titulo;
  var msg = req.body.msg;
  emailService.sendEmail(emailDestinatario, titulo, msg, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  })
}