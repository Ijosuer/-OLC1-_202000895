var fs = require('fs'); 
const Grafico = require("./controller/Instruccion/Grafico")
const Generar_Graf = require("./controller/Instruccion/Generar_Graf")
var parser = require('./gramatica');

fs.readFile('./entrada.txt', (err, data) => {
    if (err) throw err;
    var ast = parser.parse(data.toString());
    var Gdot="digraph mygraph { node [shape=box];\n"
            ast.arbol.forEach(instruccion => {
                Gdot += Grafico(instruccion,"Raiz","Raiz")
            });
            Gdot+="\n}"
            Generar_Graf(Gdot);
    
});
