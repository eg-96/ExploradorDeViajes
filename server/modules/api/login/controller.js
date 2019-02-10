import { database } from '../../../src/db';

export const register = async (req, res) => {
  console.log(req.body);
  let user = req.body;
  try {
    var usersRef = database.collection('Users').add({
      email: user.email,
      passowrd: user.password
    }).then(ref => {
      res.status(200).json({ error: false, message: "Se registro:" + user.email });
    })

  } catch (error) {
    res.status(500).json({ error: true, message: "Error al registrar un user" });
  }

}

export const log_in = async (req, res) => {
  let hello = "Hello World";
  res.status(200).json({ error: false, message: hello });
}
