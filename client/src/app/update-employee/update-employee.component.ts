import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css'],
})
export class UpdateEmployeeComponent implements OnInit {
  id!: number;
  employee: any = new Employee();
  constructor(
    private employeeservice: EmployeeService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.employeeservice.getEmloyeeById(this.id).subscribe(
      (data) => (this.employee = data),
      (err) => console.log(err)
    );
  }

  updateEmloyee() {
    this.employeeservice.updateEmployee(this.id, this.employee).subscribe(
      (data) => (this.employee = data),
      (err) => console.log(err)
    );
  }
  onSubmit() {
    this.updateEmloyee();
    this.router.navigate(['/employee']);
  }
}
