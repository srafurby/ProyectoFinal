window.onload = function() {
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
	document.getElementById("addTrenes").style.display = 'none';
	document.getElementById("addEstaciones").style.display = 'none';
	document.getElementById("addHorarios").style.display = 'none';
}

function mostrarTrenes() {
	document.getElementById("trenes").style.display = 'flex';
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
	document.getElementById("addTrenes").style.display = 'none';
	document.getElementById("addEstaciones").style.display = 'none';
	document.getElementById("addHorarios").style.display = 'none';
}

function mostrarEstaciones() {
	document.getElementById("estaciones").style.display = 'flex';
	document.getElementById("trenes").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
	document.getElementById("addTrenes").style.display = 'none';
	document.getElementById("addEstaciones").style.display = 'none';
	document.getElementById("addHorarios").style.display = 'none';
}

function mostrarHorarios() {
	document.getElementById("horarios").style.display = 'flex';
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("trenes").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
	document.getElementById("addTrenes").style.display = 'none';
	document.getElementById("addEstaciones").style.display = 'none';
	document.getElementById("addHorarios").style.display = 'none';
}

function mostrarUsuarios() {
	document.getElementById("usuarios").style.display = 'flex';
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("trenes").style.display = 'none';
	document.getElementById("addTrenes").style.display = 'none';
	document.getElementById("addEstaciones").style.display = 'none';
	document.getElementById("addHorarios").style.display = 'none';
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


function confirmarEliminacionTren(idTren) {
	if (confirm("Are you sure to delete the Train? This action cant be reverted")) {
		eliminarTren(idTren);
	}
}

function eliminarTren(idTren) {
	$.ajax({
		url: "/admin/tren/" + idTren,
		type: "POST",
		success: function() {
			window.location.href = "/admin";
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
			window.location.href = "/admin";
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
			window.location.href = "/admin";
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
			window.location.href = "/admin";
		}
	});
}