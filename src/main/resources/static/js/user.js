window.onload = function() {
	
	
	document.getElementById("botonFormulario").addEventListener("click", mostrarFormulario());
}

function mostrarTickets(){
	
}

function mostrarSettings(){
	
}

function mostrarUser(){
	
}

function mostrarFormulario(){
	if(document.getElementById("formulariodatos").style.display === 'none'){
		document.getElementById("formulariodatos").style.display = 'flex';
	}else{
		document.getElementById("formulariodatos").style.display = 'none';
	}
}