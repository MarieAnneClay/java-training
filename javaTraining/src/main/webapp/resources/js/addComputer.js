submitValidate = function() {
	$("#error").empty();
	
	var isNotError = true;
	
	var sdate1 = document.getElementById('introduced').value;
	var sdate2 = document.getElementById('discontinued').value;
	var name = document.getElementById('computerName').value;

	var patternDate = /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))/;
	var patternName = /[0-9a-zA-Zàâéèëêïîôùüç -]{1,60}$/;

	if (name != ""){
		if(patternName.test(name) == false){
			$("#error").append('Name must be alphanumeric');
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



//required: true,
//pattern : /[0-9a-zA-Zàâéèëêïîôùüç -]{1,60}$/
//},
//introduced: {
//date: true,
//pattern : /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))/
//},
//discontinued: {
//date: true,
//pattern : /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))/
