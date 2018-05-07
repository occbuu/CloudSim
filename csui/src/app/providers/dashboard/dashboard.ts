import 'rxjs/add/operator/toPromise';
import { Injectable } from '@angular/core';
import { ApiProvider } from '../api/api';

@Injectable()
export class DashboardProvider {
  constructor(private api: ApiProvider) { }

  /**
  * Get API URL
  */
  public getURL() {
    return this.api.imgUrl + "/";
  }

  
  public handleError(error: any) {
    return "Error message";
  }

  public getOrders(status: String) {
    return this.api.get('myorders?status=' + status);
  }

  /**
   * Get assets
   */
  public getAssets() {
    return this.api.get('myassets');
  }

  /**
   * Get assets
   */
  public testing() {
    return this.api.get('testing');
  }
 
}