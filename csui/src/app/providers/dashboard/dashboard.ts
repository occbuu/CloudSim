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
  public showExample(id:string) {
    return this.api.get('/ex/example'+id);
  }
  public showExample1() {
    return this.api.get('/ex/example1');
  }

  public showExample2() {
    return this.api.get('/ex/example2');
  }

 
  public showExample3() {
    return this.api.get('/ex/example3');
  }

  public showExample4() {
    return this.api.get('/ex/example4');
  }

  public showExample5() {
    return this.api.get('/ex/example5');
  }

  public showExample6() {
    return this.api.get('/ex/example6');
  }

  public showExample7() {
    return this.api.get('/ex/example7');
  }
 
  public showExample8() {
    return this.api.get('/ex/example8');
  }

  public showExample9() {
    return this.api.get('/ex/example9');
  }

  public showArima1() {
    return this.api.get('/arima/startsim1');
  }

  public showArima(info:any) {
    return this.api.post('/arima/startsim', info);
  }

  public testpost(info:any) {
    return this.api.post('/arima/testpost', info);
  }
}