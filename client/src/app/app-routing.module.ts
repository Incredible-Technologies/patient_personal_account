import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './modules/home/home.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { MedicalCardComponent } from './components/medical-card/medical-card.component';

const routes: Routes = [
  { path: '', component: HomeComponent, pathMatch: 'full' },
  { path: 'login-reg', component: LoginRegisterComponent },
  {path: 'electronic-medical-card', component: MedicalCardComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
