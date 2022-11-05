'use strict'
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
const parser = require('./gramatica')

// app.listen(8080, function () {
//   console.log('app escuchando en puerto 8080')
// })

app.get('/',function (req, res) {
  res.json({mensaje:"oki " , otro:"otro xd"});
  aumento +=1;
})

const analizar = require('./routes/analizadores.js')(parser, app)
app.listen('8080', ()=>{
    console.log("Servidor en puerto 8080")
})

app.post('/analizar',function (req, res) {
  // res.json({ans:req.codigo});
  console.log(req.body.prueba);
  res.json({consola:req.body.prueba});
  
})

app.get('/aument',function (req, res) {
  res.json({aumento:aumento});
})

app.get('/getASTimage',function (req, res) {
  var exec = require('child_process').exec, child;
child = exec('dot -Tpng ArbolAST.dot -o ArbolAST.png && open ArbolAST.png',
    function (error, stdout, stderr) {
        // console.log('stdout: ' + stdout);
        // console.log('stderr: ' + stderr);
        if (error !== null) {
            console.log('exec error: ' + error);
        }
    });

})

app.post('/setAument',function (req, res) {
  aumento+= req.body.aumento
  var text = req.body.text  
  res.json({message:text});
})
