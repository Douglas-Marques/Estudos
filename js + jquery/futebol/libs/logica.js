let nomeDosTimes = [];
let rodadaAtual = 1;

$(document).ready(function(){
  registrarEventos();
});

function registrarEventos(){
  adicionarTimes();
  atualizarTabela();
  inserirCores();
  jogar();
  proximaRodada();
  rodadaAnterior();
}

function proximaRodada(){
  $('#proxima-rodada').click(function(){
    rodadaAtual++;
    atualizarLabelRodadas();
  });
}

function rodadaAnterior(){
  $('#rodada-anterior').click(function(){
    rodadaAtual--;
    atualizarLabelRodadas();
  });
}

function atualizarLabelRodadas(){
  $('#label-rodadas').text('Rodada ' + rodadaAtual);
}

function jogar(){
  $('#jogar').click(function(){
    verificarJogo(nomeDosTimes);
  });
}

function adicionarTimes(){
  obterTimes();
}

function atualizarTabela(){
  $('#pontos').click(function(){
    $("#tabela").tablesorter();
  });
}

function inserirCores(){
  $('#cores').click(function(){
    for(let i = 1; i <= 20; i++){
      if(i <= 4){
        $(".times tr:nth-child("+i+") td:first-child").css('background-color', 'rgba(50, 177, 255, 0.5)');
      }else if(i <= 6){
        $(".times tr:nth-child("+i+") td:first-child").css('background-color', 'rgba(10, 232, 189, 0.5)');
      }else if(i <= 12){
        $(".times tr:nth-child("+i+") td:first-child").css('background-color', 'rgba(39, 180, 35, 0.5)');
      }else if(i <= 16){
        $(".times tr:nth-child("+i+") td:first-child").css('background-color', 'rgb(255, 255, 255)');
      }else if(i <= 20){
        $(".times tr:nth-child("+i+") td:first-child").css('background-color', 'rgba(255, 37, 37, 0.5)');
      }
    }
    atualizarPosicao();
  });
}

function atualizarPosicao(){
  for(var i = 20; i >= 1; i--){
    $('.times tr:nth-child('+i+') td:nth-child(1)').text(i);
  }
}

//Aqui renderizo os 20 clubes vindo do banco na tabela
function renderizarTabela(times){
  $('.times').children().remove();
  for(let i = 0; i < 20; i++){
    let nomeTime = times[i].nome;
     nomeDosTimes.push(nomeTime);
    let dadosEstatisticos = formatarLinha(times[i].pontos,
                                          times[i].qtdJogos,
                                          times[i].vitorias,
                                          times[i].empates,
                                          times[i].derrotas,
                                          times[i].golpro,
                                          times[i].golcontra);

    $('.times').append('<tr id="'+nomeTime+'"><td>' + (i+1) + '</td><td>' + nomeTime + '</td>' + dadosEstatisticos + '</tr>');
  }
}

function formatarLinha(pontos, jogos, vitorias, empates, derrotas, golpro, golcontra){
  let saldoDeGol = golpro - golcontra;
  let linhaFormatada = '<td>'+pontos+'</td>';
      linhaFormatada += '<td>'+jogos+'</td>';
      linhaFormatada += '<td>'+vitorias+'</td>';
      linhaFormatada += '<td>'+empates+'</td>';
      linhaFormatada += '<td>'+derrotas+'</td>';
      linhaFormatada += '<td>'+golpro+'</td>';
      linhaFormatada += '<td>'+golcontra+'</td>';
      linhaFormatada += '<td>'+saldoDeGol+'</td>';
      return linhaFormatada;
  }

function obterNomeDosTimes(arrayDeTimes){
  for(let i = 0; i < 20; i++){
    nomeDosTimes.push(arrayDeTimes[i].nome);
  }
}

//obter todos os times vindo do banco
window.obterTimes = function(){
  $.ajax({
      url: 'http://localhost:3000/times',
      type: 'GET',
      dataType: 'json',
      success: function(data) {
          renderizarTabela(data);
          //obterNomeDosTimes(data);
     },
      error: function (request, error){
          alert("Request: "+JSON.stringify(request));
      }
    });
}
