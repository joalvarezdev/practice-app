import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { constants } from '../constants/general';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private isAuthenticated = new BehaviorSubject<boolean>(false);

  constructor() {
    const token = this.getToken();
    if (token) {
      this.updateToken(true);
    }
  }

  public getToken(): string | null {
    return localStorage.getItem(constants.CURRENT_TOKEN);
  }

  public updateToken(value: boolean) {
    this.isAuthenticated.next(value);
  }

  public setToken(token: string) {
    this.updateToken(true);
    localStorage.setItem(constants.CURRENT_TOKEN, token);
  }

  public removeToken() {
    this.updateToken(false);
    localStorage.removeItem(constants.CURRENT_TOKEN);
  }

  public getIsAuthenticated(): BehaviorSubject<boolean> {
    return this.isAuthenticated;
  }
}
