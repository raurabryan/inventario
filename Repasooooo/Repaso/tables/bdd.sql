create table buses (
	placa varchar(8)primary key,
	cap_max int not null
);
create table usuarios(
	id_us serial primary key,
	nombre varchar(100) not null,
	correo varchar(100)not null
);
create table rutas(
	id_ruta serial primary key,
	origen varchar(50) not null,
	destino varchar(50)not null,
	horario time not null
);
create table reservas(
	id_res serial primary key,
	id_us int not null,
	id_rut int not null,
	foreign key (id_us) references usuarios(id_us),
    foreign key (id_rut) references rutas(id_ruta)
)