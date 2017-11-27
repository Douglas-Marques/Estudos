class Herois{

  constructor(array) {
       this.arrayDeHerois = array;
   }

foraDaGuerraCivil(){
  return this.arrayDeHerois.filter(heroi =>
        heroi.events.items.filter(e => e.name.contem('CiVIl war', true)).length === 0
      )
}

maisPublicado() {
  return this.arrayDeHerois
    .sort((heroi1, heroi2) =>
      heroi2.comics.available - heroi1.comics.available
    )[0];
}

mediaPaginas(){
  return this.arrayDeHerois.reduce(
      (acumulador, heroi) =>
          acumulador +
          heroi.comics.items.reduce(
            (acumuladorComic, comic) =>
              acumuladorComic + comic.pageCount
          , 0)
      , 0) / this.arrayDeHerois.length;
}

seriesPorLongevidade(){
  let todasSeries =
    this.arrayDeHerois.map(heroi => heroi.series.items)
    .reduce(
      (acumulador, series) => acumulador.concat(series)
      , []
    );

    let diff = serie => serie.endYear - serie.startYear;

    return todasSeries.sort((serie1, serie2) => diff(serie2) - diff(serie1));
}


  comicMaisCara() {
    let todasComics = []
    this.herois.forEach(heroi => {
      todasComics = todasComics.concat(heroi.comics.items);
    });

    let totalizarPrecos = comic => comic.prices.reduce(
      (acc, objetoPreco) => acc + objetoPreco.price
    , 0);

    return todasComics.sort(
      (comic1, comic2) => totalizarPrecos(comic2) - totalizarPrecos(comic1)
    )[0]
  }
}