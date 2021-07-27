import {BaseObject} from "./base-object";

export interface OrderItem extends BaseObject {
  seqNo: number;
  productId: number;
  productName: string;
  note: string;
}
