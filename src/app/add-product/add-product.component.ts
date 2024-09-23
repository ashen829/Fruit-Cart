import { Component } from '@angular/core';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {
  product = {
    name: '',
    quantity: 0,
    cartonPrice: 0,
    unitsPerCarton: 0,
    description: '',
    unitPriceMarkup: 0,
    cartonDiscountPercentage: 0,
  };
  
  selectedFile: File | null = null;

  constructor(private productService: ProductService, private router: Router) {}

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  onSubmit(): void {
    if (this.selectedFile) {
      this.productService.saveProduct(this.product, this.selectedFile).subscribe({
        next: () => {
          alert('Product added successfully!');
          this.router.navigate(['./shop-now']);
        },
        error: (err) => {
          console.error('Error saving product:', err);
        }
      });
    } else {
      alert('Please upload a product image!');
    }
  }
}
