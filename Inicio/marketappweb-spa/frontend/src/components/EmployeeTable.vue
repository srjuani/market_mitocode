<template>
    <div id ="employee-table">
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Correo</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="employee in employees" :key="employee.id">
                    <td>{{employee.name}}</td>
                    <td>{{employee.email}}</td>
                </tr>
                <!-- <tr>
                    <td>Henry</td>
                    <td>hemendo81@gmail.com</td>
                </tr> -->
            </tbody>
        </table>
    </div>
</template>

<script>
    export default {
        name:'employee-table',
        props:{
            employees:Array
        }   
    }
</script>

<style lang="stylus" scoped>

</style>