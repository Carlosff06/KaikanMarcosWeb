export interface IUser {
    userid: string;
    password: string;
}

export interface ILogin {
    email: string;
    contrasenia: string;
}

export interface ILoginResponse {

    access_token: string;
    user_id:string;
    user_role:string;

}
