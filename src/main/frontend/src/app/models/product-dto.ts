import {ProductCategory} from "./product-category";

export class ProductDto {


  sku: string;
  category: ProductCategory;
  name: string;
  description: string;
  unitPrice: number;
  imageUrl: string;
  active: boolean;
  unitsInStock: number


}
