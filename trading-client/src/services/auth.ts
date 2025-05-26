import { httpService } from "./http-service";
import { UserStorage } from "./user-storage";

export interface User {
  username: string;
  balance: number;

}

type OnChangeHandler = ((user: User | undefined) => unknown) | undefined;

const userStorage = new UserStorage();

class AuthService {
  private user: User | undefined;

  private onChange: OnChangeHandler;

  setOnChange(action: OnChangeHandler) {
    this.onChange = action;
  }

  async login(loginData: {username: string, password: string}) {
    const token = (await httpService.post<{token: string}>("/api/auth/login", loginData)).token;

    
    userStorage.save(token);
    this.user = await httpService.get<{username: string, balance: number}>("/api/user/profile");
    this.onChange?.(this.currentUser);
    console.log(this.currentUser);
  }

  logout() {
    this.user = undefined;
    userStorage.remove();
    this.onChange?.(this.currentUser);
  }

  async refreshUser() {
    this.user = await httpService.get<{username: string, balance: number}>("/api/user/profile");
    this.onChange?.(this.currentUser);
    console.log(this.currentUser);
  }
  get currentUser() {
    return this.user;
  }
}

export const authService = new AuthService();
