import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'component-default',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './default.component.html',
  styleUrl: './default.component.css',
})
export class DefaultComponent {}
