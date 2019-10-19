<template>
  <div id="app" class="small-container">
    <h1>Empleados</h1>
    <employee-form @add:employee="addEmployee"/>
    <employee-table v-bind:employees="employees"/>
  </div>
</template>

<script>
import EmployeeTable from '@/components/EmployeeTable.vue'
import EmployeeForm from '@/components/EmployeeForm.vue'

export default {
  name: 'app',
  components: {
    EmployeeTable,
    EmployeeForm
  },
  data(){
    return{
      employees:[]
    }
  },
  mounted(){
    this.getEmployees()
  },
  methods:{
    async getEmployees(){

      try {
        //const response = await fetch('https://jsonplaceholder.typicode.com/users');
        const response = await fetch('http://localhost:8093/employees');
        const data = await response.json();
        this.employees=data;
      } catch (error) {
         console.log(error);
      }
    },
    async addEmployee(employee){
      try {
        //const response = await fetch('https://jsonplaceholder.typicode.com/users');
        const response = await fetch('http://localhost:8093/employees',{
          method:'POST',
          body:JSON.stringify(employee),
          headers:{'Content-type':'application/json; charset=UTF-8'}
        });
        const data = await response.json();
        this.employees=[...this.employees,data];
      } catch (error) {
         console.log(error);
      }
    },
    // addEmployee(employee){
    //   const lastId=
    //     this.employees.length>0
    //       ?this.employees[this.employees.length-1].id
    //       :0;
    //   const id = lastId+1;
    //   const newEmployee={...employee,id};
    //   this.employees=[...this.employees,newEmployee];      
    // }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
