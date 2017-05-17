var mongoose = require('mongoose');

  var carsSchema = new mongoose.Schema({
    placa: String,
    date: Date,
    pago: Boolean
  });

module.exports = mongoose.model('Cars', carsSchema);