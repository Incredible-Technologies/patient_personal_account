import { FormGroup } from '@angular/forms';

export function confirmPasswordValidator(controlName: string, matchingControlName: string) {
    return (formGroup: FormGroup) => {
      const control = formGroup.controls[controlName];
      const matchingControl = formGroup.controls[matchingControlName];
  
      if (matchingControl.errors && !matchingControl.errors['confirmPasswordValidator']) {
        // return if another validator has already found an error on the matchingControl
        return;
      }
  
      // set error on matchingControl if validation fails
      if (control.value !== matchingControl.value) {
        matchingControl.setErrors({ confirmPasswordValidator: true });
      } else {
        matchingControl.setErrors(null);
      }
    };
  }
  