import { Router } from 'express';
import * as  loginController from './controller'

const routes = new Router();

routes.post('/register', loginController.register);
routes.post('/login', loginController.log_in);

export default routes;