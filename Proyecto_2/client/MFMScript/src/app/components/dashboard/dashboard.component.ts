import { Component, OnInit,ViewChild } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
// import { EditorComponent,MonacoEditorModule } from 'ngx-monaco-editor';
import { FormControl } from '@angular/forms';
import { filter, take } from 'rxjs/operators';
import {MonacoEditorComponent,MonacoEditorConstructionOptions,MonacoEditorLoaderService,MonacoStandaloneCodeEditor} from '@materia-ui/ngx-monaco-editor';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
  data_error: any;
  data_simbolo: any;
  opciones: string[]=[];

  @ViewChild(MonacoEditorComponent, { static: false })
  monacoComponent: MonacoEditorComponent = new MonacoEditorComponent(this.monacoLoaderService);
  editorOptions: MonacoEditorConstructionOptions = {
    // theme: 'myCustomTheme',
    language: 'python',
    roundedSelection: true,
    autoIndent:"full"
  };
  consoleOptions: MonacoEditorConstructionOptions = {
    // theme: 'myCustomTheme',
    language: '',
    roundedSelection: true,
    autoIndent:"full",
    readOnly:true,
    minimap: {
      enabled: false
    }
  };
  editorTexto = new FormControl('');
  consola = new FormControl('');
  constructor(private monacoLoaderService: MonacoEditorLoaderService, private service: UserService) {
    this.monacoLoaderService.isMonacoLoaded$
      .pipe(
        filter(isLoaded => isLoaded),
        take(1)
      )
      .subscribe(() => {
        monaco.editor.defineTheme('myCustomTheme', {
          base: 'vs-dark', // can also be vs or hc-black
          inherit: true, // can also be false to completely replace the builtin rules
          rules: [
            {
              token: 'comment',
              foreground: 'ffa500',
              fontStyle: 'italic underline'
            },
            { token: 'comment.js', foreground: '008800', fontStyle: 'bold' },
            { token: 'comment.css', foreground: '0000ff' } // will inherit fontStyle from `comment` above
          ],
          colors: {}
        });
      });
  }

  editorInit(editor: MonacoStandaloneCodeEditor) {
    monaco.editor.setTheme('vs-dark');
    editor.setSelection({
      startLineNumber: 1,
      startColumn: 1,
      endColumn: 50,
      endLineNumber: 3
    });
  }

  ngOnInit(): void {
  }

  readFile(event:any){
  let input=event.target;
  let reader = new FileReader();
  reader.onload=()=>{
    var text=reader.result;
    if(text){
      this.editorTexto.setValue(text.toString());
    } 

  }
  reader.readAsText(input.files[0]);

  }

  getdata(){
    
    let textArea = document.getElementById('meteme') as HTMLInputElement;
    let inputfile = document.getElementById('file') as HTMLInputElement;
    let texto = "";
    let reader = new FileReader();
    // reader.readAsText(file,"utf-8");
    reader.addEventListener("load", () => {
      // this will then display a text file
      texto += reader.result
    }, false);
  
    // if (file) {
    //   reader.readAsText(file);
    // }
    //retornar info
    this.service.getdata().subscribe(
      (res:any)=>{
        
        // textArea.value = `valor`+res.aumento;
        textArea.value = texto;
      },
      (err)=>{
        console.log(err);
      }
    )
  }

  setdata(){
    //retornar info
    var json ={
        aumento: 20,
        text: "jajaj"
    }
    this.service.setdata(json).subscribe(
      (res)=>{
        console.log('settingggg');
        // alert('todo cool !')
      },
      (err)=>{
        console.log(err);
      }
    )
  }

  analizar(){
    var texto = {
      prueba: this.editorTexto.value
    }
    this.service.ejecutar(texto).subscribe((res:any)=>{
      // this.consola.setValue(res.consola);
      console.log(res);
      // this.consola.setValue(res.consola);
      let errores=res.errores.tablaErrores;
      let Simbol_l=res.Simbol_lit.listado_Simbolos;
      this.data_error=errores;
      this.data_simbolo=Simbol_l;
      console.log(this.data_simbolo)
      this.consola.setValue(res.consola);
    }, err=>{
      console.log("ERROR: "+err)
    });
  }

  loadPick(){
    this.service.cargarImagen().subscribe((res:any)=>{
      console.log('Cargando Imagen...')
    },(err)=>{
      console.log(err);
    }
  )};
}
