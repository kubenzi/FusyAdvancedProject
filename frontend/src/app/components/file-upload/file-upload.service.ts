import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserService} from '../../services/user-service';
import {AuthService} from '../../authentication/services/auth.service';
import {Bank, User} from '../../models/models';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FileUploadService {

  // API url
  baseApiUrl = 'http://localhost:8080/users/';


  constructor(private http: HttpClient, private userService: UserService, private authService: AuthService) {
  }

  // Returns an observable
  upload(file, bank): Observable<any> {

    // Create form data
    const formData = new FormData();

    // Store form name as "file" with file data
    formData.append('bank', bank);
    formData.append('file', file, 'newcsv.txt');
    console.log(file.name);

    // Make http post request over api
    // with formData as req
    return this.http.post(this.baseApiUrl + this.authService.getUserId() + this.userService.address + '/post-from-csv', formData);
  }


}
