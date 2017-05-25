$(document).ready(function (){
    tableFill();
  
    $('#getCarros').click(function () {
         
    });
    
    $('#getCarroId').click(function (){
        var placa = $('#txtPlaca').val();
        console.log('ok');
        console.log(placa);
        $.ajax({
            url: 'http://localhost:3000/cars/' + placa,
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                console.log(data);
                alert('Data ' + data[0].placa)
            },
            error: function (request, error){
                alert("Request: "+JSON.stringify(request));
            }
        }); 
    });
    
   $("#formCadastro").submit(function(event) {
       event.preventDefault();
       var data = {
           placa: $('#txtPlaca').val(),
       }
       
      $.ajax({
            url: 'http://localhost:3000/cars',
            type: 'POST',
            dataType: 'json',
            data: data,
            success: function(data) {
                console.log(data);
                alert('Carro cadastrado com sucesso');
                var trHTML = '';
                trHTML += "<tr><td>" +data[1].response.placa+ "</td><td>" +data[1].response.data+ "</td><td><i class='fa fa-money fa-2x'></a>";
                $('#carsTable').append(trHTML);
            },
            error: function (request, error){
                alert("Request: "+JSON.stringify(request));
            },
            
      }); 
    });
    
    //Functions
    
    
    $(document).on('click','body .fa',function(){
        if($(this).hasClass('fa-money')){
            payTicket($(this));
        } else {
            console.log('nao esta pago')
            exitParking($(this));
        }
    });
});

function tableFill(){
    $.ajax({
        url : 'http://localhost:3000/cars',
        type : 'GET',
        dataType:'json',
        success : function(data){
           var trHTML = '';
            $.each(data, function (i, item) {
                var test = item.placa;
                trHTML += "<tr><td>" +item.placa+ "</td><td>" +item.data+ "</td><td><i class='fa fa-money fa-2x'></a>";

            });
            $('#carsTable').append(trHTML);
        },
        error : function(request,error)
        {
            alert("Request: "+JSON.stringify(request));
        }
    });
}

function payTicket(icone){
    var placa = $(icone).parent().parent().children().first().text();
    $.ajax({
      url: 'http://localhost:3000/cars' + placa,
      type: 'PUT',
      success: function(data) {
          alert('Carro pago!')
          changeIcon(icone);
      },
        erro: function(){
            console.log('Erro')
        }
        
    });
}

function changeIcon(icone){
    $(icone).removeClass('fa-money').addClass('fa-sign-out red');
}

function exitParking(icone){
    var placa = $(icone).parent().parent().children().first();
    var placaText = $(placa).text();
    console.log(placa);
    $.ajax({
        url: 'http://localhost:3000/api/cars/' + placaText,
        type: 'DELETE',
        success: function(result) {
            alert('Saiu do estacionamento');
            $(placa).parent().remove();
        }
    });
}
