var Cars = require('./cars.schema');
var service = {};

service.registerCar = registerCar;
service.getCarById = getCarById;
service.getAllCars = getAllCars;
service.deleteCar = deleteCar;
service.payParking = payParking;
module.exports = service;

function getCarById(id, callback){
	Cars.find({'placa':id}, function(err, cars) {
		if (err) {
      callback({status:500, error: err });
    }
		callback(cars);
	});
}

function getAllCars(callback){
	Cars.find({}, function(err, cars) {
		if (err) {
      callback({status:500, error: err });
    }
			callback(cars);
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