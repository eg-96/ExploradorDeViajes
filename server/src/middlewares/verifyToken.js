var jwt = require('jsonwebtoken');

module.exports.verifyToken = (req,res,next) => {
  var token = req.headers['access-token'];

  if (token) {

    // verifies secret and checks if the token is expired
    jwt.verify(token, process.env.SECRET_PASSWORD, (err, decoded) =>{
      if (err) {
        return res.json({ message: 'invalid token' });
      } else {
        // if everything is good, save to request for use in other routes
        req.decoded = decoded;
        next();
      }
    });
  } else {
    // if there is no token
    res.send({
        message: 'No token provided.'
    });
  }
}