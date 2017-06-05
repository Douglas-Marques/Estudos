var Cars = require('./cars.schema');
var service = {};

//capacidade maxima de vagas no estacionamento
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

  //lower case para motivos de comparação
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

  //lower case para motivos de comparação
  id = id.toLowerCase();
  Cars.find({'placa':id}, function (err, cars){
    if(err){
      callback({status: 500, error: err});
    }
    else if(cars.length === 0){
      callback('Nenhum carro encontrado com esta placa');
    }
    //data de entrada do carro
    var dataEntrada = cars[0].date;
    //data atual
    var dataAtual = new Date();

    //subtrair data atual com a data que o carro entrou no estacionamento (o JS transforma em milisegundos automagicamente)
    var diferencaMiliSegundos = dataAtual - dataEntrada;

    //transformando milisegundos em hora
    var diferencaHoras = ((diferencaMiliSegundos/1000)/60)/60;

    //math.floor vai arredondar o double do valor para inteiro (sem casas decimais)
    var valorTotalEstacionamento = parseInt(diferencaHoras, 10) * VALOR_ESTACIONAMENTO;

    //se o carro ficar menos que uma hora o valor do estacionamento ficará zerado, então seto isso para 5.
    callback(valorTotalEstacionamento < 5 ? 5 : valorTotalEstacionamento);
  });
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
  //para não haver problemas de case sempre salvo em minusculo e posteriormente faço buscas em minusculo
  placa = placa.toLowerCase();

  //se o carro existir seto para true e não entro no método de salvar um novo carro
  var carroJaExiste = false;

	Cars.find({}, function(err, cars){
		if (err) {
      callback({status:500, error: err });
    }
    //guardar length em uma variavel para o JS não recalcular a cada iteração
    var totalCarros = cars.length;
    for(var i = 0; i < totalCarros; i++){
      if(cars[i].placa === placa){
        carroJaExiste = true;
        callback("Já existe um carro com esta placa");
        break;
      }
    }
    //se estacionamento estiver lotado não registra o novo carro o bd
    if(totalCarros >= MAXIMUM_CAPACITY){
      callback("Estacionamento lotado");
    }
    //se carro não existir cria um novo e salva no banco
    else if(!carroJaExiste) {
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
//lower case para motivos de comparação
  placa = placa.toLowerCase();

  Cars.find({'placa':placa}, function (err, cars){
  if(err){
    callback({status: 500, error: err});
  }
  //verificar se o carro já existe
  else if(cars.length === 0){
    callback('Nenhum carro encontrado com esta placa');
  }
  //se já foi pago não atualiza novamente
  else if(cars[0].pago){
    callback('Este carro já foi pago');
  }
  //se passar pelas verificações eu atualizo o carro no banco
   else{
     cars[0].update({ $set:{pago:true}}, function(err, updated){
      if (err){
        callback({status:500, error:err});
      }
        callback('Carro pago com sucesso!');
     });
   }
  });
}

//deletar carro
function deleteCar(placa, callback){

  //lower case para motivos de comparação
  placa = placa.toLowerCase();
  Cars.find({'placa': placa}, function(err, cars){
    if(err){
      callback({status:500, error: err });
    }
    else if(cars.length === 0){
      callback('Nenhum carro encontrado com esta placa');
    }
    //carro só pode sair se já pagou
    else if(cars[0].pago === false){
      callback('Este carro ainda não foi pago');
    }
    //deletar carro se ele já pagou
    else{
      cars[0].remove(function(err, removed){
        if (err){
          callback(false);
        }
				else {
					callback("Carro deletado com sucesso");
				}
      });
    }
  });

}