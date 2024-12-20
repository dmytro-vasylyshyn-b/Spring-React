import { ICategory } from "./CategoriesModel";
import { IDiscount } from "./discountModel";

export interface IProduct {
    productId: number;
    productName: string;
    productDescription: string;
    productPrice: number;
    productStock: number;
    createdAt: string;
    photoUrl: string ;
    productCategory: ICategory | null;
    productDiscount: IDiscount | null;
}