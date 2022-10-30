import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseURL = 'http://localhost:8080/api/v1/employees';
  constructor(private httpclient: HttpClient) {}

  getEmployeesList(): Observable<Employee[]> {
    return this.httpclient.get<Employee[]>(`${this.baseURL}`);
  }
  creatEmployee(employee: Employee): Observable<Object> {
    return this.httpclient.post(`${this.baseURL}`, employee);
  }
  getEmloyeeById(id: number) {
    return this.httpclient.get(`${this.baseURL}/${id}`);
  }
  updateEmployee(id: number, employee: Employee): Observable<object> {
    return this.httpclient.put(`${this.baseURL}/${id}`, employee);
  }

  deleteEmployeeById(id: number): Observable<object> {
    return this.httpclient.delete(`${this.baseURL}/${id}`);
  }
}
