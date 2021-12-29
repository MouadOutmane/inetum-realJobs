import { AbstractControl, ValidationErrors } from "@angular/forms";

export function textValidation(control: AbstractControl): ValidationErrors | null {
    if (typeof control.value =='undefined' || !control.value){
        return { 'textValidation': true, 'requiredValue': "required text" }
    }

    return null

}

export function numberValidation(control: AbstractControl): ValidationErrors | null {
    if (!Number(control.value) || Number(control.value) <= 0){
        return { 'numberValidation': true, 'requiredValue': "required number" }
    }

    return null

}