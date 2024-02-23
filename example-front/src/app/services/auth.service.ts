import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { apiRequests } from '../constants/api-backend';
import { ILogin, ITokenResponse } from '../models/auth.model';
import { map } from 'rxjs';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient, private tokenService: TokenService) {}

  public onLogin(username: string, password: string) {
    const data: ILogin = {
      username,
      password,
    };
    return this.http
      .post<ITokenResponse>(`${apiRequests.authEndpoint.login}`, data)
      .pipe(
        map((response) => {
          console.log(response);
          if (response) {
            this.tokenService.setToken(response.token);
          }
          return response;
        })
      );
  }

  public onLogout() {
    this.tokenService.removeToken();
  }
}
