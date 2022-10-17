import {FormControl, ValidationErrors} from "@angular/forms";

export class CustomValidators {

  //whitespaceValidation
  static notOnlyWhiteSpace(control: FormControl): ValidationErrors {
    //check if string only contains whitespace
    if ((control.value != null) && (control.value.trim().length === 0)) {
      //invalid, return error object
      //html template will check for this error key to see if it needs to disply error message or not
      return {"notOnlyWhiteSpace": true};
    } else {
      //valid, return null
      return null;
    }
  }
}
