var mongoose = require('mongoose');

var timesSchema = new mongoose.Schema({
  nome: String,
  pontos: Number,
  qtdJogos: Number,
  vitorias: Number,
  empates: Number,
  derrotas: Number,
  golpro: Number,
  golcontra: Number,
  jogos: [String]
})

module.exports = mongoose.model('Times', timesSchema);