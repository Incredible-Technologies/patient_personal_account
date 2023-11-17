import { Component, OnInit,HostListener } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {
  howFiller = false;
  navbarfixed: boolean = false;
  isExpanded = false;
  public isMenuCollapsed = true;

  @HostListener("window:scroll", ["$event"]) onscroll() {
    if (window.scrollY > 100) {
      this.navbarfixed = true;
    } else {
      this.navbarfixed = false;
    }
  }

  constructor(
    public authService: AuthService,
    private router: Router,
    private toastr: ToastrService) {}
  ngOnInit(): void {}

  collapse() {
    this.isExpanded = false;
  }

  toggle() {
    this.isExpanded = !this.isExpanded;
  }
  
  onLogout() {
    this.authService.logout();
    this.toastr.success('Вы успешно вышли из системы'); // "Successfully logged out" message
    this.router.navigate(['/login-reg']);
}

}
