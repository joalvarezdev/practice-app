import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../models/product.model';
import { Observable } from 'rxjs';
import { apiRequests } from '../constants/api-backend';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  findAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${apiRequests.products.getAll}`);
  }
}
