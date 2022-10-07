var express = require('express');
var morgan = require('morgan');
var cors = require('cors');
var corsOptions = { origin:true, optionsSuccesStatus: 200};
var app = express();

app.use(morgan('dev'));
app.use(express.json());
app.use(cors(corsOptions));
app.use(express.urlencoded({extended:true}));

var aumento = 0;


app.listen(8080, function () {
  console.log('app escuchando en puerto 8080')
})

app.get('/',function (req, res) {
  res.json({mensaje:"oki " , otro:"otro xd"});
  aumento +=1;
})

app.get('/text',function (req, res) {
  res.send('Este mensaje es full texto');
})

app.get('/aument',function (req, res) {
  res.json({aumento:aumento});
})

app.post('/setAument',function (req, res) {
  aumento+= req.body.aumento
  var text = req.body.text  
  res.json({message:text});
})
