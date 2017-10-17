var Livros = require('../model/livros.schema');
var slackService = require('./slack.service');
var isbn = require('node-isbn');

var service = {};

service.salvarLivro = salvarLivro;
service.obterTodosLivros = obterTodosLivros;
service.lerIsbn = lerIsbn;
service.pesquisarPorVoz = pesquisarPorVoz;
module.exports = service;

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
        slackService.enviarMsgSlack(livro.titulo, livro.subtitulo, livro.icone);
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
  });
}

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

function pesquisarPorVoz(texto, callback){
    texto = new RegExp(".*" + texto + ".*", "i")

    Livros.find({titulo: texto}, function(err, response){
    if(err){
      callback('Erro..', err);
    }
    else if(response.length === 0){
      callback('Não foi encontrado nenhum item com este nome');
    }
    else{
      callback(response);
    }            
  });    
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