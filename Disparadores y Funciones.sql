/* Disparadores  y funciones*/
set search_path to sistemaTusug;
create view sesiones as
	select rfc, nombre, descripcion as puesto, contrasenia
	from rol 
		natural join usuario
		natural join trabajador
		
/* Disparador para crear Usuarion*/
create or replace function func_CreateNewUser() returns trigger as $$
declare
	idPuesto int;
begin	
	if NEW.puesto = 'secretaria' OR NEW.puesto = 'mantenimiento' OR NEW.puesto = 'recursos humanos' OR NEW.puesto = 'almacen' then
		select id_rol into idPuesto from rol where descripcion like NEW.puesto;
		insert into usuario values(NEW.rfc, idPuesto, MD5(NEW.rfc), NEW.url_img, current_date);
	else return null;
	end if;
	return new;
end$$language plpgsql;

CREATE TRIGGER process_CreateNewUser AFTER INSERT ON trabajador
FOR EACH ROW EXECUTE PROCEDURE func_CreateNewUser();
