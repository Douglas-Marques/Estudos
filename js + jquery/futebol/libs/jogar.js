//$('#jogar').attr('disabled', 'true');

window.verificarJogo = function(times){
    prepararJogo(times);
}

function prepararJogo(times){
  var numeroMandante = Math.floor(Math.random(0) * 20);
  var numeroVisitante = Math.floor(Math.random(0) * 20);
    
  var mandante = times[numeroMandante];
  var visitante = times[numeroVisitante];
    
  realizarJogo(mandante, visitante);
}

function realizarJogo(mandante, visitante){
  var golMandante = sortearGols();
  var golVisitante = sortearGols();
    
  obterResultadoJogo(mandante, visitante, golMandante, golVisitante);
  
}

function sortearGols(){
  var primeiraFase = Math.floor(Math.random() * 11);

  if(primeiraFase <= 7){
    return Math.floor(Math.random(0) * 3);
  }
  else if(primeiraFase >= 8 && primeiraFase <= 9){
    return Math.floor(Math.random(0) * 5);
  }
  else{
    return Math.floor(Math.random(0) * 7);
  }
}

function mostrarResultadoJogo(resultado){
   $('#lista-jogos').append("<li>" + resultado + "</li>");
}

function obterResultadoJogo(mandante, visitante, golsMandante, golsVisitante){
    var resultado = {
          mandante: mandante, 
          golsMandante: golsMandante,
          visitante: visitante,
          golsVisitante: golsVisitante
    }

    $.ajax({
      url: 'http://localhost:3000/jogo',
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      data:  resultado,               
      type: 'PATCH',
      crossDomain: true,
      dataType: 'json',
      }).done(function(data) {
        mostrarResultadoJogo(data);
        obterTimes();
      })
      .fail(function(){
        alert('Errouuu');  
      });  
}

