$(function() {
	var sdate1 = document.getElementById('introduced').value
	var date1 = new Date();
	date1.setFullYear(sdate1.substr(6,4));
	date1.setMonth(sdate1.substr(3,2));
	date1.setDate(sdate1.substr(0,2));
	date1.setHours(0);
	date1.setMinutes(0);
	date1.setSeconds(0);
	date1.setMilliseconds(0);
	var d1=date1.getTime()

	var sdate2 = document.getElementById('discontinued').value
	var date2 = new Date();
	date2.setFullYear(sdate2.substr(6,4));
	date2.setMonth(sdate2.substr(3,2));
	date2.setDate(sdate2.substr(0,2));
	date2.setHours(0);
	date2.setMinutes(0);
	date2.setSeconds(0);
	date2.setMilliseconds(0);
	var d2=date2.getTime()

	//si la date d'arrviée et superieur a la date de depart en afficher un message d'erreur

	$("form[name='addComputer']").validate({
		rules: {
			computerName: "required",
			introduced: {
				date: true,
				pattern : /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))/
			},
			discontinued: {
				date: true,
				pattern : /(?:19|20)[0-9]{2}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-9])|(?:(?!02)(?:0[1-9]|1[0-2])-(?:30))|(?:(?:0[13578]|1[02])-31))/
			}
		},
		messages: {
			computerName: "Please enter the computer name",
			date: "Please enter a valid date AAAA-MM-DD"
		},
		submitHandler: function(form) {
			if(d1>d2)
			{  
				alert('Vous avez selection un date incorrect!!');
			}else{
				form.submit();
			}

		}
	});
});
