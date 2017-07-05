let nomeDosTimes = [];

$(document).ready(function(){
  registrarEventos();
});

function registrarEventos(){
  adicionarTimes();
  atualizarTabela();
  inserirCores();
  jogar();
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
    for(var i = 1; 4 >= i; i++){
      $(".times tr:nth-child("+i+")").css('background-color', 'rgba(50, 177, 255, 0.5)');
    }
    $(".times tr:nth-child(5)").css('background-color', 'rgba(10, 232, 189, 0.5)');
    $(".times tr:nth-child(6)").css('background-color', 'rgba(10, 232, 189, 0.5)');
    for(var i = 7; 14 >= i; i++){
      $(".times tr:nth-child("+i+")").css('background-color', 'rgba(39, 180, 35, 0.5)');
    }
    $(".times tr:nth-child(15)").css('background-color', 'rgb(255, 255, 255)');
    $(".times tr:nth-child(16)").css('background-color', 'rgb(255, 255, 255)');
    for(var i = 17; 20 >= i; i++){
      $(".times tr:nth-child("+i+")").css('background-color', 'rgba(255, 37, 37, 0.5)');
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
  for(var i = 0; i < 20; i++){
    var nomeTime = times[i].nome;
     nomeDosTimes.push(nomeTime);
    var dadosEstatisticos = formatarLinha(times[i].pontos,
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
  var saldoDeGol = golpro - golcontra;
  var linhaFormatada = '<td>'+pontos+'</td>';
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
  for(var i = 0; i < 20; i++){
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
