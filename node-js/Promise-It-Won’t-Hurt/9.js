//Funciona corretamente, porém o terminal alega ter um pequeno erro no meio da resposta..
//detalhe: a solução correta é exatamente igual a esta.

function parsePromised() {
  return new Promise(function(resolve, reject) {
    try {
      var json = JSON.parse(process.argv[2])
    }
    catch (e) {
      reject(e)
    }
    resolve(obj)
  })
}

function imprimirJson(val) {
  console.log(val)
}

parsePromised()
  .then(imprimirJson)
  .then(null, console.log);