import {Directive} from '@angular/core';
import {AbstractControl, FormGroup, NG_VALIDATORS, ValidationErrors, Validator, ValidatorFn} from '@angular/forms';

@Directive({
    selector: '[appRepeatPassword]',
    providers: [{provide: NG_VALIDATORS, useExisting: RepeatPasswordValidatorDirective, multi: true}],
})
export class RepeatPasswordValidatorDirective implements Validator {

    validate(control: AbstractControl): ValidationErrors | null {
        return repeatPasswordValidator(control);
    }

}

export const repeatPasswordValidator: ValidatorFn = (control: FormGroup): ValidationErrors | null => {
    const password = control.get('password');
    const repeatedPassword = control.get('repeatPassword');
    if (password.value !== repeatedPassword.value) {
        repeatedPassword.setErrors({repeatedPassword: true});
        return {repeatedPassword: true};
    }
    return null;
};
