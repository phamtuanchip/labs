#include <stdio.h>

/* User defined types */

enum deptcode {sales,personnel,packing,engineering};

typedef enum deptcode DEPT;

struct person {
  int age, salary;
  DEPT department;
  char name[12];
  char address[6][20];
};

typedef struct person EMPLOYEE;

void read_line(char Str[]) {
  int i = 0;   char next;
  while ((next=getchar())!='\n') {
    Str[i] = next;
    i++;
  }
  Str[i] = 0;    /* Set the null char at the end */
}

void print_employee(EMPLOYEE Emp) {
  int i;
  printf(" %d %d %d\n",Emp.age,Emp.salary,Emp.department);
  printf("%s\n",Emp.name);
  for (i=0;i<=5;i++) printf("%s\n",Emp.address[i]);
}

int main () {
  EMPLOYEE This_Employee;
  int i;
  printf("Input employee age: ");
  scanf("%d",&This_Employee.age);
  printf("\nInput employee salary: ");
  scanf("%d",&This_Employee.salary);
  printf("\nInput employee department: ");
  scanf("%d\n",&This_Employee.department);
  read_line(This_Employee.name);
  for (i=0; i<=5; i++) read_line(This_Employee.address[i]);
  print_employee(This_Employee);
  getchar();
  return 0;
}