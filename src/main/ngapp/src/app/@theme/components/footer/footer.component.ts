import { Component } from '@angular/core';

@Component({
  selector: 'ngx-footer',
  styleUrls: ['./footer.component.scss'],
  template: `
    <span class="created-by">Created with ♥ by <b><a href="https://jtosti.nl" target="_blank">Joostlek</a></b> 2018</span>
    <div class="socials">
    </div>
  `,
})
export class FooterComponent {
}
