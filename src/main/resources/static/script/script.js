window.onload = function() {
	var tabla = document.getElementById("tablaProductos");
     
        var filas = tabla.getElementsByTagName("tr");
	if (filas.length > 1) {
            // Obtener el elemento del enlace
            var enlace = document.getElementById("aceptar");
            // Mostrar el enlace cambiando su estilo a "display: block;"
            enlace.style.visibility = "visible";
        }
}