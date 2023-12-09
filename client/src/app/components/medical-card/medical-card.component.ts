import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProfileService } from 'src/app/core/services/profile.service';
import { ProfileData } from 'src/app/core/models/profile-data.model';
import { ResultCard } from 'src/app/core/models/result-card.model';
import { ToastrService } from 'ngx-toastr';
import { ResultCardService } from 'src/app/core/services/result-card.service';
import * as moment from 'moment';
import { AuthService } from 'src/app/core/services/auth.service';


@Component({
  selector: 'app-medical-card',
  templateUrl: './medical-card.component.html',
  styleUrls: ['./medical-card.component.scss'],
})
export class MedicalCardComponent implements OnInit {
  profileForm!: FormGroup;
  isEditing = false;
  profileData!: ProfileData; // Define a property to hold the profile data

  resultCards: ResultCard[] = [];
  doneResultCards: ResultCard[] = [];
  notDoneResultCards: ResultCard[] = [];
  isEditingResultCard = false;
  resultCardForm!: FormGroup; // Add FormGroup for result card editing
  createResultCardForm!: FormGroup; // Form for creating a new result card
  isCreatingResultCard = false; // For showing the create result card form
  selectedResultCard: ResultCard | null = null; // To hold the selected card for editing

  constructor(
    private fb: FormBuilder,
    private profileService: ProfileService,
    private toastr: ToastrService, // Inject ToastrService
    private resultCardService: ResultCardService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    this.loadProfile();
    this.loadAllUserCards();
    this.loadAllDoneUserCards();
    this.loadAllNotDoneUserCards();
    this.initializeCreateResultCardForm();
    this.resultCardService.getAllUserCards().subscribe({
      next: (response) => {
        console.log(response); // Check the structure of the response
        this.resultCards = response.resultCardList; // Assuming the response has a property 'resultCardList'
      },
      error: (error) => console.error('Error fetching result cards:', error),
    });
  }

  initializeForm(): void {
    this.profileForm = this.fb.group({
      firstName: [''], // Add default values as needed
      middleName: [''], // Add default values as needed
      lastName: [''], // Add default values as needed
      dateOfBirth: [''], // Add default values as needed
      gender: [''], // Add default values as needed
      address: [''], // Add default values as needed
      phoneNumber: [''], // Add default values as needed
    });
  }

  

  // In MedicalCardComponent
  initializeCreateResultCardForm(): void {
    const userEmail = this.authService.getCurrentUserEmail();
    this.createResultCardForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      dateOfShouldReady: ['', Validators.required], // Use a suitable format (e.g., 'YYYY-MM-DD')
      hospitalAddress: ['', Validators.required],
      userEmail: [userEmail, [Validators.required, Validators.email]]
    });
  }

  loadProfile(): void {
    this.profileService.getProfile().subscribe({
      next: (profile) => {
        if (profile) {
          this.profileForm.patchValue(profile);
          // Add the email to profileData
          this.profileData = { ...profile, email: this.authService.getCurrentUserEmail() || '' };
        } else {
          this.toastr.info('Пожалуйста, заполните информацию в вашем профиле.');
          // Redirect to profile completion page if needed
        }
      },
      error: (error) => {
        // Handle errors if needed
      },
    });
  }
  

  toggleEdit(): void {
    this.isEditing = !this.isEditing;
    if (!this.isEditing) {
      // Reload profile data when exiting edit mode
      this.loadProfile();
    }
  }

  saveProfile(): void {
    if (this.profileForm.valid) {
      // Format the dateOfBirth field
      const formattedProfileData = {
        ...this.profileForm.value,
        dateOfBirth: this.profileForm.value.dateOfBirth 
          ? moment(this.profileForm.value.dateOfBirth).format('DD/MM/YYYY') 
          : ''
      };
  
      this.profileService.saveProfile(formattedProfileData).subscribe(() => {
        this.toggleEdit();
        this.loadProfile(); // Reload the profile data to reflect the changes
        this.toastr.success('Profile updated successfully');
      }, error => {
        this.toastr.error('Failed to update profile');
        console.error('Error updating profile:', error);
      });
    }
  }
  

  
  createResultCard(): void {
    if (this.createResultCardForm.valid) {
      // Format the date to match the backend's expected format
      const formattedDate = moment(this.createResultCardForm.value.dateOfShouldReady).format('DD-MM-YYYY');
      
      // Create a new object with the formatted date
      const formData = {
        ...this.createResultCardForm.value,
        dateOfShouldReady: formattedDate
      };
  
      this.resultCardService.createResultCard(formData).subscribe({
        next: (newResultCard) => {
          this.toastr.success('Result card created successfully');
          this.toggleCreateResultCardForm(); // Close the form and reload the list
          this.loadAllUserCards(); // Reload the cards list
        },
        error: (error) => {
          this.toastr.error('Failed to create result card');
          console.error('Error creating result card:', error);
        }
      });
    } else {
      this.toastr.error('Please fill in all required fields');
    }
  }
  


  toggleCreateResultCardForm(): void {
    console.log("Toggling Create Result Card Form");
    this.isCreatingResultCard = !this.isCreatingResultCard;
    if (!this.isCreatingResultCard) {
      console.log("Loading user cards...");
      this.loadAllUserCards(); // This should be triggered when the form is closed
    }
  }

  loadAllUserCards() {
    this.resultCardService.getAllUserCards().subscribe({
      next: (response) => (this.resultCards = response.resultCardList),
      error: (error) => console.error('Error fetching result cards:', error),
    });
  }

  loadAllDoneUserCards() {
    this.resultCardService.getAllDoneUserCards().subscribe({
      next: (response) => (this.doneResultCards = response.resultCardList),
      error: (error) =>
        console.error('Error fetching done result cards:', error),
    });
  }

  loadAllNotDoneUserCards() {
    this.resultCardService.getAllNotDoneUserCards().subscribe({
      next: (response) => (this.notDoneResultCards = response.resultCardList),
      error: (error) =>
        console.error('Error fetching not done result cards:', error),
    });
  }

  // In MedicalCardComponent

downloadResultFile(cardId: number): void {
  this.resultCardService.downloadResultFile(cardId).subscribe(blob => {
    // Create a new Blob object
    const file = new Blob([blob], { type: 'application/pdf' }); // Adjust the type as necessary

    // Create a URL for the file
    const fileURL = URL.createObjectURL(file);

    // Create a temporary anchor element and trigger the download
    const anchor = document.createElement('a');
    anchor.href = fileURL;
    anchor.download = `result-card-${cardId}.pdf`; // Set the filename as you wish
    document.body.appendChild(anchor);
    anchor.click();

    // Cleanup: remove the anchor and revoke the URL
    document.body.removeChild(anchor);
    URL.revokeObjectURL(fileURL);
  }, error => {
    console.error('Error downloading file:', error);
    this.toastr.error('Failed to download file');
  });
}


  

  
}
