module.exports = function(){
  var db = require('./../libs/connect_db')();
  var Schema = require('mongoose').Schema;

  var carro = Schema({
    placa: String,
    date: Date,
    pago: Boolean
  });
  return db.model('carros', carro);
}