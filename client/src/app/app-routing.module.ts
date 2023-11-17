import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { MedicalCardComponent } from './components/medical-card/medical-card.component';
import { ChatComponent } from './components/chat/chat.component';
import { AuthGuard } from './core/guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: '/login-reg', pathMatch: 'full' },
  { path: 'login-reg', component: LoginRegisterComponent },
  { path: 'profile', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'electronic-medical-card', component: MedicalCardComponent, canActivate: [AuthGuard] },
  { path: 'chat', component: ChatComponent, canActivate: [AuthGuard] },
  // ... other routes ...
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabledBlocking'
})],
  exports: [RouterModule],
})
export class AppRoutingModule {}
