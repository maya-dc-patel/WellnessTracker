import { Component, OnInit } from '@angular/core';
import { InventoryService } from './inventory.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  inventory: any;
  constructor(private invService: InventoryService) {}

  ngOnInit(): void {
    this.invService.getData().subscribe((data) => {
      console.log('data', data);
      this.inventory = data;
    });
  }
}
