import express from 'express';
import middlewares from './src/middlewares';
import { loginRoutes, pruebaRoutes } from './modules';
require('dotenv').config();
import db from './src/db';

const app = express();
const PORT = process.env.PORT || 8080;

// Iniciar middlewares
middlewares(app);

// Iniciar rutas a API
app.use('/api', [loginRoutes, pruebaRoutes]);

app.listen(PORT, () => console.log(`Running on port: ${PORT}!`)) 