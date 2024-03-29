window.onload = function() {
	// Obtenemos la fecha actual
	const fechaActual = new Date().toISOString().split('T')[0];

	// Establecemos la fecha mínima permitida en el input
	document.getElementById("ida").setAttribute("min", fechaActual);
	document.getElementById("vuelta").setAttribute("min", fechaActual);
}

function validar(event) {
	const select1 = document.getElementById("destino");
	const select2 = document.getElementById("origen");

	if (select1.value === select2.value) {
		alert("You can't select the same station on the departure and arrival options");
		event.preventDefault();
	}
}