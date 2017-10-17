var adService = require('../service/ad.service');
var express = require('express');
var controller = {};

controller.conectarAd = conectarAd;
module.exports = controller;

function conectarAd(req, res, next){
  adService.conectarAd(req.body, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  })
}