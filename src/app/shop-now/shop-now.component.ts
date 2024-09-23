import { Component, OnInit, OnDestroy } from '@angular/core';
import { IProduct } from '../product';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-shop-now',
  templateUrl: './shop-now.component.html',
  styleUrls: ['./shop-now.component.css']
})
export class ShopNowComponent implements OnInit, OnDestroy {

  products: IProduct[] = [];
  unitPrices: { [key: string]: number } = {};
  checkoutList: { product: IProduct, quantity: number, price: number }[] = [];
  sub!: Subscription;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.sub = this.productService.getProducts().subscribe({
      next: (products) => {
        this.products = products;
        this.products.forEach((product) => {
          this.productService.getUnitPrice(product.name).subscribe({
            next: (price) => {
              this.unitPrices[product.name] = price;
              console.log(this.unitPrices);
            },
            error: (err) => (this.errorMessage = err)
          });
        });
      },
      error: (err) => (this.errorMessage = err)
    });
  }

  ngOnDestroy(): void {
    if (this.sub) {
      this.sub.unsubscribe();
    }
  }

  addToCheckout(product: IProduct): void {
    const existingProduct = this.checkoutList.find(item => item.product.name === product.name);

    if (existingProduct) {
      existingProduct.quantity++;
      this.updatePrice(existingProduct);
    } else {
      const newItem = { product, quantity: 1, price: 0 };
      this.updatePrice(newItem);
      this.checkoutList.push(newItem);
    }
  }

  updatePrice(item: { product: IProduct, quantity: number, price: number }): void {
    this.productService.calculatePrice(item.product.name, item.quantity).subscribe({
      next: (price) => {
        item.price = price;
        console.log(`Updated price for ${item.product.name}: ${price}`);
      },
      error: (err) => (this.errorMessage = err)
    });
  }

  removeFromCheckout(product: IProduct): void {
    const index = this.checkoutList.findIndex(item => item.product.name === product.name);
    if (index !== -1) {
      this.checkoutList.splice(index, 1);
    }
  }

  increaseQuantity(item: { product: IProduct, quantity: number, price: number }): void {
    item.quantity++;
    this.updatePrice(item);
  }

  decreaseQuantity(item: { product: IProduct, quantity: number, price: number }): void {
    if (item.quantity > 1) {
      item.quantity--;
      this.updatePrice(item);
    }
  }

  getTotalPrice(): number {
    return this.checkoutList.reduce((total, item) => total + item.price, 0);
  }
}
