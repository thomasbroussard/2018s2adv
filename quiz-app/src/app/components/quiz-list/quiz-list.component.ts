import { Component, OnInit, Input, OnChanges, SimpleChange, SimpleChanges } from '@angular/core';
import { Question } from 'src/app/datamodel/question.datamodel';
import { QuizService } from 'src/app/services/quiz.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-quiz-list',
  templateUrl: './quiz-list.component.html',
  styleUrls: ['./quiz-list.component.css']
})
export class QuizListComponent implements OnInit, OnChanges {
  


  @Input()
  questions:Question[];


  ngOnChanges(changes: SimpleChanges){
    this.questions = changes.questions.currentValue;
  }
  
  constructor(private service: QuizService) { }

  ngOnInit() {
    this.service.getQuestionList().subscribe(questions =>{
      this.questions = questions;
    });
  }

}
