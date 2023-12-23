import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CryptoDto } from '../models/crypto-dto';

@Injectable({
  providedIn: 'root'
})
export class CryptoToolsService {
  baseUrl = environment.apiURL;
  constructor(private http: HttpClient) { }

  initHeader(): HttpHeaders
  {
    let httpHeaders = new HttpHeaders();
          //.set('Content-Type', 'application/json'); //application/json
    return httpHeaders;
  }

  encrypt(cryptoDto: CryptoDto): Observable<CryptoDto>
  {
    //let queryParams = new HttpParams().append("username", username);
    let options = { headers: this.initHeader()};
    return this.http.post<CryptoDto>(encodeURI(`${this.baseUrl}/api/encrypt`), cryptoDto, options);
  }

  decrypt(cryptoDto: CryptoDto): Observable<CryptoDto>
  {
    //let queryParams = new HttpParams().append("username", username);
    let options = { headers: this.initHeader()};
    return this.http.post<CryptoDto>(encodeURI(`${this.baseUrl}/api/decrypt`), cryptoDto, options);
  }
}
