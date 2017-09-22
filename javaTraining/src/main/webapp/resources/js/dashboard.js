//On load
$(function() {
	// Default: hide edit mode
	$(".editMode").hide();

	// Click on "selectall" box
	$("#selectall").click(function () {
		$('.cb').prop('checked', this.checked);
	});

	// Click on a checkbox
	$(".cb").click(function() {
		if ($(".cb").length == $(".cb:checked").length) {
			$("#selectall").prop("checked", true);
		} else {
			$("#selectall").prop("checked", false);
		}
		if ($(".cb:checked").length != 0) {
			$("#deleteSelected").enable();
		} else {
			$("#deleteSelected").disable();
		}
	});

});


//Function setCheckboxValues
(function ( $ ) {

	$.fn.setCheckboxValues = function(formFieldName, checkboxFieldName) {

		var str = $('.' + checkboxFieldName + ':checked').map(function() {
			return this.value;
		}).get().join();

		$(this).attr('value',str);

		return this;
	};

}( jQuery ));

//Function toggleEditMode
(function ( $ ) {

	$.fn.toggleEditMode = function() {
		if($(".editMode").is(":visible")) {
			$(".editMode").hide();
			$("#editComputer").text("Edit");
		}
		else {
			$(".editMode").show();
			$("#editComputer").text("View");
		}
		return this;
	};

}( jQuery ));


//Function delete selected: Asks for confirmation to delete selected computers, then submits it to the deleteForm
(function ( $ ) {
	$.fn.deleteSelected = function() {
		if (confirm("Are you sure you want to delete the selected computers?")) { 
			$('#deleteForm input[name=selection]').setCheckboxValues('selection','cb');
			$('#deleteForm').submit();
		}
	};
}( jQuery ));



//Event handling
//Onkeydown
$(document).keydown(function(e) {

	switch (e.keyCode) {
	//DEL key
	case 46:
		if($(".editMode").is(":visible") && $(".cb:checked").length != 0) {
			$.fn.deleteSelected();
		}   
		break;
		//E key (CTRL+E will switch to edit mode)
	case 69:
		if(e.ctrlKey) {
			$.fn.toggleEditMode();
		}
		break;
	}
});
(function ( $ ) {
	$.fn.submitValidate = function() {
		var sdate1 = document.getElementById('introduced').value();
		var date1 = new Date();
		date1.setFullYear(sdate1.substr(6,4));
		date1.setMonth(sdate1.substr(3,2));
		date1.setDate(sdate1.substr(0,2));
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date1.setMilliseconds(0);
		var d1=date1.getTime();

		var sdate2 = document.getElementById('discontinued').value();
		var date2 = new Date();
		date2.setFullYear(sdate2.substr(6,4));
		date2.setMonth(sdate2.substr(3,2));
		date2.setDate(sdate2.substr(0,2));
		date2.setHours(0);
		date2.setMinutes(0);
		date2.setSeconds(0);
		date2.setMilliseconds(0);
		var d2=date2.getTime();

		alert(d1+d2);

		//si la date d'arrviée et superieur a la date de depart en afficher un message d'erreur

		$("form[name='addComputer']").validate({
			rules: {
				computerName: {
					required: true,
					pattern : /[0-9a-zA-Zàâéèëêïîôùüç -]{1,60}$/
				},
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
	};
}( jQuery ));


