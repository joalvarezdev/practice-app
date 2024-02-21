import { Component, Input } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { SidenavComponent } from '../../../components/sidenav/sidenav.component';
import { SidebarToggle } from '../../../models/sidebar.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'component-principal',
  standalone: true,
  imports: [RouterOutlet, SidenavComponent, CommonModule],
  templateUrl: './principal.component.html',
  styleUrl: './principal.component.css',
})
export class PrincipalComponent {
  collapsed = false;
  screenWidth = 0;

  public onToggleSidebar(data: SidebarToggle) {
    this.collapsed = data.collapsed;
    this.screenWidth = data.screenWidth;
  }

  public isLargeScreen(): boolean {
    // console.log('LARGE');
    // console.log(this.collapsed);
    // console.log(this.screenWidth);
    return this.collapsed && this.screenWidth > 768;
  }

  public isSmallScreen(): boolean {
    // console.log('SMALL');
    // console.log(this.collapsed);
    // console.log(this.screenWidth);
    return this.collapsed && this.screenWidth < 768 && this.screenWidth > 0;
  }
}
