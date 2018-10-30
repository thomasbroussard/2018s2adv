export class Question{
  questionLabel: string;
  id: string;

  constructor(id: string, label:string){
    this.id = id;
    this.questionLabel = label;
  }

}