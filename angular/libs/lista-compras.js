function ListaComprasController($scope) {
	$scope.itens = [
		{produto: 'Leite', quantidade: 2, comprado: false},
		{produto: 'Cerveja', quantidade: 12, comprado: false}
	];
	
	$scope.flag_editar = false;
	
	$scope.adicionaItem = function () {
		let item = {
			produto : $scope.item.produto,
			quantidade: $scope.item.quantidade,
			comprado: false,
		};
		if(validarCamposObrigatorios(item)){
			$scope.itens.push(item);
			limparInputs($scope);			
		}else{
			alert("Erro ao cadastrar item");
		}
	};

	$scope.editarItem = function(item){
		$scope.flag_editar = true;
		let elementoProduto = document.getElementById(item.produto);
		elementoProduto.innerHTML = '<input type="text" id="editar-item" placeholder="Produto" value='+item.produto+' required="true">';
	}
	
	$scope.salvarEdicao = function(item){
		$scope.flag_editar = false;
		let len = $scope.itens.length;
		for(let i = 0; i < len; i++){
			if($scope.itens[i].produto === item.produto){
				atualizarHtml(item, i);
				return;
			}
		}
	}
	
	function atualizarHtml(item, posicao){
		let elementoProduto = document.getElementById(item.produto);	

		let nomeNovo = document.getElementById('editar-item').value;

		elementoProduto.id = nomeNovo;
		elementoProduto.innerHTML = '<strong class="ng-binding">' + nomeNovo + '</strong>'
		$scope.itens[posicao].produto = nomeNovo;		
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
