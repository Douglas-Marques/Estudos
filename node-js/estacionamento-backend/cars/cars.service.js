var Cars = require('./cars.schema');
var service = {};

//capacidade maxima do estacionamento
const MAXIMUM_CAPACITY = 10;

//estacionamento custa 5 reais
const VALOR_ESTACIONAMENTO = 5;

service.registerCar = registerCar;
service.getCarById = getCarById;
service.getAllCars = getAllCars;
service.deleteCar = deleteCar;
service.payParking = payParking;
service.pesquisarVagas = pesquisarVagas;
service.paymentValue = paymentValue;
module.exports = service;

//obter o carro recebendo id por parametro 
function getCarById(id, callback){
  id = id.toLowerCase();
	Cars.find({'placa':id}, function(err, cars) {
		if (err) {
      callback({status:500, error: err });
    }
    else if(cars.length === 0){
      callback('Nenhum carro encontrado com esta placa');
    }
		callback(cars);
	});
}

//obter valor do estaciomento
function paymentValue(id, callback){
  id = id.toLowerCase();
  Cars.find({'placa':id}, function (err, cars){
    if(err){
      callback({status: 500, error: err});
    }
    else if(cars.length === 0){
      callback('Nenhum carro encontrado com esta placa');
    }
    var dataEntrada = cars[0].date;
    var dataAtual = new Date();

    //subtrair data de agora com a data que o carro entrou no estacionamento (o JS transforma em milisegundos automagicamente)
    var diferencaMiliSegundos = dataAtual - dataEntrada;

    //transformando milisegundos em hora
    var diferencaHoras = ((diferencaMiliSegundos/1000)/60)/60;

    //math.floor vai arredondar o double do valor para inteiro (sem casas decimais)
    var valorTotalEstacionamento = parseInt(diferencaHoras, 10) * VALOR_ESTACIONAMENTO;

    //se o carro ficar menos que uma hora o valor do estacionamento ficará zerado, então seto isso para 5.
    callback(valorTotalEstacionamento < 5 ? 5 : valorTotalEstacionamento);
  })
}

//obter todos os carros
function getAllCars(callback){
  	Cars.find({}, function(err, cars) {
		if (err) {
      callback({status:500, error: err });
    }
    callback(cars);
	});
}

//verificar se há vagas disponiveis no estacionamento
function pesquisarVagas(callback){
  Cars.find({}, function(err, cars){
		if (err) {
      callback({status:500, error: err });
    }
    callback(MAXIMUM_CAPACITY - cars.length);
  });
}

//adicionar um novo carro
function registerCar (placa, callback){
  placa = placa.toLowerCase();
	Cars.find({}, function(err, cars){
		if (err) {
      callback({status:500, error: err });
    }
    for(var i = 0; i < cars.length; i++){
      if(cars[i].placa === placa){
        callback("Já existe um carro com esta placa");
      }
    }
    if(cars.length >= 10){
      callback("Estacionamento lotado");
    }
    else {
			var newCar = new Cars({
				'placa': placa,
        'date': new Date(),
        'pago': false
			});
			newCar.save(function(err, response){
				if (err){
          callback(false);
        }
				else {
					callback("Carro cadastado com sucesso");
				}
			});
		}
	});
}

//pagar estacionamento( o atributo pago vira true )
function payParking(placa, callback){
  Cars.findOneAndUpdate({'placa': placa, 'pago': false}, {$set : {pago: true} }, function(err, cars){
    if(err){
      callback(false);
    }
    else if(cars){
      callback("Carro pago com sucesso!");
    }
    else{
      callback("Este carro já foi pago!");
    }
  });
}

//deletar um carro 
function deleteCar(id, callback){
      Cars.findOneAndRemove({'placa': id}, function (err,response){
        if (err) {
					callback({status:500, error: err });
				}else if(response){
					callback("Carro saiu do estacionamento");
				}else{
					callback("Carro não encontrado");
				}
      });
}