$(document).ready(function() {
	
	cargarUsuarios();
});

function getHeaders() {
	return {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization': sessionStorage.token
	};
}
async function registrarUsuario() {

	let datos = {};

	datos.cedula = document.getElementById('txtCedula').value;
	datos.primerNombre = document.getElementById('txtPrimerNombre').value;
	datos.segundoNombre = document.getElementById('txtSegundoNombre').value;
	datos.primerApellido = document.getElementById('txtPrimerApellido').value;
	datos.segundoApellido = document.getElementById('txtSegundoApellido').value;
	datos.clave = document.getElementById('txtClave').value;
	datos.email = document.getElementById('txtEmail').value;
	datos.nitEmpresa = document.getElementById('selEmpresa').value;
	datos.rol = document.getElementById('selRol').value;
	datos.estado = document.getElementById('selEstado').value;

	const request = await fetch('api/usuarios', {
		method: 'POST',
		headers: getHeaders(),
		body: JSON.stringify(datos)
	});
	location.reload();
}

async function cargarUsuarios() {

	const request = await fetch('api/usuarios', {
		method: 'GET',
		headers: getHeaders()
	});
	const usuarios = await request.json(); //devuelve un resultado en tipo json

	console.log(usuarios);

	let listadohtml = '';
	for (let usuario of usuarios) {

		let btnAcciones = '<button href="#" onClick="eliminarUsuario(' + usuario.cedula + ')">eliminar</button>'
			+ '<button href="#" onClick="actualizarUsuario(' + usuario.cedula + ')">actualizar</button>';
		let estado = usuario.estado == 1 ? 'activo' : 'inactivo'; // condiciona como un if
		let rol;
		switch (usuario.rol) {
			case 1: rol = "adminsys"; break;
			case 2: rol = "asistente"; break;
			case 3: rol = "operaciones"; break;
			case 4: rol = "coordinador"; break;
			case 5: rol = "jefe"; break;
			case 6: rol = "director"; break;
			case 7: rol = "gerente"; break;

		}
		let usuariohtml = '<tr><td>' + usuario.cedula +
			'</td><td>' + usuario.primerNombre +
			'</td><td>' + usuario.segundoNombre +
			'</td><td>' + usuario.primerApellido +
			'</td><td>' + usuario.segundoApellido +
			'</td><td>' + usuario.clave +
			'</td><td>' + usuario.email +
			'</td><td>' + usuario.nitEmpresa +
			'</td><td>' + rol +
			'</td><td>' + estado +
			'</td><td>' + btnAcciones + '</td></tr>';

		listadohtml += usuariohtml;

	}

	document.querySelector('#tablaUsuarios tbody').outerHTML = listadohtml;

}

async function eliminarUsuario(cedula) {

	if (!confirm("¿Desea eliminar este usuario?")) { //metodo confirm muestra aviso de si o no
		return;
	}

	const request = await fetch('api/usuarios/' + cedula, {
		method: 'DELETE',
		headers: getHeaders()
	});

	location.reload(); //actualiza la pagina

}

async function actualizarUsuario(cedula) {

	document.getElementById("divActualizar").style.display = "block";
	document.getElementById("usuAct").value = cedula;
	document.getElementById("usuAct").innerHTML = cedula;
	const request = await fetch('api/usuarios/' + cedula, {
		method: 'GET',
		headers: getHeaders()
	});
	const usuario = await request.json(); //devuelve un resultado en tipo json
	console.log(usuario.primerNombre);

	document.getElementById('txtPrimerNombreAct').value = usuario.primerNombre;
	document.getElementById('txtSegundoNombreAct').value = usuario.segundoNombre;
	document.getElementById('txtPrimerApellidoAct').value = usuario.primerApellido;
	document.getElementById('txtSegundoApellidoAct').value = usuario.segundoApellido;
	document.getElementById('txtClaveAct').value = usuario.clave;
	document.getElementById('txtEmailAct').value = usuario.email;
	document.getElementById('selEmpresaAct').value = usuario.nitEmpresa;
	document.getElementById('selRolAct').value = usuario.rol;
	document.getElementById('selEstadoAct').value = usuario.estado;
}

async function editarUsuario() {
	let cedula = document.getElementById("usuAct").value;

	let datos = {};

	datos.primerNombre = document.getElementById('txtPrimerNombreAct').value;
	datos.segundoNombre = document.getElementById('txtSegundoNombreAct').value;
	datos.primerApellido = document.getElementById('txtPrimerApellidoAct').value;
	datos.segundoApellido = document.getElementById('txtSegundoApellidoAct').value;
	datos.clave = document.getElementById('txtClaveAct').value;
	datos.email = document.getElementById('txtEmailAct').value;
	datos.nitEmpresa = document.getElementById('selEmpresaAct').value;
	datos.rol = document.getElementById('selRolAct').value;
	datos.estado = document.getElementById('selEstadoAct').value;

	const request = await fetch('api/usuarios/' + cedula, {
		method: 'PUT',
		headers: getHeaders(),
		body: JSON.stringify(datos)
	});

	location.reload(); //actualiza la pagina

}

async function buscar() {

	let cedula = document.getElementById("txtBuscar").value;

	const request = await fetch('api/usuarios/' + cedula, {
		method: 'GET',
		headers: getHeaders()
	});
	const usuario = await request.json(); //devuelve un resultado en tipo json
	let estado = usuario.estado == 1 ? 'activo' : 'inactivo';
	let rol;
	switch (usuario.rol) {
		case 1: rol = "adminsys"; break;
		case 2: rol = "asistente"; break;
		case 3: rol = "operaciones"; break;
		case 4: rol = "coordinador"; break;
		case 5: rol = "jefe"; break;
		case 6: rol = "director"; break;
		case 7: rol = "gerente"; break;

	}

	let usuariohtml = 'CEDULA: ' + usuario.cedula + "<br>" +
		'PRIMER NOMBRE: ' + usuario.primerNombre + "<br>" +
		'SEGUNDO NOMBRE: ' + usuario.segundoNombre + "<br>" +
		'PRIMER APELLIDO: ' + usuario.primerApellido + "<br>" +
		'SEGUNDO APELLIDO: ' + usuario.segundoApellido + "<br>" +
		'CLAVE: ' + usuario.clave + "<br>" +
		'EMAIL: ' + usuario.email + "<br>" +
		'NIT EMPRESA' + usuario.nitEmpresa + "<br>" +
		'ROL: ' + rol + "<br>" +
		'ESTADO: ' + estado;

	document.getElementById("resBus").innerHTML = usuariohtml;

}

async function iniciarSesion() {

	let datos = {};
	datos.email = document.getElementById('txtEmail').value;
	datos.clave = document.getElementById('txtClave').value;

	const request = await fetch('api/login', {
		method: 'POST',
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}, 
		body: JSON.stringify(datos)  //envia datos necesarios para hacer el inicio de sesion
	});
	const respuesta = await request.text();

	if (respuesta != 'fail'){
		sessionStorage.token = respuesta;
		sessionStorage.email = datos.email;
		window.location.href = 'usuario.html';
	} else{
		alert("las credenciales son incorrectas, por favor intente nuevamente");
	}

} 



