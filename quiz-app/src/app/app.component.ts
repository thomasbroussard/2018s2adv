import { Component } from '@angular/core';
import { Question } from './datamodel/question.datamodel';
import { QuizService } from './services/quiz.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'my wonderful angular app';

  list: Question[];

  constructor(private service: QuizService) {

  }

  refreshList() {
    this.service.getQuestionList().subscribe(list => {
      this.list = list;
    });
  }
}
