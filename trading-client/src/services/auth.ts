import { httpService } from "./http-service";
import { UserStorage } from "./user-storage";

export interface User {
  username: string;
  password: string;
}

type OnChangeHandler = ((user: User | undefined) => unknown) | undefined;

const userStorage = new UserStorage();

class AuthService {
  private user: User | undefined;

  private onChange: OnChangeHandler;

  setOnChange(action: OnChangeHandler) {
    this.onChange = action;
  }

  async login(user: User) {
    const token = await httpService.post("/login", {
      username: user.username,
      password: user.password,
    })

    console.log(token);
    /*this.user = user;
    userStorage.save(user);*/
    //this.onChange?.(this.currentUser);
  }

  logout() {
    this.user = undefined;
    userStorage.remove();
    this.onChange?.(this.currentUser);
  }

  get currentUser() {
    return this.user;
  }
}

export const authService = new AuthService();
