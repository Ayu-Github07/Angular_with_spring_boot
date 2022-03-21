import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Student } from '../model/Student';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  baseUrl = "http://localhost:8080/students/";

  constructor(private http : HttpClient) { }

  getStudents() : Observable<Student[]>{
    return this.http.get<Student[]>(`${this.baseUrl}`);
  }

  setStudents(data:any) : Observable<Object>{
    return this.http.post(`${this.baseUrl}`,data,{responseType: 'text'});
  }

  updateStudent(data:any,id:number) : Observable<Object>{
    console.log(`service layer id = ${id}`);
    return this.http.put(`${this.baseUrl}update/${id}`,data,{responseType: 'text'})
  }

  deleteStudent(id:number) : Observable<any>{
    return this.http.delete(`${this.baseUrl}delete/${id}`,{responseType: 'text'});
  }
}
