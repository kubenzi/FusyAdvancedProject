export interface Operation {
  id: number;
  description: string;
  value: number;
  date: string;
}

export interface Category {
  id: number;
  name: string;
  operations: Operation[];
}

export interface AccountType {
  id: number;
  name: string;
}

export interface Currency {
  id: number;
  name: string;
  signature: string;
}

export interface Account {
  id: number;
  name: string;
  balance: number;
  accountNumber: string;
  accountType: AccountType;
  currency: Currency;
  operations: Operation[];
}

export interface User {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  categories?: Category[];
  accounts?: Account[];
}

export class Series {
  name: string;
  value: number;

  constructor(name: string, value: number) {
    this.name = name;
    this.value = value;
  }
}

export interface Data {
  name: string;
  series: Series[];
}


export interface Bank {
  id: number,
  name: string
}

