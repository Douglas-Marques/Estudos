var mongoose = require('mongoose');

var timesSchema = new mongoose.Schema({
  nome: String,
  pontos: Number,
  jogos: Number,
  vitorias: Number,
  empates: Number,
  derrotas: Number,
  golpro: Number,
  golcontra: Number
})

module.exports = mongoose.model('Times', timesSchema);