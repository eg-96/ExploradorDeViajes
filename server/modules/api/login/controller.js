import { database } from '../../../src/db';
import firebase from 'firebase-admin';

export const register = async (req, res) => {
  console.log(req.body);
  let user = req.body;
  try {
    var usersRef = {
      email: user.email,
      passowrd: user.password
    }
    await firebase.auth().createUser(usersRef).then(response => {
        console.log(response);
        return res.status(200).json({ error: false, message: "Se registro:" + user.email });
    }).catch(err => {
      console.log(err.errorInfo.message);
      return res.status(400).json({ error: true, message: err.errorInfo.message});
    })
  } catch (error) {
    return res.status(400).json({ error: true, message: error });
  }

}

export const log_in = async (req, res) => {
  let hello = "Hello World";
  res.status(200).json({ error: false, message: hello });
}
