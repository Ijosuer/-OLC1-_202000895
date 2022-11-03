var fs = require('fs');

function Generar_Graf(contenido){
    // console.log(contenido)
    fs.writeFile('ArbolAST.dot', contenido, function (err) {
        if (err) throw err;
        console.log('Dot generated!');
      }); 
    // fs.writeFile("ArbolAST.dot", contenido, function(err) {
    //     if (err) {
    //         return console.log(err);
    //     }
        var exec = require('child_process').exec, child;

        child = exec('dot -Tpng ArbolAST.dot -o ArbolAST.png && open ArbolAST.png',
            function (error, stdout, stderr) {
                // console.log('stdout: ' + stdout);
                // console.log('stderr: ' + stderr);
                if (error !== null) {
                    console.log('exec error: ' + error);
                }
            });
        return console.log("AST done!");
}

module.exports = Generar_Graf