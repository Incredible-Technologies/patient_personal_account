import { Component } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ProfileService } from 'src/app/core/services/profile.service';
import { ProfileData } from 'src/app/core/models/profile-data.model'; 

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  activeTab: 'upcoming' | 'past' = 'upcoming';
  profileData!: ProfileData; // Define a property to hold the profile data
  constructor(private title: Title, private profileService: ProfileService,) { }
  
  ngOnInit() {
    this.title.setTitle('Личный кабинет пациента');
    this.loadProfile();
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
}
