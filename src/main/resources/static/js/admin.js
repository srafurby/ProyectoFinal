window.onload = function() {
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
}

function mostrarTrenes(){
	document.getElementById("trenes").style.display = 'flex';
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
}

function mostrarEstaciones(){
	document.getElementById("estaciones").style.display = 'flex';
	document.getElementById("trenes").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
}

function mostrarHorarios(){
	document.getElementById("horarios").style.display = 'flex';
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("trenes").style.display = 'none';
	document.getElementById("usuarios").style.display = 'none';
}

function mostrarUsuarios(){
	document.getElementById("usuarios").style.display = 'flex';
	document.getElementById("estaciones").style.display = 'none';
	document.getElementById("horarios").style.display = 'none';
	document.getElementById("trenes").style.display = 'none';
}