'use strict';

var config = {};

config.environment = process.env.NODE_ENV || 'development';

// Configuração do servidor
config.server = {
    host: process.env.IP || 'localhost',
    port: process.env.PORT || 3000
};

module.exports = config;