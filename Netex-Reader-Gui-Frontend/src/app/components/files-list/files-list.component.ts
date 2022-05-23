import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FilesService } from 'src/app/service/fileService/files.service';

@Component({
  selector: 'app-files-list',
  templateUrl: './files-list.component.html',
  styleUrls: ['./files-list.component.css']
})
export class FilesListComponent implements OnInit {

  constructor(private fileService : FilesService) { }
  Files : any;
  p: number = 1;
  filterTerm!: string;

  ngOnInit(): void {
    this.getFiles();
  }

  public getFiles(): void {
    this.fileService.getFiles().subscribe(
      (response : any ) => {
        this.Files = response;
        console.log(response);
      },
      (error: HttpErrorResponse ) => {
        alert(error.message);
      }
    )
  }
  public getFileById(id: number) {
    this.fileService.getFileByid(id).subscribe(
      (response : any ) => {
        console.log(response);
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
