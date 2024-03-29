window.onload = function() {
	document.getElementById("botonFormulario").addEventListener("click", mostrarFormulario());

	document.getElementById("tickettren").style.display = 'none';
	document.getElementById("eliminarcuenta").style.display = 'none';
}

function mostrarTickets() {
	document.getElementById("user").style.display = 'none';

	document.getElementById("tickettren").style.removeProperty("display");
	document.getElementById("eliminarcuenta").style.display = 'none';
	document.getElementById("formulariodatos").style.display = 'none';

	if (document.getElementById("botonTickets").classList.contains("inactivo")) {
		document.getElementById("botonTickets").classList.remove("inactivo");
		document.getElementById("botonTickets").classList.add("activo");
		if (document.getElementById("botonUser").classList.contains("activo")) {
			document.getElementById("botonUser").classList.remove("activo");
			document.getElementById("botonUser").classList.add("inactivo");
		}
		if (document.getElementById("botonSettings").classList.contains("activo")) {
			document.getElementById("botonSettings").classList.remove("activo");
			document.getElementById("botonSettings").classList.add("inactivo");
		}
	}

}

function mostrarSettings() {
	document.getElementById("eliminarcuenta").style.removeProperty("display");

	document.getElementById("tickettren").style.display = 'none';
	document.getElementById("user").style.display = 'none';
	document.getElementById("formulariodatos").style.display = 'none';

	if (document.getElementById("botonSettings").classList.contains("inactivo")) {
		document.getElementById("botonSettings").classList.remove("inactivo");
		document.getElementById("botonSettings").classList.add("activo");
		if (document.getElementById("botonTickets").classList.contains("activo")) {
			document.getElementById("botonTickets").classList.remove("activo");
			document.getElementById("botonTickets").classList.add("inactivo");
		}
		if (document.getElementById("botonUser").classList.contains("activo")) {
			document.getElementById("botonUser").classList.remove("activo");
			document.getElementById("botonUser").classList.add("inactivo");
		}
	}
}

function mostrarUser() {
	document.getElementById("user").style.removeProperty("display");

	document.getElementById("tickettren").style.display = 'none';
	document.getElementById("eliminarcuenta").style.display = 'none';
	document.getElementById("formulariodatos").style.display = 'none';

	if (document.getElementById("botonUser").classList.contains("inactivo")) {
		document.getElementById("botonUser").classList.remove("inactivo");
		document.getElementById("botonUser").classList.add("activo");
		if (document.getElementById("botonTickets").classList.contains("activo")) {
			document.getElementById("botonTickets").classList.remove("activo");
			document.getElementById("botonTickets").classList.add("inactivo");
		}
		if (document.getElementById("botonSettings").classList.contains("activo")) {
			document.getElementById("botonSettings").classList.remove("activo");
			document.getElementById("botonSettings").classList.add("inactivo");
		}
	}
}

function mostrarFormulario() {
	if (document.getElementById("formulariodatos").style.display === 'none') {
		document.getElementById("formulariodatos").style.display = 'flex';
	} else {
		document.getElementById("formulariodatos").style.display = 'none';
	}
}

function confirmarCancelarTicket(idTicket) {
	if (confirm("Are you sure to cancel the Ticket? This action cant be reverted")) {
		cancelarTicket(idTicket);
	}
}

function cancelarTicket(idTicket) {
	$.ajax({
		url: "/user/cancelarTicket/" + idTicket,
		type: "POST",
		success: function() {
			window.location.href = "/user";
		}
	});
}

function confirmarEliminacionPasajero(idPasajero) {
	if (confirm("Are you sure to delete your User account? This action cant be reverted")) {
		eliminarPasajero(idPasajero);
	}
}

function eliminarPasajero(idPasajero) {
	$.ajax({
		url: "/user/delete/" + idPasajero,
		type: "POST",
		success: function() {
			alert("User Account Deleted");
			window.location.href = "/";
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText);
		}
	});
}