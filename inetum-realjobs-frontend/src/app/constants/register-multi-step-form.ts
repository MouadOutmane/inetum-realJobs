import {Gender} from "../models/gender.enum";

const CHOICE_LIST = [
  {value: "Yes", name: "Yes, create my resume now"},
  {value: "No", name: "No, I would like to create my resume later"}
];

const DATA_STEP_1 = {
  firstName: {
    type: "text",
    validations: {required: "true"},
    errors: {pattern: "Field is required"},
    name: "First Name"
  },
  lastName: {
    type: "text",
    validations: {required: "true"},
    errors: {pattern: "Field is required"},
    name: "Last Name"
  },
  gender: {
    type: "selectGender",
    options: [],
    validations: {required: "true"},
    errors: {pattern: "Field is required"},
    name: "Gender"
  },
  email: {
    type: "text",
    validations: {email: "true", required: "true"},
    errors: {pattern: "Not a valid email"},
    name: "Email"
  },
  password: {
    type: "password",
    validations: {minLength: 8},
    errors: {pattern: "Field is required and must be 8 characters minimum"},
    name: "Password"
  },
  confirmPassword: {
    type: "confirmPassword",
    validations: {minLength: 8},
    errors: {pattern: "Field is required and must be 8 characters minimum"},
    name: "Confirm Password"
  }
};

const DATA_STEP_2 = {
  dateOfBirth: {type: "calender", validations: {}, name: "Birth Date"},
  streetName: {type: "text", validations: {}, name: "Street Name"},
  numberHouse: {type: "text", validations: {}, name: "Nr"},
  box: {type: "text", validations: {}, name: "Box"},
  city: {type: "text", validations: {}, name: "City"},
  postalCode: {type: "text", validations: {}, name: "Postal Code"},
  country: {
    type: "selectCountry",
    options: [],
    validations: {},
    errors: {},
    name: "Country"
  },
  mobilePhone: {type: "text", validations: {}, name: "Mobile phone"},
  role: {
    type: "radioButton",
    options: [],
    validations: {},
    name: "I would like to sign up as a:"
  }
};

const DATA_STEP_3 = {
  header: {type: "label", validations: {}, name: "Almost done ! Just one more step"},
  text: {type: "label", validations: {}, name: "Would you like to add job experiences (resume) now?"},
  resume: {
    type: "radioButtonResume",
    options: CHOICE_LIST,
    validations: {},
    name: ""
  }
};

const STEP_ITEMS = [
  {label: "Step 1", data: DATA_STEP_1},
  {label: "Step 2", data: DATA_STEP_2},
  {label: "Step 3", data: DATA_STEP_3}
];

export {STEP_ITEMS}
