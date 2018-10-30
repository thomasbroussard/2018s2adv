import { Injectable } from '@angular/core';
import { Question } from '../datamodel/question.datamodel';
import { Http, Response } from '@angular/http';
import { Observable, of } from 'rxjs';
import { map, catchError} from 'rxjs/operators';
 
const endpoint = 'http://localhost:8080/quiz-rest-services/rest';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private http: Http) {
   }

   getQuestionList(): Observable<Question[]>{
     //todo fetch
    // return of(this.questions);
    return this.http.get(endpoint + '/questions').pipe(
      map(
        (response) => response.json().map(
          (item) => new Question(item.id, item.questionLabel)
      )
    ), catchError(err => of('error in get')));

   }

   saveQuestion (question : Question): Observable<string> {
    console.log("=> saveQuestion", question)
    let result = this.http.post(endpoint + '/questions', question).pipe(
      map(
        (response) => response.headers.get('Location')
    ), catchError(err => of('error : ${err}')));
    return result ;

   }
}
