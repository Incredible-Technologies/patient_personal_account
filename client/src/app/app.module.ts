import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { HomeComponent } from './modules/home/home.component';
import { LoginRegisterComponent } from './components/login-register/login-register.component';
import { MedicalCardComponent } from './components/medical-card/medical-card.component';
import { ChatComponent } from './components/chat/chat.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './core/interceptors/auth.interceptor';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ErrorInterceptor } from './core/interceptors/error.interceptor';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxSpinnerModule } from 'ngx-spinner';
import { LoadingInterceptor } from './core/interceptors/loading.interceptor';

@NgModule({
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  declarations: [
    AppComponent,
    HomeComponent,
    LoginRegisterComponent,
    MedicalCardComponent,
    ChatComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    CoreModule,
    SharedModule,
    HttpClientModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      positionClass: 'toast-top-center',
      preventDuplicates: true,
      timeOut: 3000,
      // other global options...
    }),
    NgxSpinnerModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
