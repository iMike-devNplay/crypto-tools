import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {Buffer} from 'buffer';
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
    //let buff: Buffer = Buffer.from("cryptotools:crypt0t00l$DEV", 'base64');
    let pass:string = btoa("cryptotools:crypt0t00l$DEV");
    httpHeaders = httpHeaders.append("Authentication", `Basic ${pass}`)
          //.set('Content-Type', 'application/json'); //application/json
    return httpHeaders;
  }

  encrypt(version: string, cryptoDto: CryptoDto): Observable<CryptoDto>
  {
    //let queryParams = new HttpParams().append("username", username);
    let options = { headers: this.initHeader()};
    return this.http.post<CryptoDto>(encodeURI(`${this.baseUrl}/api/encrypt/${version}`), cryptoDto, options);
  }

  decrypt(version: string, cryptoDto: CryptoDto): Observable<CryptoDto>
  {
    //let queryParams = new HttpParams().append("username", username);
    let options = { headers: this.initHeader()};
    return this.http.post<CryptoDto>(encodeURI(`${this.baseUrl}/api/decrypt/${version}`), cryptoDto, options);
  }
}
