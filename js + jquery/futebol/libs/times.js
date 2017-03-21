let times = ['Atlético-GO','Atlético-MG','Atlético-PR','Avaí','Bahia','Botafogo','Chapecoense','Corinthians','Coritiba','Cruzeiro',
                'Flamengo','Fluminense','Grêmio','Palmeiras','Ponte-Preta','Santos','São-Paulo','Sport','Vasco-da-Gama','Vitória'];

let jogos = [];

//Aqui renderizo os 20 clubes zerados na tabela
window.renderizarTabela = function(){
  let td = '<td>0</td>'
  for (let i = 0; i < 20; i++) {
    let classeNomeDoTimeAtual = times[i];
    $('.times').append('<tr class="'+classeNomeDoTimeAtual+'"><td>'+ (i+1) + '</td><td>' + times[i] + '</td>' + td+td+td+td+td+td+td+td+ '</tr>');
  }
}

window.verificarJogo = function(){
  var mandante = Math.floor(Math.random(0) * 20);
  var visitante = Math.floor(Math.random(0) * 20);

  if(mandante !== visitante){
    var nomeDoJogo = times[mandante] + times[visitante];
    if($.inArray(nomeDoJogo, jogos) === -1){
      jogos.push(nomeDoJogo);
      jogar(mandante, visitante);
    }
    else{
      verificarJogo();
    }
  }
  else{
    verificarJogo();
  }
}

function jogar(mandante, visitante){
  var golMandante = sortearGols();
  var golVisitante = sortearGols();

  if(golMandante > golVisitante){
    montarVitoria(mandante, golMandante, golVisitante);
    montarDerrota(visitante, golVisitante, golMandante);
  }
  else if(golMandante === golVisitante){
    montarEmpate(mandante, visitante, golMandante);
  }
  else{
    montarVitoria(visitante, golVisitante, golMandante);
    montarDerrota(mandante, golMandante, golVisitante)
  }

//mostrar resultado do jogo de forma simples
  $('#lista-jogos').append("<li>" + times[mandante] + " " + golMandante + "  X  " + golVisitante + " " + times[visitante] + "</li>");

  //jogos
  var jogosMandante = parseInt($('.' + times[mandante] + ' td:nth-child(4)').text());
  $('.' + times[mandante] +' td:nth-child(4)').text( jogosMandante + 1 );

  var jogosVisitante = parseInt($('.' + times[visitante] + ' td:nth-child(4)').text());
  $('.' + times[visitante] +' td:nth-child(4)').text( jogosVisitante + 1 );

  //saldo de gols
  var sgMandante = parseInt($('.' + times[mandante] + ' td:nth-child(10)').text());
  $('.' + times[mandante] +' td:nth-child(10)').text( sgMandante + (golMandante - golVisitante));

  var sgVisitante = parseInt($('.' + times[visitante] + ' td:nth-child(10)').text());
  $('.' + times[visitante] +' td:nth-child(10)').text( sgVisitante + (golVisitante - golMandante));
}

function montarEmpate(mandante, visitante, gols){
  //pontuacao
  var pegarPontuacaoAtualMandante = parseInt($('.' + times[mandante] + ' td:nth-child(3)').text());
  $('.' + times[mandante] +' td:nth-child(3)').text( pegarPontuacaoAtualMandante + 1 );

  var pegarPontuacaoAtualVisitante = parseInt($('.' + times[visitante] + ' td:nth-child(3)').text());
  $('.' + times[visitante] +' td:nth-child(3)').text( pegarPontuacaoAtualVisitante + 1 );

  //empate
  var pegarEmpateMandante = parseInt($('.' + times[mandante] + ' td:nth-child(6)').text());
  $('.' + times[mandante] + ' td:nth-child(6)').text( pegarEmpateMandante + 1 );

  var pegarEmpateVisitante = parseInt($('.' + times[visitante] + ' td:nth-child(6)').text());
  $('.' + times[visitante] + ' td:nth-child(6)').text( pegarEmpateVisitante + 1 );

  //gol pro
  var pegarQtdgolsProMandante = parseInt($('.' + times[mandante] + ' td:nth-child(8)').text());
  $('.' + times[mandante] + ' td:nth-child(8)').text( pegarQtdgolsProMandante + gols);

  var pegarQtdgolsProVisitante = parseInt($('.' + times[visitante] + ' td:nth-child(8)').text());
  $('.' + times[visitante] + ' td:nth-child(8)').text( pegarQtdgolsProVisitante + gols);

  //gol contra
  var pegarQtdgolsContraMandante = parseInt($('.' + times[mandante] + ' td:nth-child(9)').text());
  $('.' + times[mandante] + ' td:nth-child(9)').text( pegarQtdgolsContraMandante + gols);

  var pegarQtdgolsContraVisitante = parseInt($('.' + times[visitante] + ' td:nth-child(9)').text());
  $('.' + times[visitante] + ' td:nth-child(9)').text( pegarQtdgolsContraVisitante + gols);
}

function montarVitoria(ganhador, golPro, golContra){
  //pontuacao
  var pegarPontuacaoAtual = parseInt($('.' + times[ganhador] + ' td:nth-child(3)').text());
  $('.' + times[ganhador] +' td:nth-child(3)').text( pegarPontuacaoAtual + 3 );

  //vitoria
  var pegarQtdVitorias = parseInt($('.' + times[ganhador] + ' td:nth-child(5)').text());
  $('.' + times[ganhador] + ' td:nth-child(5)').text( pegarQtdVitorias + 1 );

  //gol pro
  var pegarQtdgolsPro = parseInt($('.' + times[ganhador] + ' td:nth-child(8)').text());
  $('.' + times[ganhador] + ' td:nth-child(8)').text( pegarQtdgolsPro + golPro);

  //gol contra
  var pegarQtdgolsContra = parseInt($('.' + times[ganhador] + ' td:nth-child(9)').text());
  $('.' + times[ganhador] + ' td:nth-child(9)').text( pegarQtdgolsContra + golContra);
}

function montarDerrota(perdedor, golPro, golContra){
  //derrota
  var pegarQtdDerrotas = parseInt($('.' + times[perdedor] + ' td:nth-child(7)').text());
  $('.' + times[perdedor] + ' td:nth-child(7)').text( pegarQtdDerrotas + 1 );

  //gol pro
  var pegarQtdgolsPro = parseInt($('.' + times[perdedor] + ' td:nth-child(8)').text());
  $('.' + times[perdedor] + ' td:nth-child(8)').text( pegarQtdgolsPro + golPro);

  //gol contra
  var pegarQtdgolsContra = parseInt($('.' + times[perdedor] + ' td:nth-child(9)').text());
  $('.' + times[perdedor] + ' td:nth-child(9)').text( pegarQtdgolsContra + golContra);
}

function sortearGols(){
  var primeiraFase = Math.floor(Math.random() * 11);

  if(primeiraFase <= 6){
    return Math.floor(Math.random(0) * 3);
  }
  else if(primeiraFase >= 7 && primeiraFase <= 9){
    return Math.floor(Math.random(0) * 5);
  }
  else{
    return Math.floor(Math.random(0) * 7);
  }
}
