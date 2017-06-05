
var express = require('express');
var timesService = require('./times.service');

module.exports = {
  salvarTime:function(req, res, next){
    var nome = req.body.nome;
    timesService.salvarTime(nome, function(response){
      if(response){
        res.json(response);
      }
      else{
        res.json('Bad request');
        res.status(400);
      }
    })
  },

  obterTodosTimes:function(req, res, next){
    timesService.obterTodosTimes(function(response){
      if(response){
        res.json(response);
        res.status(200);
      }
      else{
        res.json('Bad request');
        res.status(400);
      }
    });
  },

  obterVitoria:function(req, res, next){
    var nome = req.body.nome;
    var golpro = parseInt(req.body.golpro);
    var golcontra = parseInt(req.body.golcontra);
      timesService.obterVitoria(nome, golpro, golcontra, function(response){
        if(response){
            res.json(response);
            res.status(response[0].status);
          }else{
            res.json('Bad request');
            res.status(400);
          }
      });
  },

  obterDerrota:function(req, res, next){
    var nome = req.body.nome;
    var golpro = parseInt(req.body.golpro);
    var golcontra = parseInt(req.body.golcontra);
      timesService.obterDerrota(nome, golpro, golcontra, function(response){
        if(response){
            res.json(response);
            res.status(response[0].status);
          }else{
            res.json('Bad request');
            res.status(400);
          }
      });
  },

  obterEmpate:function(req, res, next){
    var nome = req.body.nome;
    var gols = parseInt(req.body.gols);
      timesService.obterEmpate(nome, gols, function(response){
        if(response){
            res.json(response);
            res.status(response[0].status);
          }else{
            res.json('Bad request');
            res.status(400);
          }
      });
  }
}