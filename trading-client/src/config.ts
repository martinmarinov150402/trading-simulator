function getRequired(key: string) {
  const value = process.env[key] as string | undefined;

  if (!value) {
    throw new Error(`Env variable missing ${key}`);
  }

  return value;
}

export const config = {
  baseUrl: getRequired('BASE_API_URL'),
};
