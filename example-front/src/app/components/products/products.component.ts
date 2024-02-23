import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../models/product.model';

@Component({
  selector: 'component-products',
  standalone: true,
  imports: [],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css',
})
export class ProductsComponent implements OnInit {
  private products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.findAllProducts();
  }

  private findAllProducts() {
    this.productService.findAllProducts().subscribe({
      next: (response) => {
        this.products = response;
      },
    });
  }

  public getProducts() {
    return this.products;
  }
}
