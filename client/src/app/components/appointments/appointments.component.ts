import { Component } from '@angular/core';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.scss']
})
export class AppointmentsComponent {
  activeTab: 'upcoming' | 'past' = 'upcoming';

  constructor() { }
  bookNewAppointment() {
    // Logic to open appointment booking modal or navigate to booking page
  }

  viewDoctors() {
    // Logic to navigate to the list of available doctors
  }

}
