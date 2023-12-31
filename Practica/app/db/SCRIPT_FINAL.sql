INSERT INTO ADMINPUCELACAR.DIRECCIONES (ID, NOMBREDELAVIA, NUMERO, OTROS, CODIGOPOSTAL, LOCALIDAD, PROVINCIA) 
	VALUES (1, 'Calle Mayor', 12, '3º A', 28001, 'Madrid', 'Madrid');
INSERT INTO ADMINPUCELACAR.DIRECCIONES (ID, NOMBREDELAVIA, NUMERO, OTROS, CODIGOPOSTAL, LOCALIDAD, PROVINCIA) 
	VALUES (2, 'Calle de la Princesa', 45, NULL, 8003, 'Barcelona', 'Barcelona');


INSERT INTO ADMINPUCELACAR.PUNTOS (ID, NOMBRE, EMAIL, TELEFONO, LOCALIZACION) 
	VALUES (1, 'Juan', 'juan@example.com', '555-1234', 1);
INSERT INTO ADMINPUCELACAR.PUNTOS (ID, NOMBRE, EMAIL, TELEFONO, LOCALIZACION) 
	VALUES (2, 'Maria', 'maria@example.com', '555-5678', 2);


INSERT INTO ADMINPUCELACAR.USUARIOS (NIF, NOMBRE, PASSWORD, EMAIL, TELEFONO, DIRECCIONPOSTAL) 
	VALUES ('12345678A', 'Juan Perez', 'clave123', 'juan.perez@email.com', '123456789', 1);
INSERT INTO ADMINPUCELACAR.USUARIOS (NIF, NOMBRE, PASSWORD, EMAIL, TELEFONO, DIRECCIONPOSTAL) 
	VALUES ('98765432B', 'Maria Lopez', 'clave456', 'maria.lopez@email.com', '987654321', 2);
INSERT INTO ADMINPUCELACAR.USUARIOS (NIF, NOMBRE, PASSWORD, EMAIL, TELEFONO, DIRECCIONPOSTAL) 
	VALUES ('12345678C', 'Juanjo molino', 'clave789', 'juanjo.molino@email.com', '123456789', 1);


INSERT INTO ADMINPUCELACAR.EMPLEADOS (NIF, NUMEROSEGURIDADSOCIAL, IBAN, FECHAINICIOENEMPRESA, DESTINADOEN) 
	VALUES ('12345678A', '123456789012', 'ES0123456789012345678901', '2020-01-01', 1);
INSERT INTO ADMINPUCELACAR.EMPLEADOS (NIF, NUMEROSEGURIDADSOCIAL, IBAN, FECHAINICIOENEMPRESA, DESTINADOEN) 
	VALUES ('98765432B', '987654321098', 'ES9876543210987654321098', '2019-01-01', 1);
INSERT INTO ADMINPUCELACAR.EMPLEADOS (NIF, NUMEROSEGURIDADSOCIAL, IBAN, FECHAINICIOENEMPRESA, DESTINADOEN) 
	VALUES ('12345678C', '123456789015', 'ES0123456789012345678906', '2020-01-01', 1);


INSERT INTO ADMINPUCELACAR.ROLESENEMPRESA (COMIENZOENROL, EMPLEADO, ROL) 
	VALUES ('2020-01-01', '12345678A', 1);
INSERT INTO ADMINPUCELACAR.ROLESENEMPRESA (COMIENZOENROL, EMPLEADO, ROL) 
	VALUES ('2020-01-01', '98765432B', 2);
INSERT INTO ADMINPUCELACAR.ROLESENEMPRESA (COMIENZOENROL, EMPLEADO, ROL) 
	VALUES ('2020-01-01', '12345678C', 3);



INSERT INTO ADMINPUCELACAR.VINCULACIONESCONLAEMPRESA (INICIO, EMPLEADO, VINCULO) 
	VALUES ('2020-01-01', '12345678A', 1);
INSERT INTO ADMINPUCELACAR.VINCULACIONESCONLAEMPRESA (INICIO, EMPLEADO, VINCULO) 
	VALUES ('2019-01-01', '98765432B', 1);
INSERT INTO ADMINPUCELACAR.VINCULACIONESCONLAEMPRESA (INICIO, EMPLEADO, VINCULO) 
	VALUES ('2020-01-01', '12345678C', 1);


INSERT INTO ADMINPUCELACAR.DISPONIBILIDADES (COMIENZO, FINALPREVISTO, EMPLEADO, DISPONIBILIDAD) 
	VALUES ('2023-04-18', '2023-04-19', '12345678A', 3);
INSERT INTO ADMINPUCELACAR.DISPONIBILIDADES (COMIENZO, FINALPREVISTO, EMPLEADO, DISPONIBILIDAD) 
	VALUES ('2023-04-22', '2023-04-23', '98765432B', 3);
INSERT INTO ADMINPUCELACAR.DISPONIBILIDADES (COMIENZO, FINALPREVISTO, EMPLEADO, DISPONIBILIDAD) 
	VALUES ('2023-04-18', '2023-04-19', '12345678C', 3);


INSERT INTO ADMINPUCELACAR.CLIENTES (NIF, NUMEROCLIENTE, TARJETACREDITO) 
	VALUES ('98765432B', 1, '1234567812345678');
INSERT INTO ADMINPUCELACAR.CLIENTES (NIF, NUMEROCLIENTE, TARJETACREDITO) 
	VALUES ('12345678A', 2, '1111222233334444');



INSERT INTO ADMINPUCELACAR.MARCAS (ID, NOMBRE) 
	VALUES (1, 'Toyota');
INSERT INTO ADMINPUCELACAR.MARCAS (ID, NOMBRE) 
	VALUES (2, 'Ford');


INSERT INTO ADMINPUCELACAR.MODELOS (ID, NOMBRE, PUERTAS, LARGO, ANCHO, ALTO, CAPACIDADMALETERO, PASAJEROS, MARCA, CATEGORIATAMANO, CATEGORIAGAMA) 
	VALUES (1, 'Yaris', 5, 3.95, 1.73, 1.5, 286.0, 5, 1, 1, 1);
INSERT INTO ADMINPUCELACAR.MODELOS (ID, NOMBRE, PUERTAS, LARGO, ANCHO, ALTO, CAPACIDADMALETERO, PASAJEROS, MARCA, CATEGORIATAMANO, CATEGORIAGAMA) 
	VALUES (2, 'Fiesta', 3, 4.04, 1.73, 1.48, 292.0, 5, 2, 1, 1);


INSERT INTO ADMINPUCELACAR.VEHICULOS (MATRICULA, COLOR, ESTADO, PUNTOASIGNADO, SITUADOEN, MODELO) 
	VALUES ('1234ABC', 'Azul', 3, 1, 2, 1);
INSERT INTO ADMINPUCELACAR.VEHICULOS (MATRICULA, COLOR, ESTADO, PUNTOASIGNADO, SITUADOEN, MODELO) 
	VALUES ('5678DEF', 'Rojo', 4, 2, 1, 2);



INSERT INTO ADMINPUCELACAR.RESERVAS (ID, ESTADO, FECHARESERVA, FECHAINICIO, HORAINICIO, FECHAFIN, HORAFIN, VEHICULO, PUNTORECOGIDA, PUNTOENTREGA, CLIENTE) 
	VALUES (1, 5, '2023-05-01', '2023-05-02', '08:00:00', '2023-05-05', '12:00:00', '1234ABC', 1, 1, '98765432B');
INSERT INTO ADMINPUCELACAR.RESERVAS (ID, ESTADO, FECHARESERVA, FECHAINICIO, HORAINICIO, FECHAFIN, HORAFIN, VEHICULO, PUNTORECOGIDA, PUNTOENTREGA, CLIENTE) 
	VALUES (2, 5, '2023-05-06', '2023-05-10', '10:00:00', '2023-05-15', '12:00:00', '1234ABC', 1, 1, '12345678A');



INSERT INTO ADMINPUCELACAR.ALQUILERES (ID, MOMENTO, EMPLEADO, RESERVA) 
	VALUES (1, '2023-05-26 12:00:00.0', '98765432B', 1);




INSERT INTO ADMINPUCELACAR.TALLERES (ID, NOMBRE, TELEFONO, EMAIL, LOCALIZACION) 
	VALUES (1, 'Taller 1', '981345421', 'taller1@gmail.com', 1);
INSERT INTO ADMINPUCELACAR.TALLERES (ID, NOMBRE, TELEFONO, EMAIL, LOCALIZACION) 
	VALUES (2, 'Taller 2', '988845421', 'taller2@gmail.com', 2);


INSERT INTO ADMINPUCELACAR.ORDENESDEREPARACION (ID, MOMENTO, RAZONES, VEHICULO, TALLER) 
	VALUES (1, '2023-05-22 10:00:00.0', 'Razones de la reparación 1', '1234ABC', 1);
INSERT INTO ADMINPUCELACAR.ORDENESDEREPARACION (ID, MOMENTO, RAZONES, VEHICULO, TALLER) 
	VALUES (2, '2023-05-23 14:30:00.0', 'Razones de la reparación 2', '5678DEF', 1);



INSERT INTO ADMINPUCELACAR.REGISTROSENTRADASALIDATALLER (ORDENDEREPARACION, LLEGADAALTALLER, SALIDADELTALLER, ENTRADAENPUNTO, PROPUESTOPARABAJA, RAZONESPARABAJA) 
	VALUES (1, '2023-05-22 10:30:00.0', '2023-05-22 15:00:00.0', NULL, false, 'cacharro');
INSERT INTO ADMINPUCELACAR.REGISTROSENTRADASALIDATALLER (ORDENDEREPARACION, LLEGADAALTALLER, SALIDADELTALLER, ENTRADAENPUNTO, PROPUESTOPARABAJA, RAZONESPARABAJA) 
	VALUES (2, '2023-05-23 15:00:00.0', '2023-05-24 11:30:00.0', NULL, false, 'cacharro');