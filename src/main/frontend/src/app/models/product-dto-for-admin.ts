import {ProductCategory} from "./product-category";

export class ProductDtoForAdmin {
  id: number;
  sku: string;
  category: ProductCategory;
  name: string;
  description: string;
  unitPrice: number;
  imageUrl: string;
  active: boolean;
  unitsInStock: number;
  dateCreated: Date;
  lastUpdated: Date
}
