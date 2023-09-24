-- Reset database
--Derby does not support DROP TABLE IF EXISTS 

DROP TABLE REGISTROSENTRADASALIDATALLER;
DROP TABLE ORDENESDEREPARACION;
DROP TABLE TALLERES;
DROP TABLE INCIDENCIAS;
DROP TABLE TIPOSDEINCIDENCIA;
DROP TABLE ENTREGAS;
DROP TABLE ALQUILERES;
DROP TABLE RESERVAS;
DROP TABLE ESTADOSRESERVA;
DROP TABLE VEHICULOS;
DROP TABLE MODELOS;
DROP TABLE MARCAS;
DROP TABLE ESTADOSVEHICULO;
DROP TABLE CATEGORIASDETAMANO;
DROP TABLE CATEGORIASDEGAMA;

DROP TABLE DISPONIBILIDADES;
DROP TABLE VINCULACIONESCONLAEMPRESA;
DROP TABLE ROLESENEMPRESA;
DROP TABLE TIPOSDEDISPONIBILIDAD;
DROP TABLE TIPOSDEVINCULACION;
DROP TABLE TIPOSDEROL;

DROP TABLE PERFILES;
DROP TABLE CLIENTES;
DROP TABLE EMPLEADOS;
DROP TABLE USUARIOS;
DROP TABLE PUNTOS;
DROP TABLE DIRECCIONES;





-- Enum
create table TIPOSDEROL
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPOSDEROL
VALUES  (1,'GerenteDePunto'),
        (2,'AtencionEnPunto'),
        (3,'TecnicoEnPunto');

-- Enum
create table TIPOSDEVINCULACION
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)

);

INSERT INTO TIPOSDEVINCULACION
VALUES  (1,'Contratado'),
        (2,'Despedido'),
        (3,'EnERTE');

-- Enum
create table TIPOSDEDISPONIBILIDAD
(
	IdTipo SMALLINT not null,
	NombreTipo VARCHAR(20) not null unique,
		PRIMARY KEY(IdTipo)
);

INSERT INTO TIPOSDEDISPONIBILIDAD
VALUES  (1,'Vacaciones'),
        (2,'BajaTemporal'),
	(3, 'Trabajando');


-- Datatype
create table DIRECCIONES
(
	Id SMALLINT not null,
	NombreDeLaVia VARCHAR(20) not null,
	Numero SMALLINT,
	Otros VARCHAR(20),
	CodigoPostal SMALLINT not null,
	Localidad VARCHAR(20) not null,
	Provincia VARCHAR(20) not null,
		PRIMARY KEY(Id)
);

-- Entity
create table PUNTOS
(
	Id SMALLINT not null primary key,
	Nombre VARCHAR(50) not null,
	Email VARCHAR(100) not null,
	Telefono VARCHAR(12) not null,
	Localizacion SMALLINT not null,
		FOREIGN KEY(Localizacion) REFERENCES DIRECCIONES(Id)
);

-- Entity
create table USUARIOS
(
	Nif VARCHAR(9) not null primary key,
	Nombre VARCHAR(50) not null,
	Password VARCHAR(15) not null,
	Email VARCHAR(100) not null,
	Telefono VARCHAR(12) not null,
	DireccionPostal SMALLINT not null,
		FOREIGN KEY(DireccionPostal) REFERENCES DIRECCIONES(Id)
);

-- Entity
create table EMPLEADOS
(
	Nif VARCHAR(9) not null primary key,
	NumeroSeguridadSocial VARCHAR(12) not null,
	Iban VARCHAR(24) not null,
	FechaInicioEnEmpresa DATE not null,
	DestinadoEn SMALLINT not null,
            FOREIGN KEY(Nif) REFERENCES USUARIOS(Nif),
            FOREIGN KEY(DestinadoEn) REFERENCES PUNTOS(Id)
);

-- Association
create table ROLESENEMPRESA
(
	ComienzoEnRol DATE not null,
	Empleado VARCHAR(9) not null,
	Rol SMALLINT not null,
            FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
            FOREIGN KEY(Rol) REFERENCES TIPOSDEROL(IdTipo)
);

-- Association
create table VINCULACIONESCONLAEMPRESA
(
	inicio DATE not null,
	Empleado VARCHAR(9) not null,
	Vinculo SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Vinculo) REFERENCES TIPOSDEVINCULACION(IdTipo) 
);

-- Association
create table DISPONIBILIDADES
(
	Comienzo DATE not null,
	FinalPrevisto DATE,
	Empleado VARCHAR(9) not null,
	Disponibilidad SMALLINT not null,
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Disponibilidad) REFERENCES TIPOSDEDISPONIBILIDAD(IdTipo)
);

-- Entity
create table CLIENTES
(	Nif VARCHAR(9) not null,
	NumeroCliente INTEGER not null unique,
	TarjetaCredito VARCHAR(16) not null,
		PRIMARY KEY(Nif),
		FOREIGN KEY(Nif) REFERENCES USUARIOS(Nif)
);

-- Entity
create table PERFILES
(
	NumeroCliente INTEGER not null,
	Puntos SMALLINT not null,
		PRIMARY KEY(NumeroCliente),
		FOREIGN KEY(NumeroCliente) REFERENCES CLIENTES(NumeroCliente)
);

-- Powertype
create table CATEGORIASDEGAMA
(
	Id SMALLINT not null,
	Nombre VARCHAR(10) not null unique,
	Prestaciones VARCHAR(250) not null,
	ExtraPrecioAlDia REAL not null,
		PRIMARY KEY(Id)
);

INSERT INTO CATEGORIASDEGAMA
VALUES (1, 'Media', 'Una descripción de gama media', 0),
       (2, 'Alta', 'Una descripción de gama alta', 10);

-- Powertype
create table CATEGORIASDETAMANO 
(
	Id SMALLINT not null,
	Nombre VARCHAR(10) not null unique,
	Minimo REAL not null,
	Maximo REAL not null,
	PrecioPorDia REAL not null,
	FactorEntregaEnOtroPunto REAL not null,
		PRIMARY KEY(Id)
);

INSERT INTO CATEGORIASDETAMANO
VALUES  (1, 'Pequeño', 2.4, 3.5, 10, 1.3),
	(2, 'Mediano', 3.6, 4.5, 15, 1.7),
	(3, 'Grande', 4.6, 6, 20, 2.0);

-- Enum
create table ESTADOSVEHICULO
(
	Id SMALLINT not null,
	Nombre VARCHAR(30) not null unique,
		PRIMARY KEY(Id)
);

INSERT INTO ESTADOSVEHICULO
VALUES  (1, 'Disponible'),
        (2, 'EnAlquiler'),
        (3, 'EnReserva'),
        (4, 'EnTaller'),
        (5, 'Averiado'),
        (6, 'EnPreparación'),
        (7, 'PropuestoParaBaja');

-- Entity
create table MARCAS
(
	Id SMALLINT not null,
	Nombre VARCHAR(20) not null unique,
		PRIMARY KEY(Id)
);

-- Entity
create table MODELOS
(
	Id SMALLINT not null,
	Nombre VARCHAR(20) not null unique,
	Puertas SMALLINT not null,
	Largo REAL not null,
	Ancho REAL not null,
	Alto REAL not null,
	CapacidadMaletero REAL not null,
	Pasajeros SMALLINT not null,
	Marca SMALLINT not null,
	CategoriaTamano SMALLINT not null,
	CategoriaGama SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Marca) REFERENCES MARCAS(Id),
		FOREIGN KEY(CategoriaTamano) REFERENCES CATEGORIASDETAMANO(Id),
		FOREIGN KEY(CategoriaGama) REFERENCES CATEGORIASDEGAMA(Id)
);

-- Entity
create table VEHICULOS
(
	Matricula VARCHAR(7) not null,
	Color VARCHAR(20) not null,
	Estado SMALLINT not null,
	PuntoAsignado SMALLINT not null,
	SituadoEn SMALLINT,
	Modelo SMALLINT not null,
		PRIMARY KEY(Matricula),
		FOREIGN KEY(Estado) REFERENCES ESTADOSVEHICULO,
		FOREIGN KEY(PuntoAsignado) REFERENCES PUNTOS(Id),
		FOREIGN KEY(SituadoEn) REFERENCES PUNTOS(Id),
		FOREIGN KEY(Modelo) REFERENCES MODELOS(Id)
);

-- Enum
create table ESTADOSRESERVA
(
	Id SMALLINT not null,
	Nombre VARCHAR(30) not null unique,
		PRIMARY KEY(Id)

);

INSERT INTO ESTADOSRESERVA
VALUES  (1,'Solicitada'),
        (2,'EnFirme'),
        (3, 'Anulada'),
        (4, 'Facturada'),
        (5, 'EnAlquiler'),
        (6,'Finalizada');

-- Entity
create table RESERVAS
(
	Id INTEGER not null,
	Estado SMALLINT not null,
	FechaReserva DATE not null,
	FechaInicio DATE not null,
	HoraInicio TIME not null,
	HechaFin DATE not null,
	HoraFin TIME not null,
	Vehiculo VARCHAR(7) not null,
	PuntoRecogida SMALLINT not null,
	PuntoEntrega SMALLINT not null,
	Cliente VARCHAR(9) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Estado) REFERENCES ESTADOSRESERVA(Id),
		FOREIGN KEY(Vehiculo) REFERENCES VEHICULOS(Matricula),
		FOREIGN KEY(PuntoRecogida) REFERENCES PUNTOS(Id),
		FOREIGN KEY(PuntoEntrega) REFERENCES PUNTOS(Id),
		FOREIGN KEY(Cliente) REFERENCES CLIENTES(Nif)

);

-- Entity. Transaction.
create table ALQUILERES
(
	Id INTEGER not null,
	Momento TIMESTAMP not null,
	Empleado VARCHAR(9) not null,
	Reserva INTEGER not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif),
		FOREIGN KEY(Reserva) REFERENCES RESERVAS(Id)
);

-- Entity. Transaction.
create table ENTREGAS
(
	Id INTEGER not null,
	Momento TIMESTAMP not null,
	Alquiler INTEGER not null,
	Empleado VARCHAR(9) not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Alquiler) REFERENCES ALQUILERES(Id),
		FOREIGN KEY(Empleado) REFERENCES EMPLEADOS(Nif)
);

-- Enum
create table TIPOSDEINCIDENCIA
(
	Id SMALLINT not null,
	Nombre VARCHAR(20) not null unique,
		PRIMARY KEY(Id)
);

INSERT INTO TIPOSDEINCIDENCIA
VALUES  (1,'Accidente'),
        (2,'Avería'),
        (3,'SuciedadExtrema'),
        (4,'RetrasoEnLaEntrega');

-- Entity
create table INCIDENCIAS
(
	Id INTEGER not null,
	Tipo SMALLINT not null,
	Descripcion VARCHAR(250) not null,
	CargoAsociado REAL,
	Entrega INTEGER not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Tipo) REFERENCES TIPOSDEINCIDENCIA(Id),
		FOREIGN KEY(Entrega) REFERENCES ENTREGAS(Id)
);

-- Entity
create table TALLERES
(
	Id INTEGER not null,
	Nombre VARCHAR(50) not null,
	Telefono VARCHAR(12) not null,
	Email VARCHAR(100) not null,
	Localizacion SMALLINT not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Localizacion) REFERENCES DIRECCIONES(Id)
);

-- Association + Data
create table ORDENESDEREPARACION
(
	Id INTEGER not null,
	Momento TIMESTAMP not null,
	Razones VARCHAR(250) not null,
	Vehiculo VARCHAR(7) not null,
	Taller INTEGER not null,
		PRIMARY KEY(Id),
		FOREIGN KEY(Vehiculo) REFERENCES VEHICULOS(Matricula),
		FOREIGN KEY(Taller) REFERENCES TALLERES(Id)
);

-- Auxiliary
create table REGISTROSENTRADASALIDATALLER
(
	OrdenDeReparacion INTEGER not null,
	LlegadaAlTaller TIMESTAMP,
	SalidaDelTaller TIMESTAMP,
	EntradaEnPunto TIMESTAMP,
	PropuestoParaBaja BOOLEAN,
	RazonesParaBaja VARCHAR(250),
		FOREIGN KEY(OrdenDeReparacion) REFERENCES ORDENESDEREPARACION(Id)
);