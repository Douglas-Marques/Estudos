function ListaComprasController($scope) {
	$scope.itens = [
		{produto: 'Leite', quantidade: 2, comprado: false},
		{produto: 'Cerveja', quantidade: 12, comprado: false}
	];

	$scope.adicionaItem = function () {
		let item = {
			produto : $scope.item.produto,
			quantidade: $scope.item.quantidade,
			comprado: false,
			id: $scope.idAtual++
		};
		if(validarCamposObrigatorios(item)){
			$scope.itens.push(item);
			limparInputs($scope);			
		}else{
			alert("Erro ao cadastrar item");
		}
	};

	$scope.editarItem = function(item){
		let elementoProduto = document.getElementById(item.produto);
		elementoProduto.innerHTML = '<input type="text" ng-model="item.produto" placeholder="Produto" value='+item.produto+' required="true">';
	}

	$scope.removerItem = function(item){
		let index = $scope.itens.indexOf(item);
		$scope.itens.splice(index, 1);
	}
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
