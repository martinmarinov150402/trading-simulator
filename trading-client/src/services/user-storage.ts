import { User } from "./auth";

const USER_KEY = "logged-user";

export class UserStorage {
  get current(): User | undefined {
    const user = localStorage.getItem(USER_KEY);
    if (!user) {
      return undefined;
    }

    return JSON.parse(user);
  }

  get token() {
    const token = localStorage.getItem(USER_KEY);

    if (!token) {
      return undefined;
    }

    return token;
  }


  save(user: User) {
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  remove() {
    localStorage.removeItem(USER_KEY);
  }
}
