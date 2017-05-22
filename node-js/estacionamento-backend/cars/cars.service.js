var Cars = require('./cars.schema');
var service = {};
const MAXIMUM_CAPACITY = 10;
const VALOR_ESTACIONAMENTO = 5;

service.registerCar = registerCar;
service.getCarById = getCarById;
service.getAllCars = getAllCars;
service.deleteCar = deleteCar;
service.payParking = payParking;
service.pesquisarVagas = pesquisarVagas;
service.paymentValue = paymentValue;
module.exports = service;

function getCarById(id, callback){
	Cars.find({'placa':id}, function(err, cars) {
		if (err) {
      callback({status:500, error: err });
    }
		callback(cars);
	});
}

function paymentValue(id, callback){
  Cars.find({'placa':id}, function (err, cars){
    if(err){
      callback({status: 500, error: err});
    }
    var dataEntrada = cars[0].date;
    var dataAtual = new Date();

    var diferencaMiliSegundos = dataAtual - dataEntrada;

    //transformando milisegundos em hora
    var diferencaHoras = ((diferencaMiliSegundos/1000)/60)/60;

    //math.floor vai arredondar o double do valor para inteiro (sem casas decimais)
    var valorEstacionamento = Math.floor(diferencaHoras * VALOR_ESTACIONAMENTO);

    //se o carro ficar menos que uma hora o valor do estacionamento ficará zerado, então seto isso para 5.
    callback(valorEstacionamento < 5 ? 5 : valorEstacionamento);
  })
}

function getAllCars(callback){
	Cars.find({}, function(err, cars) {
		if (err) {
      callback({status:500, error: err });
    }
			callback(cars);
	});
}

function pesquisarVagas(callback){
  Cars.find({}, function(err, cars){
		if (err) {
      callback({status:500, error: err });
    }
    callback((MAXIMUM_CAPACITY - cars.length));
  });
}

function registerCar (placa, callback){
	Cars.find({'placa':placa}, function(err, cars){
		if (err) {
      callback({status:500, error: err });
    }
		if(cars[0]){
			callback("Já existe um carro com esta placa");
		}else{
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

//{ new: true }, depois do pago = true caso de erro.
function payParking(id, callback){
	Cars.findOneAndUpdate({'placa': id }, { $set: { pago: true } }, function(err, response) {
		if (err) {
			callback(false);
		}else if(response){
			callback("Carro pago com sucesso");
		}
		else {
			callback("Erro ao efetuar pagamento");
		}
	});
}

function deleteCar(id, callback){
      Cars.findOneAndRemove({'placa': id}, function (err,response){
				console.log(response);
        if (err) {
					callback({status:500, error: err });
				}else if(response){
					callback("Carro saiu do estacionamento");
				}else{
					callback("Carro não encontrado");
				}
      });
}
