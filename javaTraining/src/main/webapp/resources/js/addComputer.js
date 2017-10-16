submitValidate = function() {
	$("#error").empty();
	
	var isNotError = true;
	
	var name = document.getElementById('computerName').value;
	var sdate1 = document.getElementById('introduced').value;
	var sdate2 = document.getElementById('discontinued').value;

	var patternDate = /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))/;
	var patternName = /[0-9a-zA-Zàâéèëêïîôùüç -]{1,60}$/;

	if (name != ""){
		if(patternName.test(name) == false){
			$("#error").append($("#errorName"));
			isNotError = false;
		}

	}else{
		$("#error").append('nom vide');
		isNotError = false;
	}

	if (sdate1 != "" && sdate2 != ""){
		if(patternDate.test(sdate1) && patternDate.test(sdate2)){
			tabdeb = (sdate1.split(/[-]/));
			tabfin = (sdate2.split(/[-]/));
			Odeb = new Date(tabdeb[2],tabdeb[1],tabdeb[0]);
			Ofin = new Date(tabfin[2],tabfin[1],tabfin[0]);
			if(Odeb > Ofin) {
				$("#error").append('Introduced date must be before discontinued date');
				isNotError = false;
			}
		}
		else{
			$("#error").append('Format de la date incorrect');
			isNotError = false;
		}
	}
	if(isNotError){
		$("#addComputer").submit();
	}


};

//jQuery(document).ready(function() {
//	   jQuery("#addComputer").validate({
//	      rules: {
//	         "computerName":{
//	            "required": true,
//	            "minlength": 1,
//	            "maxlength": 60,
//	            "regex": /^[0-9a-zA-Zàâéèëêïîôùüç -]$/
//	         },
//	         "introduced": {
//	        	 "regex": /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))/
//	         },
//	         "discontinued": {
//	        	 "regex": /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))/
//	         }
//	  }),
//	  jQuery.validator.addMethod(
//			  "regex",
//			   function(value, element, regexp) {
//			       if (regexp.constructor != RegExp)
//			          regexp = new RegExp(regexp);
//			       else if (regexp.global)
//			          regexp.lastIndex = 0;
//			          return this.optional(element) || regexp.test(value);
//			   },"erreur expression reguliere"
//			),
//	   jQuery.extend(jQuery.validator.messages, {
//		    required: "votre message",
//		    remote: "votre message",
//		    email: "votre message",
//		    url: "votre message",
//		    date: "votre message",
//		    dateISO: "votre message",
//		    number: "votre message",
//		    digits: "votre message",
//		    creditcard: "votre message",
//		    equalTo: "votre message",
//		    accept: "votre message",
//		    maxlength: jQuery.validator.format("votre message {0} caractéres."),
//		    minlength: jQuery.validator.format("votre message {0} caractéres."),
//		    rangelength: jQuery.validator.format("votre message  entre {0} et {1} caractéres."),
//		    range: jQuery.validator.format("votre message  entre {0} et {1}."),
//		    max: jQuery.validator.format("votre message  inférieur ou égal à {0}."),
//		    min: jQuery.validator.format("votre message  supérieur ou égal à {0}.")
//		  });
//	});
