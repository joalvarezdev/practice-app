export interface ILogin {
  username: string;
  password: string;
}

export interface ITokenResponse {
  token_type: string;
  expires_in: number;
  token: string;
}
