window.onload = function() {
	ocultarTodo();
	mostrarTrenes();
}

function ocultarTodo() {
	document.getElementById("trenes").style.display = 'none';
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
	document.getElementById("addTrenes").style.display = 'none';
	document.getElementById("addEstaciones").style.display = 'none';
	document.getElementById("addHorarios").style.display = 'none';
	document.getElementById("modifyTrenes").style.display = 'none';
	document.getElementById("modifyEstaciones").style.display = 'none';
	document.getElementById("modifyHorarios").style.display = 'none';
}

function mostrarTrenes() {
	ocultarTodo();
	document.getElementById("trenes").style.display = 'flex';
}

function mostrarEstaciones() {
	ocultarTodo();
	document.getElementById("estaciones").style.display = 'flex';
}

function mostrarHorarios() {
	ocultarTodo();
	document.getElementById("horarios").style.display = 'flex';
}

function mostrarUsuarios() {
	ocultarTodo();
	document.getElementById("usuarios").style.display = 'flex';
}

function mostrarAddTrenes() {
	if (document.getElementById("addTrenes").style.display == 'none') {
		document.getElementById("addTrenes").style.display = 'flex';
	} else {
		document.getElementById("addTrenes").style.display = 'none';
	}
}

function mostrarAddEstaciones() {
	if (document.getElementById("addEstaciones").style.display == 'none') {
		document.getElementById("addEstaciones").style.display = 'flex';
	} else {
		document.getElementById("addEstaciones").style.display = 'none';
	}
}

function mostrarAddHorarios() {
	if (document.getElementById("addHorarios").style.display == 'none') {
		document.getElementById("addHorarios").style.display = 'flex';
	} else {
		document.getElementById("addHorarios").style.display = 'none';
	}
}

function mostrarModifyTrenes(idTren) {
	if (document.getElementById("modifyTrenes").style.display == 'none') {
		document.getElementById("idModifyTren").value = idTren;
		document.getElementById("modifyTrenes").style.display = 'flex';
	} else {
		document.getElementById("modifyTrenes").style.display = 'none';
	}
}

function mostrarModifyEstaciones(idEstacion) {
	if (document.getElementById("modifyEstaciones").style.display == 'none') {
		document.getElementById("idModifyEstacion").value = idEstacion;
		document.getElementById("modifyEstaciones").style.display = 'flex';
	} else {
		document.getElementById("modifyEstaciones").style.display = 'none';
	}
}

function mostrarModifyHorarios(idHorario) {
	if (document.getElementById("modifyHorarios").style.display == 'none') {
		document.getElementById("idModifyHorario").value = idHorario;
		document.getElementById("modifyHorarios").style.display = 'flex';
	} else {
		document.getElementById("modifyHorarios").style.display = 'none';
	}
}

function confirmarEliminacionTren(idTren) { //IF DELETE BUTTON HAS PRESSED RECEIVES THE ID
	if (confirm("Are you sure to delete the Train? This action cant be reverted")) {
		eliminarTren(idTren);
	}
}

function eliminarTren(idTren) {
	$.ajax({//AJAX FUNCTION FOR DELETE TRAIN
		url: "/admin/tren/" + idTren, //SEND THE URL TO SPRINGBOOT WHIT THE ID OF THE TRAIN
		type: "POST",
		success: function() {//IF RECEIVE OK FROM SPRINGBOOT REDIRECT TO ADMIN PAGE
			alert("Train Deleted");
			window.location.href = "/admin";
		},
		error: function(jqXHR, textStatus, errorThrown) {//IF YOU RECEIVE AN ERROR IT SHOWS IN AN ALERT
			alert(jqXHR.responseText);
		}
	});
}

function confirmarEliminacionEstacion(idEstacion) {
	if (confirm("Are you sure to delete the Station? This action cant be reverted")) {
		eliminarEstacion(idEstacion);
	}
}

function eliminarEstacion(idEstacion) {
	$.ajax({
		url: "/admin/estacion/" + idEstacion,
		type: "POST",
		success: function() {
			alert("Station Deleted");
			window.location.href = "/admin";
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		}
	});
}

function confirmarEliminacionHorario(idHorario) {
	if (confirm("Are you sure to delete the Schedule? This action cant be reverted")) {
		eliminarHorario(idHorario);
	}
}

function eliminarHorario(idHorario) {
	$.ajax({
		url: "/admin/horario/" + idHorario,
		type: "POST",
		success: function() {
			alert("Schedule Deleted");
			window.location.href = "/admin";
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		}
	});
}

function confirmarEliminacionPasajero(idPasajero) {
	if (confirm("Are you sure to delete the User? This action cant be reverted")) {
		eliminarPasajero(idPasajero);
	}
}

function eliminarPasajero(idPasajero) {
	$.ajax({
		url: "/admin/pasajero/" + idPasajero,
		type: "POST",
		success: function() {
			alert("User Deleted");
			window.location.href = "/admin";
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		}
	});
}