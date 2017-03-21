$(document).ready(function(){
  registrarEventos();
});

function registrarEventos(){
  adicionarTimes();
  iniciar();
  atualizarTabela();
  inserirCores();
}

function adicionarTimes(){
  renderizarTabela();
}

function iniciar(){
  $('#jogar').click(function(){
    verificarJogo();
  });
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
