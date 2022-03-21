import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/services/api.service';
import {MatDialogRef,MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Student } from 'src/app/model/Student';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css']
})
export class AddStudentComponent implements OnInit {

  studentForm!: FormGroup;
  title: string = "Add New Student"
  actionBtn: string = "Save";

  constructor(private formBuilder: FormBuilder,private api : ApiService,private dialogRef : MatDialogRef<AddStudentComponent>,
    @Inject(MAT_DIALOG_DATA) public editData: Student) { }

  ngOnInit(): void {
    this.studentForm = this.formBuilder.group({
      'id' : [''],
      'name' : ['',Validators.required],
      'email' : ['',Validators.required],
      'rollno' : ['',Validators.required],
      'mobile' : ['',Validators.required],
      'aadhar' : ['',Validators.required],
      'address' : ['',Validators.required],
      'pincode' : ['',Validators.required],
      'gender' : ['',Validators.required]
    })

    if(this.editData){
      this.title = "Update Existing Student";
      this.actionBtn = "Update";
      this.studentForm.controls['id'].setValue(this.editData.id);
      this.studentForm.controls['name'].setValue(this.editData.name);
      this.studentForm.controls['email'].setValue(this.editData.email);
      this.studentForm.controls['rollno'].setValue(this.editData.rollno);
      this.studentForm.controls['mobile'].setValue(this.editData.mobile);
      this.studentForm.controls['aadhar'].setValue(this.editData.aadhar);
      this.studentForm.controls['address'].setValue(this.editData.address);
      this.studentForm.controls['pincode'].setValue(this.editData.pincode);
      this.studentForm.controls['gender'].setValue(this.editData.gender);
      
    }
  }

  addStudent(){
    if(!this.editData){
      if(this.studentForm.valid){
        this.api.setStudents(this.studentForm.value).subscribe(
          (data) => {
            alert("Student Details Added Successfully!");
            this.studentForm.reset();
            this.dialogRef.close('save');
          },
          (error) => {
            alert("Error Occurred While Adding The Student Details");
          }
        )
        
      }
    }else{
      this.editStudent();
    }
  }

  editStudent(){
    console.log(this.editData);
    console.log(this.studentForm.value);
    console.log(`id = ${this.editData.id}`);

    this.api.updateStudent(this.studentForm.value,this.editData.id)
    .subscribe({
      next:(res)=>{
        console.log(res);
        alert("Advertisement Updated Successfully!");
        this.studentForm.reset();
        this.dialogRef.close("Update");
      },
      error: (err) => {
        alert("Error Occured while updating the record!!");
      }
    })
  }
}
