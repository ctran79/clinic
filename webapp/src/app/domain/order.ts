import {OrderItem} from "./order-item";
import {BaseObject} from "./base-object";

export interface Order extends BaseObject{
  client: string;
  address: string;
  createDate?: Date;
  items: OrderItem[];
}
