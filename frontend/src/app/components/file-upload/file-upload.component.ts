import { Component, OnInit } from '@angular/core';
import { FileUploadService } from './file-upload.service';
import {Bank} from '../../models/models';
import {HttpClient} from '@angular/common/http';
import {UserService} from '../../services/user-service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  // Variable to store shortLink from api response
  shortLink = '';
  loading = false; // Flag variable
  file: File = null; // Variable to store file
  banks$: Bank[];
  bank: Bank;

  // Inject service
  constructor(private fileUploadService: FileUploadService, private http: HttpClient, private userService: UserService) { }

  ngOnInit(): void {
    this.setBanks$();
  }

  // On file Select
  onChange(event): void {
    // this.bank = event.target.files[0];
    this.file = event.target.files[0];
  }

  // OnClick of button Upload
  onUpload(): void {
    this.loading = !this.loading;
    console.log(this.file);
    this.fileUploadService.upload(this.file).subscribe(
      (event: any) => {
        if (typeof (event) === 'object') {

          // Short link via api response
          this.shortLink = event.link;

          this.loading = false; // Flag variable
        }
      }
    );
  }

  setBanks$(): void {
    const url = 'http://localhost:8080/api/v1/banks';
    this.http.get<Bank[]>(url).subscribe(value => this.banks$ = value);
  }

}
