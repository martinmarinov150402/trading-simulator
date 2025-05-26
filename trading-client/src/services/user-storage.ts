const USER_KEY = "USER_TOKEN";

export class UserStorage {

  get token() {
    const token = localStorage.getItem(USER_KEY);

    if (!token) {
      return undefined;
    }

    return token;
  }


  save(token: string) {
    localStorage.setItem(USER_KEY, token);
  }

  remove() {
    localStorage.removeItem(USER_KEY);
  }
}
