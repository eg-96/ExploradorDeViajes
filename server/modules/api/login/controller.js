import { database } from '../../../src/db';

export const register = async (req, res) => {
  console.log(req);
  let hello = "registrando";
  res.status(200).json({ error: false, message: hello });
}

export const log_in = async (req, res) => {
  let hello = "Hello World";
  res.status(200).json({ error: false, message: hello });
}
