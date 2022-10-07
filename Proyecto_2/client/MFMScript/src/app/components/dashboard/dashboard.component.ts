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
    //retornar info
    this.service.getdata().subscribe(
      (res:any)=>{
        // console.log('yeah buddy getInfo');
        alert('valor: '+res.aumento)
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
