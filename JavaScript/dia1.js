var person = {
    firstName:"Eduardo",
    lastName:"Ribas",
    age:20,
    eyeColor:"black",
    fullName: function(){return this.firstName + " " + this.lastName}
}; 

//acessando propriedades
 person.lastName; 
//or 
 person["lastName"]; 

//acessando metodos
 var name = person.fullName();

var text = '{ "employees" : [' +
'{ "firstName":"John" , "lastName":"Doe" },' +
'{ "firstName":"Anna" , "lastName":"Smith" },' +
'{ "firstName":"Peter" , "lastName":"Jones" } ]}';

var obj = JSON.parse(text);

var x = 5;
var y = 6;
Math.max(x, y);
//this return y;