drop table if exists detalle_ventas;
drop table if exists cabecera_ventas;
drop table if exists historial_stock;
drop table if exists detalle_pedido;
drop table if exists cabecera_pedido;
drop table if exists estado_pedido;
drop table if exists proveedores;
drop table if exists producto;
drop table if exists unidades_medida;
drop table if exists categoria_unidad_medida;
drop table if exists categorias;

create table categorias (
    codigo_cat serial not null,
    nombre varchar(100) not null,
    categoria_padre int,
    constraint categorias_pk primary key (codigo_cat),
    constraint categorias_fk foreign key (categoria_padre) references categorias(codigo_cat)
);

create table categoria_unidad_medida (
    codigo_cum char(1) not null,
    nombre_cum varchar(50),
    constraint categoria_unidad_medida_pk primary key (codigo_cum)
);

create table unidades_medida (
    nombre_udm char(2) not null,
    descripcion_udm varchar(50),
    categoria_udm char(1) not null,
    constraint unidades_medida_pk primary key (nombre_udm),
    constraint unidades_medida_fk foreign key (categoria_udm) references categoria_unidad_medida(codigo_cum)
);

create table producto (
    codigo_pk serial primary key,
    nombre varchar(50) not null,
    udm_fk char(2) references unidades_medida(nombre_udm),
    precio_venta money not null,
    tiene_iva boolean not null,
    coste money,
    categoria_fk int references categorias(codigo_cat),
    stock int default 0
);

create table proveedores (
    identificador_pk varchar(15) primary key,
    tipo_documento char(1),
    nombre varchar(100),
    telefono varchar(15),
    correo varchar(100),
    direccion varchar(100)
);

create table estado_pedido (
    codigo_pk char(1) primary key,
    descripcion varchar(50)
);

create table cabecera_pedido (
    numero_pk serial primary key,
    proveedor_fk varchar(15) references proveedores(identificador_pk),
    fecha date not null,
    estado_fk char(1) references estado_pedido(codigo_pk)
);

create table detalle_pedido (
    codigo_pk serial primary key,
    cabecera_pedido_fk int references cabecera_pedido(numero_pk),
    producto_fk int references producto(codigo_pk),
    cantidad_solicitada int not null,
    subtotal money,
    cantidad_recibida int
);

create table historial_stock (
    codigo_serial serial primary key,
    fecha timestamp not null,
    referencia varchar(100),
    producto_fk int references producto(codigo_pk),
    cantidad_int int
);

create table cabecera_ventas (
    codigo serial primary key,
    fecha timestamp not null,
    total_sin_iva money,
    iva money,
    total money
);

create table detalle_ventas (
    codigo serial primary key,
    cabecera_ventas_fk int references cabecera_ventas(codigo),
    producto_fk int references producto(codigo_pk),
    cantidad int not null,
    precio_venta money,
    subtotal money,
    subtotal_con_iva money
);

insert into categorias (nombre, categoria_padre) values
('materia prima', null),
('proteina', 1),
('salsas', 1),
('punto de venta', null),
('bebidas', 4),
('con alcohol', 5),
('sin alcohol', 5);

insert into categoria_unidad_medida (codigo_cum, nombre_cum) values
('u', 'unidades'),
('v', 'volumen'),
('p', 'peso');

insert into unidades_medida (nombre_udm, descripcion_udm, categoria_udm) values
('ml', 'mililitros', 'v'),
('l', 'litros', 'v'),
('u', 'unidad', 'u'),
('d', 'docena', 'u'),
('g', 'gramos', 'p'),
('kg', 'kilogramos', 'p'),
('lb', 'libras', 'p');

insert into producto (nombre, udm_fk, precio_venta, tiene_iva, coste, categoria_fk, stock) values
('coca-cola pequeña', 'u', 0.58, true, 0.3729, 7, 105),
('salsa de tomate', 'kg', 0.95, true, 0.8736, 3, 0),
('mayonesa', 'kg', 1.10, true, 0.9, 3, 0),
('feuz tea', 'u', 0.80, true, 0.7, 7, 49);

insert into proveedores (identificador_pk, tipo_documento, nombre, telefono, correo, direccion) values
('4172601098798', 'r', 'juan torres', '998890987', 'juantorres@mail.com', 'guajalo'),
('098765432', 'c', 'maria galarza', '987657898', 'mariamaria@gmail.com', 'ambato');

insert into estado_pedido (codigo_pk, descripcion) values
('s', 'solicitado'),
('r', 'recibido');

insert into cabecera_pedido (proveedor_fk, fecha, estado_fk) values
('4172601098798', '2024-11-30', 'r'),
('4172601098798', '2024-10-30', 'r');

insert into detalle_pedido (cabecera_pedido_fk, producto_fk, cantidad_solicitada, subtotal, cantidad_recibida) values
(1, 1, 100, 37.29, 100),
(1, 4, 50, 11.8, 50),
(2, 1, 10, 3.73, 10);

insert into historial_stock (fecha, referencia, producto_fk, cantidad_int) values
('2024-11-11 19:09:54', 'pedido 1', 1, 100),
('2024-11-11 19:09:54', 'pedido 1', 4, 50),
('2024-11-11 19:09:54', 'pedido 2', 1, 10),
('2024-11-12 13:09:54', 'venta 1', 1, -5),
('2024-11-12 13:09:54', 'venta 1', 4, -1);

insert into cabecera_ventas (fecha, total_sin_iva, iva, total) values
('2024-11-11 20:09:54', 3.26, 0.39, 3.65);

insert into detalle_ventas (cabecera_ventas_fk, producto_fk, cantidad, precio_venta, subtotal, subtotal_con_iva) values
(1, 1, 5, 0.58, 2.9, 3.25),
(1, 4, 1, 0.36, 0.36, 0.4);

select * from Detalle_Ventas;
select * from Cabecera_Ventas;
select * from Historial_Stock;
select * from Detalle_Pedido;
select * from Cabecera_Pedido;
select * from Estado_Pedido;
select * from Proveedores;
select * from Producto;
select * from Unidades_Medida;
select * from Categoria_Unidad_Medida;
select * from Categorias;