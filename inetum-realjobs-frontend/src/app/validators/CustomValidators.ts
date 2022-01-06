import {ValidatorFn, AbstractControl, ValidationErrors} from "@angular/forms";

export default class CustomValidators {
  static date1AfterDate2Validator(date1Component, date2Component): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (!control) {
        return null;
      }

      const date2 = control.get(date2Component);
      const date1 = control.get(date1Component);

      if (date2.value && date1.value) {
        return date2.value > date1.value ? {date1AfterDate2: true} : null;
      }
      return null;
    };
  }

  static dateInPastValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      return control.value > new Date() ? {dateInPast: true} : null;
    };
  }

  static uniqueItemValidator(list: string[]): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (list.map(i => i.toLowerCase()).includes(control.value.toLowerCase())) {
        return {uniqueItem: true}
      } else {
        return null;
      }
    }
  }
}
