import { Component,OnInit,ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import { Observable } from 'rxjs';
import { AddStudentComponent } from './components/add-student/add-student.component';
import { Student } from './model/Student';
import { ApiService } from './services/api.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Student-Registration';

  students!: Student[];
  constructor(public dialog: MatDialog,private api : ApiService){

  }
  ngOnInit(): void {
    this.getAllStudents();
  }
  
  openDialog() {
    this.dialog.open(AddStudentComponent, {
      width:"40%"
    }).afterClosed().subscribe(val=>{
      if(val === "save"){
        this.getAllStudents();
      }
    })
  }

  getAllStudents(){
    this.api.getStudents()
    .subscribe(
      (res) => {
        this.students = res;
        console.log(this.students);
      },
      (error) =>{
        alert("Error while fetching the records!!");
      }
    )
  }

  editStudent(student : Student){
    this.dialog.open(AddStudentComponent,(
      {
        width: "40%",
        data: student
      }
      
    )).afterClosed().subscribe(val=>{
      if(val === "Update"){
        this.getAllStudents();
      }
    });
    console.log("Fetching Student Data",student);
  }

  deleteStudent(id: number){
    this.api.deleteStudent(id)
    .subscribe({
      next:(res)=>{
        alert("Students Details Deleted Successfully!");
        this.getAllStudents();
      },
      error: (err) =>{
        alert("Error Occured while deleting the record!");
      }
    })
  }
}
