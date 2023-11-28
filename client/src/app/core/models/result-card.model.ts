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