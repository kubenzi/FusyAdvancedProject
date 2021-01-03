import { UserService, User, AccountType, Category, Currency, Operation, OperationType} from './services/user-service';

// @ts-ignore
export const USER_MOCK: User = {
  firstName: 'Bartosz',
  lastName: 'Jakimko',
  email: 'barjakimko@gmail.com',
  username: 'bj',
  categories: [
  {
    id: 1,
    name: 'Whores',
    operations: []
  },
  {
    id: 3,
    name: 'Alcohol',
    operations: []
  },
  {
    id: 5,
    name: 'Drugs',
    operations: []
  },
  {
    id: 7,
    name: 'Zigaretes',
    operations: []
  },
  {
    id: 9,
    name: 'Orzo',
    operations: [
      {
        id: 1,
        description: 'Dinner',
        value: 21.0,
        date: '2020-11-29T14:03:53.000+00:00',
        operationType: {
          id: 2,
          name: 'OUT'
        }
      }
    ]
  }
],
  accounts: [
  {
    id: 1,
    name: 'ING',
    balance: 100000,
    accountNumber: '1234567812345678',
    accountType: {
      id: 2,
      name: 'Current'
    },
    currency: {
      id: 2,
      name: 'Polish zloty',
      signature: 'PLN'
    },
    operations: [
      {
        id: 1,
        description: 'Dinner',
        value: 21.0,
        date: '2020-11-29T14:03:53.000+00:00',
        operationType: {
          id: 2,
          name: 'OUT'
        }
      }
    ]
  },
  {
    id: 2,
    name: 'PKO BP',
    balance: 10.0 ,
    accountNumber: '0000111122223333',
    accountType: {
      id: 1,
      name: 'Savings'
    },
    currency: {
      id: 2,
      name: 'Polish zloty',
      signature: 'PLN'
    },
    operations: []
  }
]
};
