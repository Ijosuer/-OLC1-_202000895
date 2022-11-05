import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from "rxjs";


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
  ejecutar(codigo: any):Observable<any>{
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    };
    return this.http.post(`${this.URL}/analizar`, codigo,httpOptions);
  }
  cargarImagen(){
      return this.http.get(`${this.URL}/getASTimage`)
  }

}
