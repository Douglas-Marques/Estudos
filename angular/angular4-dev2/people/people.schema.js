var mongoose = require('mongoose'); // Importando mongoose para criar schema

// Criando Schema
var peopleSchema = new mongoose.Schema({
  people: Number,
  name: String
});

// Exportando Schema
module.exports = mongoose.model('People', peopleSchema);
