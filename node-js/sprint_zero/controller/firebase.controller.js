var firebaseService = require('../service/firebase.service');
var express = require('express');

var controller = {};

controller.concetarFirebase = concetarFirebase;
module.exports = controller;

function concetarFirebase(req, res, next){
  var token = req.body.token;
  firebaseService.concetarFirebase(token, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  })
}