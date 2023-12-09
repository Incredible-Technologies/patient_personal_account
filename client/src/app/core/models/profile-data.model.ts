// profile-data.model.ts

export interface ProfileData {
  firstName: string;
  middleName: string;
  lastName: string;
  address: string;
  dateOfBirth: string; // Ensure this is in 'dd/MM/yyyy' format
  phoneNumber: string;
  gender: Gender;
  mriImageUrl: string;
}

export enum Gender {
  MALE = 'Мужской',
  FEMALE = 'Женский',
  // ... other gender values as per your backend ...
}
