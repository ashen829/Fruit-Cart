# Fruit Cart: Web-Based Pricing Engine

## Overview

**Fruit Cart** is a web-based pricing engine application built using Spring Boot for the backend and Angular for the frontend. It helps a street vendor manage and calculate the prices of two products: **King Coconut** and **Apple**. The system optimizes pricing based on carton and unit purchase quantities, applying discounts for bulk purchases.

### Key Features

- **Product Management**: Add, view, and manage products like King Coconut and Apple.
- **Price Calculation**: Calculates optimal prices based on the quantity of cartons and single units.
- **Discount Handling**: Automatically applies discounts when purchasing 2 or more cartons.
- **Checkout Feature**: Allows customers to add items to the cart, adjust quantities, and view total price dynamically.
- **Backend API**: Exposes RESTful endpoints to manage products and calculate prices.
- **Responsive UI**: Built using Angular, providing a smooth user experience for managing products and making purchases.

## Technology Stack

- **Backend**: Spring Boot, Java
- **Frontend**: Angular
- **Database**: MySQL
- **Build Tools**: Maven (for backend), NPM (for frontend)

## Prerequisites

- **Java 11+**
- **Node.js** & **NPM**
- **Angular CLI**
- **MySQL Server**

## Application Flow

1. **Product Entity**:  
   Each product has the following attributes:
    - Product Name
    - Quantity
    - Carton Price
    - Units Per Carton
    - Description
    - Unit Price Markup (%)
    - Carton Discount (%)
    - Product Image

2. **Pricing Engine**:
    - **Unit Price** = Carton Price / Units Per Carton
    - **Unit Price with Markup** = Unit Price x (1 + (Unit Price Markup % / 100))
    - If purchasing **2 or more cartons**, a **20% discount** is applied to the Carton Price.
    - Total cost is optimized by combining cartons and individual units (if applicable).

## Backend Endpoints

| Method | Endpoint                                       | Description                                           |
|--------|------------------------------------------------|-------------------------------------------------------|
| GET    | `/api/v1/product/getProducts`                  | Retrieves a list of products from the MySQL database   |
| POST   | `/api/v1/product/save`                         | Saves a new product to the database                   |
| GET    | `/api/v1/pricing/calculateUnitPrice`           | Returns the unit price with markup for each product    |
| POST   | `/api/v1/pricing/calculate`                    | Calculates the total price based on the selected units and cartons |

## Frontend Pages

| Page          | Endpoint                        | Description                                                          |
|---------------|---------------------------------|----------------------------------------------------------------------|
| Product List  | `/product-list`                 | Displays a table of all products from the database                    |
| Add Product   | `/add-product`                  | Provides a form to add new products                                   |
| Shop Now      | `/shop-now`                     | Shows product cards, allowing users to add products to the checkout , Displays the total price based on selected products and quantities   |


## Price Calculation Logic
- **How the Price Engine Works**:
    - The price engine calculates the total price for each product based on the number of cartons and single units.
    - If purchasing single units, a 20% markup is applied.
    - If purchasing 2 or more cartons, a 20% discount is applied to the total carton price.
  
- **Single Units**:
    - The price for individual units is calculated as:  
      `Unit Price with Markup = (Carton Price / Units Per Carton) * 1.2`

- **Carton Pricing**:
    - If the purchase includes **2 or more cartons**, a **20% discount** is applied to the carton price.
    - Total price is optimized by combining full cartons and individual units.

- **Example**:
  If purchasing 30 units, and the carton holds 20 units:
    - You will be charged for **1 carton** and **10 single units**.

## Installation and Setup

### Backend Setup (Spring Boot)

1. Clone the repository:
   ```bash
   git clone https://github.com/ashen829/Fruit-Cart.git
   cd Fruit-Cart/backend
2. Switch to the backend branch:
   ```bash
   git checkout backend
3. Configure the MySQL database:
   
   - Ensure you have MySQL running locally.
   - Create a database for the application (e.g., fruit_cart).
   - Update the database credentials in src/main/resources/application.properties.
   

4. Build and run the backend:
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
5. The backend will run on http://localhost:8080.

### Frontend Setup (Angular)

1. Navigate to the frontend directory:
   ```bash
   cd Fruit-Cart/frontend
   git checkout frontend
2. Install dependencies:
   ```bash
   npm install
3. Run the Angular application:
   ```bash
   ng serve --port 3000
4. The frontend will be available at http://localhost:3000.

