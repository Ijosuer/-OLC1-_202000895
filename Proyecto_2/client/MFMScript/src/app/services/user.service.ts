import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  URL = "http://localhost:8080" //endpoint
  constructor(private http:HttpClient) { }

  getdata(){
    return this.http.get(`${this.URL}/aument`);
  }
  
  setdata(json:any){
    return this.http.post(`${this.URL}/setAument`,json);

  }

}


