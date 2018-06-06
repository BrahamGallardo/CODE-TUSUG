/* Disparadores  y funciones*/
set search_path to sistemaTusug;
create view sesiones as
	select rfc, nombre, descripcion as puesto, contrasenia
	from rol 
		natural join usuario
		natural join trabajador