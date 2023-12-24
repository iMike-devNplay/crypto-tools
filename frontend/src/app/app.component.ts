import { Component } from '@angular/core';
import { CryptoToolsService } from './services/crypto-tools.service';
import { CryptoDto } from './models/crypto-dto';
import { AlertService } from './services/alert.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent 
{
  title = 'Crypto Tools';

  masterKey: string = "";
  password: string = "";
  result: string = "";

  constructor(private cryptoToolsService: CryptoToolsService, private alertService: AlertService){}

  ngOnInit(): void {
    setTimeout(() => {});
  }

  onEncrypt()
  {
    this.cryptoToolsService.encrypt(new CryptoDto(this.masterKey, this.password)).subscribe(
      data => 
      {
        this.result = data.password;
      }
    );
  }

  onDecrypt()
  {
    this.cryptoToolsService.decrypt(new CryptoDto(this.masterKey, this.password)).subscribe(
      data => 
      {
        this.result = data.password;
      }
    );
  }

  onClear()
  {
    this.masterKey = "";
    this.password = "";
    this.result = "";
    navigator.clipboard.writeText("");
    this.alertService.openSnackBar("Clipboard cleared !");
  }


  onCopy(event: any)
  {
    navigator.clipboard.writeText(this.result);
    this.alertService.openSnackBar("Copied into clipboard !");
  }
}
