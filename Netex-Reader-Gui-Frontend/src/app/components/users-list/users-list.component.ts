import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RoleData } from 'src/app/models/roleData';
import { User } from 'src/app/models/user';
import { UserData } from 'src/app/models/userData';
import { UserService } from "src/app/service/userservice/user.service";


@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {
  public users : User[] = [];
  public roles : RoleData[] = [];
  public editUser: UserData | undefined |null ;
  public actualUser: any;
  public actualEmail: string;
  public updatedId: number=0;
  public radioData : any;
  users1:any;

  searchText:String=''

  p: number = 1;

  sizePage:number=5;

  total:number=0;

  filterTerm!: string;


  constructor( private userService : UserService) { }

  ngOnInit(): void {
    
    this.getUsers();
  }

  onclickUpdate(user: UserData, id: number) {
    this.actualUser=user;
    this.updatedId=id;
  }

  onclickAddRole(email: string) {
    this.actualEmail= email;
  }

  public addRoleToUser(email:string) {

    this.userService.addRoleToUser(email, this.radioData).subscribe(
      Response=>{
        console.log("seccessfuly addedd role");
        this.getUsers();
      }
    )
  }

  changePage(event){
    this.p=event
    this.userService.getAllUsers(this.p-1,this.sizePage,this.searchText).subscribe(
      res=>{
        console.log(res)
        this.users1=res.content
        console.log();
      },
      err=>{
        console.log(err)
      })
  }
  search(event){
    this.userService.getAllUsers(0,this.sizePage,this.searchText).subscribe(
      res=>{
        console.log(res)
        this.users1=res.content
        this.total=res.totalElements
        this.p=0
      },
      err=>{
        console.log(err);
      })
  }
 
  taille_page(event){
    let size=Number((<HTMLInputElement>document.getElementById("sizePage")).value)
    if(size!=this.sizePage){
      this.sizePage=size
      this.userService.getAllUsers(0,size,this.searchText).subscribe(
        res=>{
          console.log(res)
          this.users=res.content
          this.p=0
        },
        err=>{
          console.log(err)
        })
    }
  }
  updateEnabel(id: number) {
    console.log(id);
    this.userService.enable(id).subscribe(
      (response: any) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    
  }
  updateDesabel(id: number) {
    console.log(id);
    this.userService.desable(id).subscribe(
      (response: any) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    
  }

   public getUsers(): void {
    this.userService.getAllUsers(0,5,'').subscribe(

      res=>{
        console.log(res);
        this.users1=res.content;
        this.total=res.totalElements;
      },
      err=>{
        console.log(err);
      }
    )    
   }

  public onUpdateUser(firstName: string, lastName: string, email: string, phoneNumber:string, id:string): void {
    let phoneNumber1=Number(phoneNumber);
    let id1 = Number(id)
    let user: UserData =  {
      firstName: firstName, lastName: lastName, email: email, phoneNumber: phoneNumber1,
      id: id1
    };
    this.userService.updateUser(user).subscribe(
      (response: UserData) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteUser(userId: number): void {
    this.userService.deleteUser(userId).subscribe(
      (response: void) => {
        console.log(response);
        this.getUsers();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
