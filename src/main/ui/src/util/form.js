export default function extraSubmitForm(source, form) {
  const submitForm = {};
  form.fields.forEach((field) => {
    if (field.prop in source) {
      submitForm[field.prop] = source[field.prop];
    }
  });
  return submitForm;
}
