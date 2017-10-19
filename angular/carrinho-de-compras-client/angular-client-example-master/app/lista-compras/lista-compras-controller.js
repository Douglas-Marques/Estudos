let module = angular.module('myApp.lista.controller', ['myApp.lista.service']);
module.controller('ListaComprasController', function($scope, ListaComprasService){
  
  $scope.listAllProducts = function(){
    ListaComprasService.getList().then(function(response){
      $scope.itens = response;     
    });
  }
  $scope.listAllProducts();

  $scope.postProduct = function(nome, quantidade){
    ListaComprasService.postProduct(nome, quantidade).then(function(response){
      $scope.listAllProducts();
      alert(response);   
    });
  }

  $scope.updateProduct = function(id, nome, quantidade){
    ListaComprasService.updateProduct(id, nome, quantidade).then(function(response){
      alert(response);
    });
  }

  $scope.deleteProduct = function(id){
    ListaComprasService.deleteProduct(id).then(function(response){
      alert(response);
    });
  }
  
	$scope.adicionaItem = function () {
		let item = {
			produto : $scope.item.produto,
			quantidade: $scope.item.quantidade,
		};
		if(validarCamposObrigatorios(item)){
      $scope.postProduct(item.produto, item.quantidade);
      limparInputs($scope);			
		}else{
			alert("Erro ao cadastrar item");
		}
	};

	$scope.editarItem = function(item, index){
    let elementoProduto = document.getElementById(item.produto);
    alterarVisibilidadeElemento("editar-" + index, 'none');
    alterarVisibilidadeElemento("excluir-" + index, 'block');    
		elementoProduto.innerHTML = '<input type="text" id="editar-item" placeholder="Produto" value='+item.produto+' required="true">';
  }
	
	$scope.salvarEdicao = function(item, index){
    $scope.updateProduct(item.id, item.produto, item.quantidade);
    atualizarHtml(item, index);
  }

  $scope.removerItem = function(item){
    $scope.deleteProduct(item.id);
		let index = $scope.itens.indexOf(item);
		$scope.itens.splice(index, 1);
	}
});
	
function atualizarHtml(item, index){
  let elementoProduto = document.getElementById(item.produto);	

  let nomeNovo = document.getElementById('editar-item').value;

  elementoProduto.id = nomeNovo;
  elementoProduto.innerHTML = '<strong class="ng-binding">' + nomeNovo + '</strong>'
  $scope.itens[index].produto = nomeNovo;		
  alterarVisibilidadeElemento("editar-" + index, 'block');
  alterarVisibilidadeElemento("excluir-" + index, 'none');
}

function alterarVisibilidadeElemento(id, visibilidade){
  document.getElementById(id).style.display = visibilidade;  
}

function limparInputs(escopo){
	escopo.item.produto = '';
	escopo.item.quantidade = '';
}

function validarCamposObrigatorios(item){
	if(item.produto && item.quantidade){
		if(item.produto.trim().length && item.quantidade > 0){
			return true;			
		}else{
			return false;
		}
	}else{
		return false;
  }
}
