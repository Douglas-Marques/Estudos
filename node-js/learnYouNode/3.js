var fs = require('fs');

var array = process.argv[2];
var file = fs.readFileSync(array).toString().split('\n');
console.log(file.length -1)