"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var Carro_1 = require("./Carro");
var Pessoa = /** @class */ (function () {
    function Pessoa(nome, carroPreferido) {
        this.carro = undefined;
        this.nome = nome;
        this.carroPreferido = carroPreferido;
    }
    Pessoa.prototype.dizerNome = function () {
        return this.nome;
    };
    Pessoa.prototype.dizerCarroPreferido = function () {
        return this.carroPreferido;
    };
    Pessoa.prototype.comprarCarro = function (carro) {
        this.carro = carro;
    };
    Pessoa.prototype.dizerCarroQueTem = function () {
        if (!this.carro) {
            return new Carro_1.default("", 0);
        }
        return this.carro;
    };
    return Pessoa;
}());
exports.default = Pessoa;
