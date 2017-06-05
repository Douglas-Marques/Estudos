var Times = require('./times.schema');
var service = {};

const PONTOS_VITORIA = 3;

service.salvarTime = salvarTime;
service.obterTodosTimes = obterTodosTimes;
service.obterVitoria = obterVitoria;
service.obterDerrota = obterDerrota;
service.obterEmpate = obterEmpate;
module.exports = service;

function salvarTime(nome, callback){
  var newTime = new Times({
    'nome': nome,
    'pontos': 0,
    'jogos': 0,
    'vitorias': 0,
    'empates': 0,
    'derrotas': 0,
    'golpro': 0,
    'golcontra': 0
  });
  newTime.save(function(err, response){
    if(err){
      callback("Erro ao salvar time");
    }
    else{
      callback("Time cadastrado com sucesso");
    }
  });
}

function obterTodosTimes(callback){
  Times.find({}, function(err, times){
    if(err)
      {
        callback(err)
      }
    callback(times);
  })
}

function obterVitoria(nome, golpro, golcontra, callback){
  Times.find({'nome': nome}, function(err, times){
    if(err){
      callback({status: 500, error: err});
    }
    else if(times.length === 0){
      callback("Nenhum time encontrado com este nome");
    }
    else{
      var golproTotal = times[0].golpro + golpro;
      var golcontraTotal = times[0].golcontra + golcontra;
      var pontos = times[0].pontos + PONTOS_VITORIA;
      var vitorias = times[0].vitorias + 1;
      var jogos = times[0].jogos + 1;

      times[0].update({$set:{'jogos':jogos, 'vitorias': vitorias, 'pontos': pontos, 'golpro': golproTotal, 'golcontra': golcontraTotal}}, function(err, updated){
        if(err){
          callback({status: 500, error: err});
        }
        callback(nome + " venceu o jogo");
      })
    }
  })
}

function obterDerrota(nome, golpro, golcontra, callback){
  Times.find({'nome': nome}, function(err, times){
    if(err){
      callback({status: 500, error: err});
    }
    else if(times.length === 0){
      callback("Nenhum time encontrado com este nome");
    }
    else{
      var golproTotal =  times[0].golpro + golpro;
      var golcontraTotal = times[0].golcontra + golcontra;
      var derrotas = times[0].derrotas + 1;
      var jogos = times[0].jogos + 1;

      times[0].update({$set:{'jogos':jogos, 'derrotas': derrotas, 'golpro': golproTotal, 'golcontra': golcontraTotal}}, function(err, updated){
        if(err){
          callback({status: 500, error: err});
        }
        callback(nome + " perdeu o jogo");
      })
    }
  })
}

function obterEmpate(nome, gols, callback){
  Times.find({'nome': nome}, function(err, times){
    if(err){
      callback({status: 500, error: err});
    }
    else if(times.length === 0){
      callback("Nenhum time encontrado com este nome");
    }
    else{
      var golProTotal = times[0].golpro + gols;
      var golContraTotal = times[0].golcontra + gols;
      var jogos = times[0].jogos + 1;
      var empates = times[0].empates + 1;
      var pontos = times[0].pontos + 1;

       times[0].update({$set:{'pontos': pontos, 'jogos':jogos, 'empates': empates, 'golpro': golProTotal, 'golcontra': golContraTotal}}, function(err, updated){
        if(err){
          callback({status: 500, error: err});
        }
        callback(nome + " empatou o jogo");
      });
    }
  });
}