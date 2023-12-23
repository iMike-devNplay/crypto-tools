import { Component } from '@angular/core';
import { CryptoToolsService } from './services/crypto-tools.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Crypto Tools';

  constructor(private cryptoToolsService: CryptoToolsService){}

  ngOnInit(): void {
    setTimeout(() => {});
  }
}
