import { RoleData } from "./roleData";

export interface User {
    id:number
    firstName: string;
    lastName: string;
    email : string;
    phoneNumber:number;
    roles : RoleData[];
}