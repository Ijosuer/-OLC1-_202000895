import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})

export class DashboardComponent implements OnInit {
  
  constructor(private service:UserService) { }
  ngOnInit(): void {
  }

  
  getdata(){
    
    let textArea = document.getElementById('meteme') as HTMLInputElement;
    let inputfile = document.getElementById('file') as HTMLInputElement;
    let file = (inputfile.files?.item(0))
    console.log(file?.name)
    let reader = new FileReader();
    let texto = "";
    // reader.readAsText(file,"utf-8");
    reader.addEventListener("load", () => {
      // this will then display a text file
      texto += reader.result
    }, false);
  
    if (file) {
      reader.readAsText(file);
    }
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

}
