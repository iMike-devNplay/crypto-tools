export class CryptoDto {
    masterKey: string;
    password: string;

    constructor(masterKey: string, password: string)
    {
        this.masterKey = masterKey;
        this.password = password;
    }
}
