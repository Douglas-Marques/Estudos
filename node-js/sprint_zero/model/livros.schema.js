var mongoose = require('mongoose');

var livrosSchema = new mongoose.Schema({
  titulo: String,
  subtitulo: String,
  autores: [

  ],
  descricao: String,
  icone: String,
  idioma: String,
  avaliacao: Number,
  qtdPaginas: Number,
  categorias: [

  ],
  isbn: String
});

module.exports = mongoose.model('Livros', livrosSchema);