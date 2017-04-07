//Soma todos os valores passados pela linha de comando e imprime no final
var sum = 0;
for (var i = 2; i < process.argv.length; i++) {
  sum += Number(process.argv[i]);
}

console.log(sum);