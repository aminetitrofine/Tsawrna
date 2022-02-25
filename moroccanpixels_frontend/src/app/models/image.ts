export interface Image {
    id : number;
    owner : string;
    filePath : string;
    uploadedAt : Date;
    lastModified : Date;
    description : string;
    viewCount : number;
    saveCount : number;
    type : string;
    keywords : string[];
    saved : boolean;
}