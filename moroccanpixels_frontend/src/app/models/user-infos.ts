export class UserInfos {
  firstName!:string
  lastName!:string
  username!:string
  email!:string;
  private birthDate:Date = new Date();
  private role!:string;
  private password!:string;
  private passwordConfirmation!:string;
}
