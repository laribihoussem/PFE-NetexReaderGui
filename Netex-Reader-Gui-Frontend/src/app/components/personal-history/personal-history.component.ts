import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserData } from 'src/app/models/userData';
import { FilesService } from 'src/app/service/fileService/files.service';
import { UserService } from 'src/app/service/userservice/user.service';

@Component({
  selector: 'app-personal-history',
  templateUrl: './personal-history.component.html',
  styleUrls: ['./personal-history.component.css']
})
export class PersonalHistoryComponent implements OnInit {

  constructor(private fileService: FilesService, public jwtHelper: JwtHelperService, private userService: UserService) { }
  Files: any;
  p: number = 1;
  filterTerm!: string;
  private actualEmail: string;
  private actualId : number;

  ngOnInit(): void {
    const tokenDecoded = this.jwtHelper.decodeToken(localStorage.getItem('token'));
    this.actualEmail=tokenDecoded.sub;
    console.log(this.actualEmail);
    this.getActualUser(this.actualEmail);
    
  }

  getActualUser(email) {
    this.userService.getUserByEmail(email).subscribe(
      (response : UserData ) => {
        this.actualId= response.id;  
        this.getFiles(this.actualId)  ;
      },
           (error: HttpErrorResponse ) => {
             alert(error.message);
           }
    );
  }

  public getFiles(id) {
    console.log(this.actualId);
    this.fileService.getFilesByUser(id).subscribe(
      (response : any ) => {
        this.Files = response;

      },
      (error: HttpErrorResponse ) => {
        alert(error.message);
      }
    )
  }
  download(id: number) {
		this.fileService.getFileByid(id).subscribe((response: any) => {
      console.log(response);
      console.log(response.headers.get('Content-Disposition'));
      console.log(response.headers);
      let fileName=response.headers.get('Content-Disposition')?.split(':')[1].split('=')[1];
      let data = response.body
			//let blob:any = new Blob([response]);
      let a = document.createElement('a');
      a.download=fileName;
      a.href= window.URL.createObjectURL(data);
			a.click();
			//window.open(url);
			}), (error: any) => console.log('Error downloading the file'),
			() => console.info('File downloaded successfully');
	}
}
