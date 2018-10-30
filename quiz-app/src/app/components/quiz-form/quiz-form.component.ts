import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Question } from 'src/app/datamodel/question.datamodel';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-quiz-form',
  templateUrl: './quiz-form.component.html',
  styleUrls: ['./quiz-form.component.css']
})
export class QuizFormComponent implements OnInit {

  question: Question;

  @Output()
  refresh = new EventEmitter<boolean>();

  constructor(private service : QuizService) { }

  ngOnInit() {
    this.question = new Question("", "test");
  }

  save() {
    this.service.saveQuestion(this.question).subscribe(resp => {
      this.question.id = resp
      console.log(resp);
      this.question = new Question("","");
      this.refresh.next();
    });
  }

}
