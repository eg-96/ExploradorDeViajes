import { Router } from 'express';
import * as  pruebaController from './controller'

const routes = new Router();

routes.post('/test', pruebaController.prueba);

export default routes;