import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  activeTab: 'upcoming' | 'past' = 'upcoming';
  constructor(private title: Title) { }
  
  ngOnInit() {
    this.title.setTitle('Личный кабинет пациента');
  }
}
