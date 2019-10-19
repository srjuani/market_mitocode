<template>
    <div id ="employee-form">
        <form @submit.prevent="handlerSubmit">
            <label>Nombre</label>
            <input v-model="employee.name" type="text"/>
            
            <label>Correo</label>
            <input v-model="employee.email" type="text">

            <button>Guardar</button>

            <p v-if="error & submitting">
                Por favor debe llenar el formulario
            </p>
            <p v-if="success">
                Registro exitoso
            </p>
        </form>
    </div>
</template>

<script>
    export default {
        name:'employee-form',
        data(){
            return{
                submitting:false,
                error:false,
                success:false,
                employee:{
                    name:'',
                    email:''
                }
            }
        },
        methods:{
            handlerSubmit(){
                this.submitting=true;
                if(this.invalidName || this.invalidEmail){
                    this.error=true;
                    return
                }
                this.$emit('add:employee',this.employee)
                this.error=false
                this.success=true
                this.submitting=false
            }
        },
        computed:{
            invalidName(){
                return this.employee.name===''
            },
            invalidEmail(){
                return this.employee.email===''
            }
            
        }
    }
</script>

<style lang="stylus" scoped>

</style>