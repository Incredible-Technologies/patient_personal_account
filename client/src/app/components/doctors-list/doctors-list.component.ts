import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctors-list',
  templateUrl: './doctors-list.component.html',
  styleUrls: ['./doctors-list.component.scss'],
})
export class DoctorsList {
  constructor(private router: Router) {}

  onDoctorClick(doctorName: string, speciality: string) {
    this.router.navigate([
      '/schedule-an-appointment',
      { doctorName, speciality },
    ]);
  }

  onReturnToAppointments() {
    this.router.navigate(['/appointments']);
  }
}
