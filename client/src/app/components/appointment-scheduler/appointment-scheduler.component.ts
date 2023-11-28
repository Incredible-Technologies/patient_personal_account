import { Component } from '@angular/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ProfileData } from 'src/app/core/models/profile-data.model';
import { ProfileService } from 'src/app/core/services/profile.service';

function getJsonFromUrl(url: string) {
  if (!url) url = location.search;
  var result: any = {};
  url.split('&').forEach(function (part) {
    var item = part.split('=');
    result[item[0]] = decodeURIComponent(item[1]);
  });
  return result;
}
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

const DOCTORS = [
  'Петров А.И.',
  'Сидоров Т.К.',
  'Андреев Ж.К.',
  'Васильев А.В.',
  'Терентьев А.Т.',
  'Богомолов Е. Е.',
  'Ольгина А. Г.',
  'Ефремов Е. А.',
  'Умнова А. А.',
  'Карагонова В. П.',
];

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
  profileData!: ProfileData; 
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
    'Симптомы ОРВИ/Грипп/COVID',
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
    ticketsLeft: Math.trunc(Math.random() * 100),
    isAllFieldsFilled: false,
    timetable: {
      isSelectedDay: 'empty',
    },
    dataSource: [DOCTORS_TIMETABLE[Math.round(Math.random() * 3)]],
    buttonToggle: false,
  };

  constructor(private router: Router, private profileService: ProfileService,) {}

  ngOnInit() {
    const paramsParts = this.router.url.split(';');
    this.loadProfile();

    const newDoctor: { doctorName: string } | undefined = getJsonFromUrl(
      paramsParts[1]
    );

    const newSpeciality: { speciality: string } | undefined = getJsonFromUrl(
      paramsParts[2]
    );

    if (newDoctor?.doctorName && newSpeciality?.speciality) {
      this.form.doctor = newDoctor?.doctorName;
      this.form.speciality = newSpeciality?.speciality;
    }
  }

  loadProfile(): void {
    this.profileService.getProfile().subscribe({
      next: (profile) => {
        if (profile) {
          //this.profileForm.patchValue(profile);
          this.profileData = profile;
        } else {
          //this.toastr.info("Пожалуйста, заполните информацию в вашем профиле.");
          // Redirect to profile completion page if needed
        }
      },
      error: (error) => {
        // Handle errors if needed
      }
    });
  }

  onReturnToAppointments() {
    this.router.navigate(['/appointments']);
  }

  updateSpeciality(e: Event) {
    this.form.speciality = (e.target as HTMLInputElement).value;
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
