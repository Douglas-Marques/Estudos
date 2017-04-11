var fs = require('fs');

var arquivo = process.argv[2];
var file = fs.readFileSync(arquivo).toString().split('\n');
console.log(file.length -1)