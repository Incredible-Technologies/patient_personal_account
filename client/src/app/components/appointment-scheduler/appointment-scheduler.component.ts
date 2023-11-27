import { Component } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

interface DoctorTimetable {
  mnd: string;
  tsd: string;
  wnd: string;
  trd: string;
  frd: string;
  std: string;
  snd: string;
}

const DOCTORS_TIMETABLE: DoctorTimetable[] = [
  {
    mnd: '11:00 - 15-00',
    tsd: '9:00 - 19-00',
    wnd: '9:00 - 19-00',
    trd: '9:00 - 19-00',
    frd: '9:00 - 19-00',
    std: '9:00 - 12-00',
    snd: 'Выходной',
  },
  {
    mnd: '09:00 - 15-00',
    tsd: '14:00 - 19-00',
    wnd: 'Выходной',
    trd: '11:00 - 16-00',
    frd: '11:00 - 16-00',
    std: '11:00 - 19-00',
    snd: '10:00 - 14-00',
  },
  {
    mnd: '09:00 - 15-00',
    tsd: '11:00 - 16-00',
    wnd: 'Выходной',
    trd: '11:00 - 16-00',
    frd: '09:00 - 15-00',
    std: '11:00 - 19-00',
    snd: '10:00 - 14-00',
  },
  {
    mnd: 'Выходной',
    tsd: '14:00 - 19-00',
    wnd: 'Выходной',
    trd: '10:00 - 14-00',
    frd: '9:00 - 19-00',
    std: '14:00 - 19-00',
    snd: '11:00 - 16-00',
  },
];

const DOCTORS = ['А. И. Трубецкой', 'Э. В. Васильев'];

@Component({
  selector: 'app-appointment-scheduler',
  templateUrl: './appointment-scheduler.component.html',
  imports: [
    MatFormFieldModule,
    MatInputModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    CommonModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
  ],
  standalone: true,
  styleUrls: ['./appointment-scheduler.component.scss'],
})
export class AppointmentScheduler {
  specialities: string[] = [
    'АКУШЕР-ГИНЕКОЛОГ',
    'ВОП',
    'Вакцинация',
    'ДИСПАНСЕРИЗАЦИЯ -> ГИНЕКОЛОГИ',
    'Диспансеризация/Углуб. диспансеризация',
    'ИНФЕКЦИОНИСТ',
    'ОСТЕОПОРОЗ',
    'ТЕРАПЕВТ',
    'УРОЛОГ',
    'Бокс, симптомы ОРВИ/Грипп/COVID',
    'КАРДИОЛОГ',
    'ОТОЛАРИНГОЛОГ',
    'ОФТАЛЬМОЛОГ',
    'ПСИХОЛОГ',
    'ХИРУРГ',
    'ЭНДОКРИНОЛОГ',
  ];

  user = {
    firstName: 'Иван',
    lastName: 'Иванов',
  };
  minDate = new Date();

  displayedColumns: string[] = [
    'mnd',
    'tsd',
    'wnd',
    'trd',
    'frd',
    'std',
    'snd',
  ];

  doctors = DOCTORS;

  form = {
    speciality: 'empty',
    doctor: 'empty',
    date: 'empty',
    ticketsLeft: -1,
    isAllFieldsFilled: false,
    timetable: {
      isSelectedDay: 'empty',
    },
    dataSource: [DOCTORS_TIMETABLE[Math.round(Math.random() * 3)]],
    buttonToggle: false,
  };

  updateSpeciality(e: Event) {
    this.form.speciality = (e.target as HTMLInputElement).value;
    console.log(this.form.speciality);
    this.form.isAllFieldsFilled =
      !!this.form.date &&
      this.form.speciality !== 'empty' &&
      this.form.doctor !== 'empty' &&
      this.form.date !== 'empty' &&
      this.form.timetable.isSelectedDay !== 'empty';
  }

  updateDoctor(e: Event) {
    this.form.doctor = (e.target as HTMLInputElement).value;
    this.form.ticketsLeft = Math.trunc(Math.random() * 100);
    this.form.isAllFieldsFilled =
      !!this.form.date &&
      this.form.speciality !== 'empty' &&
      this.form.doctor !== 'empty' &&
      this.form.date !== 'empty' &&
      this.form.timetable.isSelectedDay !== 'empty';
    this.form.dataSource = [DOCTORS_TIMETABLE[Math.round(Math.random() * 3)]];
  }

  updateDate(e: any) {
    this.form.date = e.target.value;
    this.form.isAllFieldsFilled =
      !!this.form.date &&
      this.form.speciality !== 'empty' &&
      this.form.doctor !== 'empty' &&
      this.form.date !== 'empty' &&
      this.form.timetable.isSelectedDay !== 'empty';
  }

  onClick(dayOfWeek: string) {
    this.form.timetable.isSelectedDay = dayOfWeek;
    this.form.isAllFieldsFilled =
      !!this.form.date &&
      this.form.speciality !== 'empty' &&
      this.form.doctor !== 'empty' &&
      this.form.date !== 'empty' &&
      this.form.timetable.isSelectedDay !== 'empty';
  }

  onSubmit() {
    const {
      form: {
        doctor,
        speciality,
        date,
        timetable: { isSelectedDay },
      },
    } = this;

    this.form.buttonToggle = !this.form.buttonToggle;

    setTimeout(() => {
      this.form.buttonToggle = !this.form.buttonToggle;
    }, 1000);
    // TODO: send the request to create new visit.
  }
}
