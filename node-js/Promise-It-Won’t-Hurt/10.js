//Funciona corretamente, porém o terminal alega ter um pequeno erro no meio da resposta..
//detalhe: a solução correta é exatamente igual a esta.
function alwaysThrows () {
  throw new Error("OH NOES");
};

function iterate(int) {
  console.log(int);
  return ++int;
};

Promise.resolve(iterate(1))
    .then(iterate)
    .then(iterate)
    .then(iterate)
    .then(iterate)
    .then(alwaysThrows)
    .then(iterate)
    .then(iterate)
    .then(iterate)
    .then(iterate)
    .then(iterate)
    .then(null, console.log);
