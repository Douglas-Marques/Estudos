var expect = require('chai').expect;

var Cars = require('../cars/cars.schema');

describe('cars', function(){
  it('Verifica se a placa está vazia', function(done){
   //configurar model para validação falahar
    var carro = new Cars();

    //fazer validação
    carro.validade(function(err){
      //checar se existiu realmente o erro
      expect(err.erros.placa).to.exist;
      done();
    });
  });
});

describe('cars', function(){
  it('Verifica se o carro foi criado', function(done){
   //configurar model para validação falahar
    var carro = new Cars('eduardo');

    //fazer validação
    carro.validade(function(err){
      //checar se não existiu realmente o erro
      expect(err.erros.placa).to.not.exist;
      done();
    });
  });
});