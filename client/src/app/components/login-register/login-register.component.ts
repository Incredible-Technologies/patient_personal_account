import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { ToastrService } from 'ngx-toastr';
import { confirmPasswordValidator } from 'src/app/shared/components/custom-validators/custom-validators';


@Component({
  selector: 'app-login-register',
  templateUrl: './login-register.component.html',
  styleUrls: ['./login-register.component.scss'],
})



export class LoginRegisterComponent implements OnInit {
  activeTab: 'login' | 'register' = 'login';
  loginForm!: FormGroup;
  registerForm!: FormGroup;

  loginErrorMessage: string = '';
  registerErrorMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/']);
    }

    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
    });

    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]], // Example: Minimum length
      confirmPassword: ['', Validators.required]
    }, { validator: confirmPasswordValidator('password', 'confirmPassword') });
    
  }

  onLogin() {
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          localStorage.setItem('authToken', response.token);
          this.router.navigate(['/']); // Navigate to home
          this.toastr.success('Успешный вход в систему!'); // "Logged in successfully!" in Russian
        },
        error: (error) => {
          this.loginErrorMessage = error;
          this.toastr.error('Ошибка входа'); // "Login failed" in Russian
        }
      });
    }
  }
  
  onRegister() {
    if (this.registerForm.valid) {
      this.authService.register(this.registerForm.value).subscribe({
        next: (response) => {
          this.router.navigate(['/']); // Navigate to home
          this.toastr.success('Регистрация прошла успешно!'); // "Registered successfully!" in Russian
        },
        error: (error) => {
          this.registerErrorMessage = error;
          this.toastr.error('Регистрация не удалась'); // "Registration failed" in Russian
        }
      });
    }
  }
    
}
