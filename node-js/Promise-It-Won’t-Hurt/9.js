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