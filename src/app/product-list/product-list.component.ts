import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { IProduct } from '../product';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {

  products : IProduct[]=[];
  sub! : Subscription;
  errorMessage = ' ';
  action : string = "view";

  constructor(private route : ActivatedRoute,
    private router : Router,
    private productService: ProductService){}

  ngOnInit(): void {
    this.sub = this.productService.getProducts().subscribe({
      next : products =>{
        this.products = products;
        console.log(this.products.length); 
      },
      error : err => this.errorMessage = err
    }); 
    console.log(this.action);     
  }

  

}
