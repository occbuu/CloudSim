import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from "rxjs/Observable";
import { config, environment } from '../../../environments/environment';

/**
 * API is a generic REST Api handler. Set your API url first.
 */
@Injectable()
export class ApiProvider {
    public apiUrl;
    public imgUrl;

    constructor(private http: HttpClient, private rou: Router) {
        if (environment.production) {
            this.apiUrl = (!config.apiUrl.startsWith(location.origin) ? location.origin : '') + config.apiUrl;
            this.imgUrl = (!config.imgUrl.startsWith(location.origin) ? location.origin : '') + config.imgUrl;
        }
        else {
            this.apiUrl = config.apiUrl;
            this.imgUrl = config.imgUrl;
        }

        //console.log(this.apiUrl);
        //console.log(this.imgUrl);
    }

    public get(endpoint: string, params?: any, reqOpts?: any) {
        if (!reqOpts) {
            reqOpts = {
                //headers: new HttpHeaders().set('Authorization', this.getToken()),
                params: new HttpParams()
            };
        }

        // Support easy query params for GET requests
        if (params) {
            reqOpts.params = new HttpParams();
            for (let k in params) {
                reqOpts.params.set(k, params[k]);
            }
        }

        return this.http.get(this.apiUrl + '/' + endpoint, reqOpts);
    }

    public post(endpoint: string, body: any, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders();//.set('Authorization', this.getToken());
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.post(this.apiUrl + '/' + endpoint, body, reqOpts);
    }

    public postz(endpoint: string, body: any, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders();//.set('Authorization', this.getToken());
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.post(endpoint, body, reqOpts);
    }

    public put(endpoint: string, body: any, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders();//.set('Authorization', this.getToken());
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.put(this.apiUrl + '/' + endpoint, body, reqOpts);
    }

    public delete(endpoint: string, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders();//.set('Authorization', this.getToken());
            reqOpts = { headers: h };
        }

        return this.http.delete(this.apiUrl + '/' + endpoint, reqOpts);
    }

    public patch(endpoint: string, body: any, reqOpts?: any) {
        if (!reqOpts) {
            let h = new HttpHeaders();//.set('Authorization', this.getToken());
            h = h.append('Content-Type', 'application/json');
            reqOpts = { headers: h };
        }

        return this.http.patch(this.apiUrl + '/' + endpoint, body, reqOpts);
    }

    public download(endpoint: string, body: any, reqOpts?: any): Observable<any> {
        if (!reqOpts) {
            reqOpts = {
                headers: new HttpHeaders(),//.set('Authorization', this.getToken()),
                responseType: 'blob'
            };
        }
        return this.http.post(this.apiUrl + '/' + endpoint, body, reqOpts);
    }

    private getToken(): string {
        let t = localStorage.getItem("CURRENT_TOKEN");
        let json = JSON.parse(t);

        if (json === null) {
            t = "";
            this.rou.navigate(['/']);
        } else {
            t = "Bearer " + json.token;
        }

        return t;
    }
}