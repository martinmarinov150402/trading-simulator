import { config } from '../config';
import { authService } from './auth';
import { UserStorage } from './user-storage';

type HttpMethod = 'GET' | 'POST' | 'PUT' | 'PATCH' | 'DELETE';

export class NotFoundError extends Error {}
export class ForbiddenError extends Error {}
export class UnauthorizedError extends Error {}
export class ConflictError extends Error {}
export class ServerError extends Error {}

interface RequestOptions {
  query?: object;
  body?: object;
}

class HttpService {
  async get<Result = unknown>(path: string, query?: object) {
    return this.request<Result>(path, 'GET', { query });
  }

  async post<Result = unknown>(path: string, body: object) {
    return this.request<Result>(path, 'POST', { body });
  }

  async put<Result = unknown>(path: string, body: object) {
    return this.request<Result>(path, 'PUT', { body });
  }

  async patch<Result = unknown>(path: string, body: object) {
    return this.request<Result>(path, 'PATCH', { body });
  }

  async delete<Result = unknown>(path: string) {
    return this.request<Result>(path, 'DELETE', {});
  }

  private async request<Result = unknown>(
    path: string,
    method: HttpMethod,
    options: RequestOptions
  ) {
    const headers = new Headers();

    if (options.body) {
      headers.append('Content-Type', 'application/json');
    }

    const userStorage = new UserStorage();
    const token = userStorage.token;

    if (token) {
      headers.append('Authorization', `Bearer ${token}`);
    }

    const baseUrl = config.baseUrl.replace(/\/+$/, '');
    const sanitizedPath = path.replace(/^\/+/, '');
    const queryParams = new URLSearchParams();

    if (options.query) {
      Object.entries(options.query).forEach(([key, value]) => {
        queryParams.append(key, `${value}`);
      });
    }

    const queryString = queryParams.toString();

    const response = await fetch(`${baseUrl}/${sanitizedPath}?${queryString}`, {
      method,
      headers,
      ...(options.body ? { body: JSON.stringify(options.body) } : {}),
    });

    if (!response.ok) {
      if (response.status === 401) {
        authService.logout();
        throw new UnauthorizedError();
      }

      if (response.status === 403) {
        throw new ForbiddenError();
      }

      if (response.status === 404) {
        throw new NotFoundError();
      }

      if (response.status === 409) {
        throw new ConflictError();
      }

      if (response.status === 500) {
        throw new ServerError();
      }

      throw new Error();
    }

    const data = (await response.json()) as Result;

    return data;
  }
}

export const httpService = new HttpService();
