// import { Component, OnInit } from '@angular/core';

// @Component({
//   selector: 'app-root',
//   templateUrl: './app.component.html',
//   styleUrls: ['./app.component.scss']
// })
// export class AppComponent implements OnInit {
//   theme: string = 'light-theme'; // default theme

//   ngOnInit() {
//     // Check for saved theme preference in local storage
//     const savedTheme = localStorage.getItem('theme');
//     if (savedTheme) {
//       this.theme = savedTheme;
//       document.body.classList.add(this.theme);
//     } else {
//       // Check for system preference
//       const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
//       this.theme = prefersDark ? 'dark-theme' : 'light-theme';
//       document.body.classList.add(this.theme);
//     }
//   }

//   toggleTheme() {
//     this.theme = this.theme === 'light-theme' ? 'dark-theme' : 'light-theme';
//     document.body.className = this.theme;
//     localStorage.setItem('theme', this.theme);
//   }
// }

import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  theme: string = 'light-theme'; // default theme

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit() {
    if (isPlatformBrowser(this.platformId)) {
      // Only execute this code in the browser
      const savedTheme = localStorage.getItem('theme');
      if (savedTheme) {
        this.theme = savedTheme;
        document.body.classList.add(this.theme);
      } else {
        // Check for system preference
        const prefersDark = window.matchMedia('(prefers-color-scheme: dark)').matches;
        this.theme = prefersDark ? 'dark-theme' : 'light-theme';
        document.body.classList.add(this.theme);
      }
    }
  }

  toggleTheme() {
    if (isPlatformBrowser(this.platformId)) {
      // Only execute this code in the browser
      this.theme = this.theme === 'light-theme' ? 'dark-theme' : 'light-theme';
      document.body.className = this.theme;
      localStorage.setItem('theme', this.theme);
    }
  }
}
