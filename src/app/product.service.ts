import { Injectable } from '@angular/core';
import { IProduct } from './product';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private getProductsUrl = 'http://localhost:8080/api/v1/product/getProducts';
  private getUnitPriceUrl = 'http://localhost:8080/api/v1/pricing/calculateUnitPrice';
  private calculatePriceUrl = 'http://localhost:8080/api/v1/pricing/calculate';
  private saveProductUrl = 'http://localhost:8080/api/v1/product/save'

  constructor(private http: HttpClient) {}

  saveProduct(product: any, image: File): Observable<any> {
    const formData = new FormData();
    formData.append('name', product.name);
    formData.append('quantity', product.quantity.toString());
    formData.append('cartonPrice', product.cartonPrice.toString());
    formData.append('unitsPerCarton', product.unitsPerCarton.toString());
    formData.append('description', product.description);
    formData.append('unitPriceMarkup', product.unitPriceMarkup.toString());
    formData.append('cartonDiscountPercentage', product.cartonDiscountPercentage.toString());
    formData.append('image', image, image.name);

    return this.http.post(this.saveProductUrl, formData).pipe(
      catchError(this.handleError)
    );
  }

  getProducts(): Observable<IProduct[]> {
    return this.http.get<IProduct[]>(this.getProductsUrl).pipe(
      tap(data => console.log('Fetched Products:', JSON.stringify(data))),
      catchError(this.handleError)
    );
  }

  calculatePrice(productName: string, quantity: number): Observable<number> {
    const url = `${this.calculatePriceUrl}?productName=${productName}&quantity=${quantity}`;
    return this.http.get<number>(url).pipe(
      tap(data => console.log(`Calculated Price for ${quantity} of ${productName}:`, data)),
      catchError(this.handleError)
    );
  }

  getUnitPrice(name: string): Observable<number> {
    const url = `${this.getUnitPriceUrl}?name=${name}`;
    return this.http.get<number>(url).pipe(
      tap(data => console.log(`Unit Price for ${name}:`, data)),
      catchError(this.handleError)
    );
  }

  private handleError(err: HttpErrorResponse) {
    let errorMessage = '';
    if (err.error instanceof ErrorEvent) {
      errorMessage = `An error occurred: ${err.error.message}`;
    } else {
      errorMessage = `Server returned code ${err.status}, error message: ${err.message}`;
    }
    console.error(errorMessage);
    return throwError(() => errorMessage);
  }
}
