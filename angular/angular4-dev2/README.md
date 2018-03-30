# API node-api-example

## Dependencias utilizadas

- body-parser: https://github.com/expressjs/body-parser
- cors: https://github.com/expressjs/cors
- express: https://github.com/expressjs/express
- method-override: https://github.com/expressjs/method-override
- mm-node-logger: https://github.com/martinmicunda/mm-node-logger
- mongoose: https://github.com/Automattic/mongoose
- morgan: https://github.com/expressjs/morgan
- nodemon: https://github.com/remy/nodemon

## Instalação

- npm install

## Execução  

- npm start  

## Rotas  

- Página http://localhost:3000

### GET /api/people/:id

- Retorna people de acordo com o ID fornecido.

### GET /api/people

- Retorna todos os registros de people.

### POST /api/people

- Passando o ID por "body" cadastra um novo people.

### PUT /api/people/:id

- Passando o ID por parametro e um "name" por "body" cadastra um novo people com nome.

### PATCH /api/people/:id

- Altera o valor do "name", passando o ID por parametro e um "name" por "body" altera o valor atual de "name"

### DELETE /api/people/:id

- Exclui um registro de people passando o ID por parametro
