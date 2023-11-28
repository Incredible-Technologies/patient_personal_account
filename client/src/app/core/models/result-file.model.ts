// result-file.model.ts
export interface ResultFile {
    id: number;
    name: string;
    originalFileName: string;
    size: number;
    contentType: string;
    bytes: Blob;
  }