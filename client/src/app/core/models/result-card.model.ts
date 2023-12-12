// result-card.model.ts
export interface ResultCard {
    id: number;
    name: string;
    description: string;
    dateOfMake: Date;
    dateOfShouldReady: Date;
    dateOfDelivered: Date;
    hospitalAddress: string;
    resultFileId?: number;
    userId: number;
  }

  // Add this interface in your models
export interface CreateResultCardRequest {
  name: string;
  description: string;
  dateOfShouldReady: string;
  hospitalAddress: string;
  userEmail: string;
}