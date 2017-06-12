var Times = require('./times.schema');
var service = {};

const PONTOS_VITORIA = 3;

service.salvarTime = salvarTime;
service.obterTodosTimes = obterTodosTimes;
service.obterVitoria = obterVitoria;
service.obterDerrota = obterDerrota;
service.obterEmpate = obterEmpate;
service.obterTimePeloNome = obterTimePeloNome;
service.obterResultadoJogo = obterResultadoJogo;
service.obterArrayDeNomeDosTimes = obterArrayDeNomeDosTimes;
module.exports = service;

function salvarTime(nome, callback){
  var newTime = new Times({
    'nome': nome,
    'pontos': 0,
    'qtdJogos': 0,
    'vitorias': 0,
    'empates': 0,
    'derrotas': 0,
    'golpro': 0,
    'golcontra': 0,
    'jogos': []
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
  Times.find({}, ['nome','pontos','qtdJogos', 'vitorias', 'empates', 'derrotas', 'golpro', 'golcontra', 'jogos'], {sort:{nome:1}}, function(err, times){
    if(err){
        callback(err)
      }
    else if(times.length === 0){
      callback('Nenhum time encontrado com esse nome');
    }  
    callback(times);
  })
}

function obterArrayDeNomeDosTimes(callback){
  Times.find({}, 'nome', {sort:{nome: 1}}, function(err, times){
    if(err){
      callback({status: 500, error: err});
    }
    else if(times.length === 0){
      callback("Nenhum time encontrado");
    }
    else{
      callback(times);
    }
  });
}

function obterResultadoJogo(mandante, golsMandante, visitante, golsVisitante, callback){
  var resultadoJogoFormatado = formatarResultadoJogo(mandante, golsMandante, visitante, golsVisitante);

  salvarNovoJogo(mandante, resultadoJogoFormatado);
  salvarNovoJogo(visitante, resultadoJogoFormatado);

  if(golsMandante > golsVisitante){
    obterVitoria(mandante, golsMandante, golsVisitante, function(response){

    });
    obterDerrota(visitante, golsVisitante, golsMandante, function(response2){

    });
  }
  else if(golsMandante === golsVisitante){
    obterEmpate(mandante, golsMandante, function(response){

    });
    obterEmpate(visitante, golsVisitante, function(response){

    });
  }
  else{
    obterVitoria(visitante, golsVisitante, golsMandante, function(response){

    });
    obterDerrota(mandante, golsMandante, golsVisitante, function(response){

    });   
  }
   callback(resultadoJogoFormatado);
}

function obterVitoria(nome, golpro, golcontra, callback){
  Times.find({'nome': nome}, function(err, times){
    if(err){
      return {status: 500, error: err};
    }
    else if(times.length === 0){
      return "Nenhum time encontrado com este nome";
    }
    else{
      var golproTotal = times[0].golpro + golpro;
      var golcontraTotal = times[0].golcontra + golcontra;
      var pontos = times[0].pontos + PONTOS_VITORIA;
      var vitorias = times[0].vitorias + 1;
      var jogos = times[0].qtdJogos + 1;

      times[0].update({$set:{'qtdJogos':jogos, 'vitorias': vitorias, 'pontos': pontos, 'golpro': golproTotal, 'golcontra': golcontraTotal}}, function(err, updated){
        if(err){
          return{status: 500, error: err};
        }
        return true;
      })
    }
  })
}

function obterDerrota(nome, golpro, golcontra, callback){
  Times.find({'nome': nome}, function(err, times){
    if(err){
      return{status: 500, error: err};
    }
    else if(times.length === 0){
      return"Nenhum time encontrado com este nome";
    }
    else{
      var golproTotal =  times[0].golpro + golpro;
      var golcontraTotal = times[0].golcontra + golcontra;
      var derrotas = times[0].derrotas + 1;
      var jogos = times[0].qtdJogos + 1;

      times[0].update({$set:{'qtdJogos':jogos, 'derrotas': derrotas, 'golpro': golproTotal, 'golcontra': golcontraTotal}}, function(err, updated){
        if(err){
          return{status: 500, error: err};
        }
        return true;
      })
    }
  })
}

function obterEmpate(nome, gols, callback){
  Times.find({'nome': nome}, function(err, times){
    if(err){
      return{status: 500, error: err};
    }
    else if(times.length === 0){
      return "Nenhum time encontrado com este nome";
    }
    else{
      var golProTotal = times[0].golpro + gols;
      var golContraTotal = times[0].golcontra + gols;
      var jogos = times[0].qtdJogos + 1;
      var empates = times[0].empates + 1;
      var pontos = times[0].pontos + 1;

       times[0].update({$set:{'pontos': pontos, 'qtdJogos':jogos, 'empates': empates, 'golpro': golProTotal, 'golcontra': golContraTotal}}, function(err, updated){
        if(err){
          return {status: 500, error: err};
        }
        return true;
      });
    }
  });
}

function obterTimePeloNome(nome, callback){
  Times.find({'nome': nome}, function(err, times){
    if(err){
      callback({status: 500, error: err});
    }
    else if(times.length === 0){
      callback("Nenhum time encontrado com este nome");
    }
    else{
      callback(times);
    }
  });
}

function salvarNovoJogo(nome, resultadoJogo){
  Times.find({'nome': nome}, function(err, times){
    if(err){
      return {status: 500, error: err};     
    }
    else if(times.length === 0){
      return "Nenhum time encontrado com este nome";
    }
    else{
      times[0].update({$push:{ jogos: resultadoJogo}}, function(err, updated){
        if(err){
          return {status: 500, error: err};
        }
        else{
          return true;
        }        
      });
    }    
  });
}

function formatarResultadoJogo(mandante, golsMandante, visitante, golsVisitante){
  return mandante + " " + golsMandante + "X" + golsVisitante + " " + visitante;
}

