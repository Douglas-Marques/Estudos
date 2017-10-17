var isbnService = require('../service/isbn.service');
var express = require('express');
var controller = {};

controller.lerIsbn = lerIsbn;
controller.salvarLivro = salvarLivro;
controller.obterTodosLivros = obterTodosLivros;
controller.pesquisarPorVoz = pesquisarPorVoz;
module.exports = controller;

function salvarLivro(req, res, next){
   isbnService.salvarLivro(req.body, function(response){
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
    isbnService.obterTodosLivros(function(response){
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

function lerIsbn(req, res, next){
  var numeroIsbn = req.params.isbn;
  isbnService.lerIsbn(numeroIsbn, function(response){
    if(response){
      res.json(response);
    }
    else{
      res.json('Bad request');
      res.status(400);
    }
  });
}

function pesquisarPorVoz(req, res, next){
  var texto = req.params.texto;
    isbnService.pesquisarPorVoz(texto, function(response){
      if(response){
        res.json(response);
      }
      else{
        res.json('Bad request');
        res.status(400);
      }
    });   
}