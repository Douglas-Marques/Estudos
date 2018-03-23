import Carro from './Carro'

export default class Pessoa {
    private nome: string
    private carroPreferido: string
    private carro?: Carro = undefined

    constructor(nome: string, carroPreferido: string) {
        this.nome = nome
        this.carroPreferido = carroPreferido
    }

    public dizerNome(): string {
        return this.nome
    }

    public dizerCarroPreferido(): string {
        return this.carroPreferido
    }

    public comprarCarro(carro: Carro): void {
        this.carro = carro
    }

    public dizerCarroQueTem(): Carro {
        if(!this.carro) {
			return new Carro("", 0);
		}
        return this.carro
    }
}