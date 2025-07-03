const authUrl = import.meta.env.VITE_AUTH_URL

export const registerUserEndpoint = `${authUrl}/user/register`
export const loginUserEndpoint = `${authUrl}/user/login`