import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { FilesService } from "../../service/fileService/files.service";
import { UserService } from "../../service/userservice/user.service";
import { UserData } from "../../models/userData";
import { HttpErrorResponse } from '@angular/common/http';
import { DestinationService } from 'src/app/service/destinationService/destination.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {
  private actualEmail: string;
  private actualUser : UserData;
  public fileContent : string;
  public show: boolean=false;
  destinations : any;

  constructor(private fileService: FilesService,
      public jwtHelper: JwtHelperService,
      private userService: UserService,
      private destinationService: DestinationService,
    ) { }

  

  ngOnInit(): void {
    console.log(File);
    const tokenDecoded = this.jwtHelper.decodeToken(localStorage.getItem('token'));
    this.actualEmail=tokenDecoded.sub;
    console.log(this.actualEmail);
    this.getActualUser(this.actualEmail);  
    this.getDestination();
  }
  
  files: File[] = [];
  public radioData: any;
  public radioDataOutput: any;
  public radioDestination: any;

  getActualUser(email) {
    this.userService.getUserByEmail(email).subscribe(
      (response : UserData ) => {
        console.log(response);
        this.actualUser = response;
        console.log(this.actualUser)
      },
        (error: HttpErrorResponse ) => {
             alert(error.message);
        }
    );
  }
  


onSelect(event) {
  console.log(event);
  this.files.push(...event.addedFiles);
}

onRemove(event) {
  console.log(event);
  this.files.splice(this.files.indexOf(event), 1);
}

// prettify() {
//   var element = document.getElementById("json");
// var obj = JSON.parse(element.innerText);
// element.innerHTML = JSON.stringify(obj, undefined, 2);
// }

onVisualizeFile() {
  console.log(this.files[0]);
  this.fileService.visualise(this.files[0]).subscribe(
   (response: any) => {
     console.log(response);
     this.fileContent=response;
     this.show=true;
     Swal.fire("successfuly visualized");
   },
     (error: HttpErrorResponse ) => {
      Swal.fire("an error has occured");
       //alert(error.message);
     }
 );
}
 onAddFile() {
  const Toast = Swal.mixin({
    toast: true,
    position: 'top-end',
    showConfirmButton: false,
    timer: 3000,
    timerProgressBar: true,
    didOpen: (toast) => {
      toast.addEventListener('mouseenter', Swal.stopTimer)
      toast.addEventListener('mouseleave', Swal.resumeTimer)
    }
  })
   console.log(this.files[0]);
   this.fileService.upload(this.files[0] ,this.actualUser.id, this.radioData,this.radioDataOutput, this.radioDestination).subscribe(
    (response: any) => {
        console.log(response);
        Toast.fire({
          icon: 'success',
          title: 'File saved successfully'
        })
    },
      (error: HttpErrorResponse ) => {
        console.log(error.status);
        if(error.status==500) {
          Toast.fire({
            icon: 'error',
            title: 'please verify that file type matches the input type'
          })
        }else if(error.status==403){
        Toast.fire({
          icon: 'error',
          title: 'file exists already in the data base'
        })
        }else {
          Toast.fire({
            icon: 'error',
            title: 'Oops, Something went wrong!'
          })
        }
        
        
        
      }
  );
 }

 getDestination() {
   this.destinationService.getDestinations().subscribe(
     (response: any) => {
        console.log(response)
        this.destinations= response;
     },
     (error: HttpErrorResponse ) => {
       alert(error.message);
     }
   )
 }

}
