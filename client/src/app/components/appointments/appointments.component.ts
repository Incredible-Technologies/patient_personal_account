import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.scss']
})
export class AppointmentsComponent {
  activeTab: 'upcoming' | 'past' = 'upcoming';

  constructor(private router: Router) { }
  bookNewAppointment() {
    this.router.navigate(['/schedule-an-appointment']);
  }

  viewDoctors() {
    // Logic to navigate to the list of available doctors
  }

}
