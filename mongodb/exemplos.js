//insere na "tabela" usuarios 
db.usuarios.insert( {
                     nome: "Eduardo Ribas",
                     cidade: "Cachoeirinha",
                     estado: "Rio Grande do Sul"
            }
  )
  ///ou
  MeusDados = {
            nome: "Eduardo Ribas",
            endereco: {cidade: "Cachoeirinha", estado: "Rio Grande do Sul"}
  }
  //ou
  MeusDados = {
            nome: "Eduardo Ribas",
            endereco: {cidade: "Cachoeirinha", estado: {nome:"Rio Grande do Sul", sigla:"RS", pais:"Brasil"}}
  }
//salva na "tabela" usuarios o objeto já criado
  db.usuarios.save(MeusDados) 

//insertOne insere e retorna o id.
db.movies.insertOne({
  name: "Movie 2", author: 'Author 1',date: new Date()
 })

//insert many insere um array
db.movies.insertMany(
  [{name: 'Movie 3', author: 'Author 2', date: new Date() },
   {name: "Movie 4", author: 'Author 1', date: new Date() } 
   ])


//DELETE FROM usuarios WHERE estado = "Rio Grande do Sul"
//remover da tabela usuarios todos usuarios com estado rs
  db.usuarios.remove( { estado: "Rio Grande do Sul" } )

//update
//no primeiro parametro passo o find, no segundo passo o replace
//sem o $set ele altera todos os valores do objeto, por isso tenho que passar o valor item novamente
db.books.update(
   { item: "XYZ123" },
   {
     item: "XYZ123",
     stock: 10,
     info: { publisher: "2255", pages: 150 },
     tags: [ "baking", "cooking" ]
   }
)
//fazendo o update com $set ele mantém todos os valores que eu não passar do jeito que estão
//neste caso o campo idade vai continuar 50
db.usuarios.update(
  {idade: "50"},
  {
     $set:{nome: "Miller" }
  } 
);

//para fazer um LIKE no mongo 
//pego todos os usuarios q tenham 'Edu' em alguma parte do nome
db.usuarios.find(
            { nome: /^Edu/ } // assim pesquiso só a partir do começo da variavel nome
  )

db.usuarios.find(
            { nome: /Edu/ } // assim pesquiso em qualquer parte da variavel nome
  )

db.usuarios.find({
    nome: /Edu/i  } // o  barra i serve para optar por CASE INSENSITIVE
  )

  //ordenar em ordem crescente
  //dentro do find ainda posso passar parametros para só obter alguns dados
  //1 para ordem crescente, -1 para ordem decrescente
  db.usuarios.find().sort(
    {nome:1}
  )
  //ordenar em ordem decrescente
  db.usuarios.find().sort(
    {nome:-1}
  )

//pesquisar por mais de alguma coisa AND.. o lte significa 'less then e'
//o gte signfica greater than e
> db.inventory.find({
  item: /p/, qty:{ $lte: 50}
  //item: /p/, qty:{ $gte: 50}
});

//exemplo de or
 db.inventory.find({    
    $or:[ 
      {item: /p/},
      {qty: {$lt: 50 }}
      ]
  })

  //a propriedade h está dentro de size.. por isso o ponto para acessar o campo
  db.inventory.find({
    'size.h': {$lte: 10}
  })

  db.inventory.find({
    'size.uom': 'in'
  })

  //para pesquisar em um array
  db.users.find({
    badges: ['black', 'blue']
  })

  //pesquisa objetos que nao tenham o atributo endereço.. se for true pesquisa apenas os que tem
  db.usuarios.find({
    'endereco': {$exists: false }
   })

//só vai retornar o id e o name dos usuarios 
   db.usuarios.find({}, { _id: 1, name: 1})
  //posso adicionar um filtro ainda no find
  db.usuarios.find({
    'endereco': {$exists: true }
   }, { _id: 1, name: 1})

//Traz só o primeiro registro
   db.usuarios.findOne({})

//o multi serve para dizer que o update vai alterar multiplos valores... neste caso todos que forem maior ou igual a 10
db.inventory.update({
  qty: {$gte: 10}}, 
  {$set: {qty: 30 }}, 
  {multi: true }
)

//para remover atributos de uma collection, o $unset tirou o atributo status
db.inventory.update({
  item: 'paper' 
},
 {$unset:  {status: 0 }})

 //renomear o nome de uma propriedade(não o valor dela)
 db.inventory.update({
   item: 'journal'
 },
 {$rename:  {'qty': 'quantidade' }})

 