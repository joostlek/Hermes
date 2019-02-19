import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {EMPTY, Observable} from 'rxjs';
import {switchMap} from 'rxjs/operators';
import {CurrentUserService} from './current-user.service';
import {Company} from './domain/company';

@Injectable({
    providedIn: 'root',
})
export class CompanyService {

    constructor(private http: HttpClient,
                private currentUserService: CurrentUserService) {
    }

    getAllCompanies(): Observable<Company[]> {
        return this.http.get<Company[]>('api/companies');
    }

    getCompanyById(id: number): Observable<Company> {
        return this.http.get<Company>('api/companies/' + id);
    }

    getCompaniesByUserId(userId: number): Observable<Company[]> {
        return this.http.get<Company[]>('api/users/' + userId + '/companies');
    }

    addCompany(company: Company): Observable<Company> {
        return this.currentUserService.getCurrentUser()
            .pipe(
                switchMap((value) => {
                        if (value !== null) {
                            return this.addCompanyWithUserId(company, value.id);
                        } else {
                            return EMPTY;
                        }
                    },
                ),
            );
    }

    private addCompanyWithUserId(company: Company, userId: number): Observable<Company> {
        return this.http.post<Company>('api/users/' + userId + '/companies', company);
    }

    public getPersonalCompanies(userId: number): Observable<Company[]> {
        return this.http.get<Company[]>('api/users/' + userId + '/companies/personal');
    }

    public getAdvertisingCompanies(userId: number): Observable<Company[]> {
        return this.http.get<Company[]>('api/users/' + userId + '/companies/advertising');
    }

    public addUserToCompany(email: string, companyId: number): Observable<any> {
        return this.http.put('api/companies/' + companyId + '/users', {email});
    }

    public removeUserFromCompany(userId: number, companyId: number): Observable<any> {
        return this.http.delete('api/companies/' + companyId + '/users/' + userId);
    }

    public updateCompany(company: Company): Observable<Company> {
        return this.http.put<Company>('api/companies/' + company.id, company);
    }

    public deleteCompany(companyId: number): Observable<any> {
        return this.http.delete('api/companies/' + companyId);
    }

    public removeAdvertisingLocationFromCompany(locationId: number, companyId: number): Observable<Company> {
        return this.http.delete<Company>('api/companies/' + companyId + '/advertising/' + locationId);
    }

    public addAdvertisingLocationToCompany(locationId: number, companyId: number): Observable<Company> {
        return this.http.post<Company>('api/companies/' + companyId + '/advertising', {locationId});
    }
}
