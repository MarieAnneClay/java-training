// Wait for the DOM to be ready
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='addComputer']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
    	computerName: "required",
    	introduced: {
        // Specify that email should be validated
        // by the built-in "email" rule
        date: true
      },
      discontinued: {
    	  date: true
      }
    },
    // Specify validation error messages
    messages: {
    	computerName: "Please enter the computer name",
    	date: "Please enter a valid date AAAA-MM-DD"
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});
