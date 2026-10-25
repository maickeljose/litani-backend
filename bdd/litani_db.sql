-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-10-2026 a las 02:30:08
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `litani_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargas`
--

CREATE TABLE `cargas` (
  `id_carga` bigint(20) NOT NULL,
  `camion` varchar(60) NOT NULL,
  `chofer` varchar(80) DEFAULT NULL,
  `estado` varchar(10) NOT NULL DEFAULT 'ABIERTA',
  `fecha_inicio` datetime NOT NULL,
  `fecha_cierre` datetime DEFAULT NULL,
  `id_creador` bigint(20) NOT NULL,
  `id_cierre` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cargas`
--

INSERT INTO `cargas` (`id_carga`, `camion`, `chofer`, `estado`, `fecha_inicio`, `fecha_cierre`, `id_creador`, `id_cierre`) VALUES
(1, '00012', 'hola', 'CERRADA', '2026-10-25 00:54:52', '2026-10-25 00:54:58', 3, 3),
(2, 'ghjj', 'hola', 'CERRADA', '2026-10-25 01:05:16', '2026-10-25 01:05:22', 4, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estivas`
--

CREATE TABLE `estivas` (
  `id_estiva` int(11) NOT NULL,
  `numero_estiva` int(11) NOT NULL,
  `id_fila` int(11) NOT NULL,
  `capacidad_maxima` int(11) NOT NULL DEFAULT 50,
  `bultos_actuales` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estivas`
--

INSERT INTO `estivas` (`id_estiva`, `numero_estiva`, `id_fila`, `capacidad_maxima`, `bultos_actuales`) VALUES
(1, 1, 1, 50, 0),
(2, 1, 2, 50, 0),
(3, 1, 3, 50, 0),
(4, 1, 4, 50, 0),
(5, 1, 5, 50, 0),
(6, 1, 6, 50, 0),
(7, 1, 7, 50, 0),
(8, 1, 8, 50, 0),
(9, 1, 9, 50, 0),
(10, 2, 1, 50, 0),
(11, 2, 2, 50, 0),
(12, 2, 3, 50, 0),
(13, 2, 4, 50, 0),
(14, 2, 5, 50, 0),
(15, 2, 6, 50, 0),
(16, 2, 7, 50, 0),
(17, 2, 8, 50, 0),
(18, 2, 9, 50, 0),
(19, 3, 1, 50, 0),
(20, 3, 2, 50, 0),
(21, 3, 3, 50, 0),
(22, 3, 4, 50, 0),
(23, 3, 5, 50, 0),
(24, 3, 6, 50, 0),
(25, 3, 7, 50, 0),
(26, 3, 8, 50, 0),
(27, 3, 9, 50, 0),
(28, 4, 1, 50, 0),
(29, 4, 2, 50, 0),
(30, 4, 3, 50, 0),
(31, 4, 4, 50, 0),
(32, 4, 5, 50, 0),
(33, 4, 6, 50, 0),
(34, 4, 7, 50, 0),
(35, 4, 8, 50, 0),
(36, 4, 9, 50, 0),
(37, 5, 1, 50, 0),
(38, 5, 2, 50, 0),
(39, 5, 3, 50, 0),
(40, 5, 4, 50, 0),
(41, 5, 5, 50, 0),
(42, 5, 6, 50, 0),
(43, 5, 7, 50, 0),
(44, 5, 8, 50, 0),
(45, 5, 9, 50, 0),
(46, 6, 1, 50, 0),
(47, 6, 2, 50, 0),
(48, 6, 3, 50, 0),
(49, 6, 4, 50, 0),
(50, 6, 5, 50, 0),
(51, 6, 6, 50, 0),
(52, 6, 7, 50, 0),
(53, 6, 8, 50, 0),
(54, 6, 9, 50, 0),
(55, 7, 1, 50, 0),
(56, 7, 2, 50, 0),
(57, 7, 3, 50, 0),
(58, 7, 4, 50, 0),
(59, 7, 5, 50, 0),
(60, 7, 6, 50, 0),
(61, 7, 7, 50, 0),
(62, 7, 8, 50, 0),
(63, 7, 9, 50, 0),
(64, 8, 1, 50, 0),
(65, 8, 2, 50, 0),
(66, 8, 3, 50, 0),
(67, 8, 4, 50, 0),
(68, 8, 5, 50, 0),
(69, 8, 6, 50, 0),
(70, 8, 7, 50, 0),
(71, 8, 8, 50, 0),
(72, 8, 9, 50, 0),
(73, 9, 1, 50, 0),
(74, 9, 2, 50, 0),
(75, 9, 3, 50, 0),
(76, 9, 4, 50, 0),
(77, 9, 5, 50, 0),
(78, 9, 6, 50, 0),
(79, 9, 7, 50, 0),
(80, 9, 8, 50, 0),
(81, 9, 9, 50, 0),
(82, 10, 1, 50, 0),
(83, 10, 2, 50, 0),
(84, 10, 3, 50, 0),
(85, 10, 4, 50, 0),
(86, 10, 5, 50, 0),
(87, 10, 6, 50, 0),
(88, 10, 7, 50, 0),
(89, 10, 8, 50, 0),
(90, 10, 9, 50, 0),
(91, 1, 10, 50, 0),
(92, 2, 10, 50, 0),
(93, 3, 10, 50, 0),
(94, 4, 10, 50, 0),
(95, 5, 10, 50, 0),
(96, 6, 10, 50, 0),
(97, 7, 10, 50, 0),
(98, 8, 10, 50, 0),
(99, 9, 10, 50, 0),
(100, 10, 10, 50, 0),
(101, 1, 11, 50, 0),
(102, 2, 11, 50, 0),
(103, 3, 11, 50, 0),
(104, 4, 11, 50, 0),
(105, 5, 11, 50, 0),
(106, 6, 11, 50, 0),
(107, 7, 11, 50, 0),
(108, 8, 11, 50, 0),
(109, 9, 11, 50, 0),
(110, 10, 11, 50, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ferreterias`
--

CREATE TABLE `ferreterias` (
  `id_ferreteria` int(11) NOT NULL,
  `rif` varchar(20) NOT NULL,
  `razon_social` varchar(150) NOT NULL,
  `direccion` text NOT NULL,
  `estado_region` varchar(50) NOT NULL,
  `fecha_registro` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ferreterias`
--

INSERT INTO `ferreterias` (`id_ferreteria`, `rif`, `razon_social`, `direccion`, `estado_region`, `fecha_registro`) VALUES
(1, 'J-12345678-0', 'Ferretería El Candado C.A.', 'Av. Principal Calle 4', 'Zulia', '2026-10-14 01:39:02'),
(2, 'J-87654321-9', 'Materiales San José', 'Zona Industrial Lote 12', 'Zulia', '2026-10-14 01:39:02'),
(3, 'J-30501371-1', 'Materiales Santa Rita C.A.', 'Av. 5 de Julio, Maracaibo', 'Zulia', '2026-10-25 00:03:07'),
(4, 'J-30502742-2', 'Distribuidora El Maestro C.A.', 'Av. Intercomunal, Cabimas', 'Zulia', '2026-10-25 00:03:07'),
(5, 'J-30504113-3', 'Ferre Mi Casa C.A.', 'Av. Principal Sur, San Francisco', 'Zulia', '2026-10-25 00:03:07'),
(6, 'J-30505484-4', 'Tornillos y Santa Elena C.A.', 'Av. Bolívar, Ciudad Ojeda', 'Zulia', '2026-10-25 00:03:07'),
(7, 'J-30506855-5', 'Construcciones El Obrero C.A.', 'Av. Jesús Enrique Lossada, Punto Fijo', 'Falcón', '2026-10-25 00:03:07'),
(8, 'J-30508226-6', 'Suministros El Arado C.A.', 'Av. Los Médanos, Coro', 'Falcón', '2026-10-25 00:03:07'),
(9, 'J-30509597-7', 'Comercial San José C.A.', 'Av. Vargas, Barquisimeto', 'Lara', '2026-10-25 00:03:07'),
(10, 'J-30510968-8', 'Ferretería El Martillo C.A.', 'Av. Intercomunal, Cabudare', 'Lara', '2026-10-25 00:03:07'),
(11, 'J-30512339-9', 'Materiales Los Hermanos C.A.', 'Av. Carvajal, Valera', 'Trujillo', '2026-10-25 00:03:07'),
(12, 'J-30513710-0', 'Distribuidora El Pilar C.A.', 'Av. Independencia, Trujillo', 'Trujillo', '2026-10-25 00:03:07'),
(13, 'J-30515081-1', 'Ferre La Palma C.A.', 'Av. Las Américas, Mérida', 'Mérida', '2026-10-25 00:03:07'),
(14, 'J-30516452-2', 'Tornillos y Rio Claro C.A.', 'Av. Bolívar, El Vigía', 'Mérida', '2026-10-25 00:03:07'),
(15, 'J-30517823-3', 'Construcciones La Roca C.A.', 'Av. Carabobo, San Cristóbal', 'Táchira', '2026-10-25 00:03:07'),
(16, 'J-30519194-4', 'Suministros La Esperanza C.A.', 'Calle 8, Rubio', 'Táchira', '2026-10-25 00:03:07'),
(17, 'J-30520565-5', 'Comercial Central C.A.', 'Av. Industrial, Barinas', 'Barinas', '2026-10-25 00:03:07'),
(18, 'J-30521936-6', 'Ferretería Bolívar C.A.', 'Calle Real, Socopó', 'Barinas', '2026-10-25 00:03:07'),
(19, 'J-30523307-7', 'Materiales Maracay C.A.', 'Av. Unda, Guanare', 'Portuguesa', '2026-10-25 00:03:07'),
(20, 'J-30524678-8', 'Distribuidora El Ingeniero C.A.', 'Av. Libertador, Acarigua', 'Portuguesa', '2026-10-25 00:03:07'),
(21, 'J-30526049-9', 'Ferre Las Palmas C.A.', 'Av. 15, San Felipe', 'Yaracuy', '2026-10-25 00:03:07'),
(22, 'J-30527420-0', 'Tornillos y El Sembrador C.A.', 'Calle 6, Yaritagua', 'Yaracuy', '2026-10-25 00:03:07'),
(23, 'J-30528791-1', 'Construcciones El Constructor C.A.', 'Av. Bolívar Norte, Valencia', 'Carabobo', '2026-10-25 00:03:07'),
(24, 'J-30530162-2', 'Suministros Siglo XXI C.A.', 'Av. Salom, Puerto Cabello', 'Carabobo', '2026-10-25 00:03:07'),
(25, 'J-30531533-3', 'Comercial El Sol C.A.', 'Zona Industrial, Guacara', 'Carabobo', '2026-10-25 00:03:07'),
(26, 'J-30532904-4', 'Ferretería El Roble C.A.', 'Av. Constitución, Maracay', 'Aragua', '2026-10-25 00:03:07'),
(27, 'J-30534275-5', 'Materiales Costa Azul C.A.', 'Av. Ribas, La Victoria', 'Aragua', '2026-10-25 00:03:07'),
(28, 'J-30535646-6', 'Distribuidora La Mina C.A.', 'Av. Bermúdez, Los Teques', 'Miranda', '2026-10-25 00:03:07'),
(29, 'J-30537017-7', 'Ferre Oriente C.A.', 'Av. Intercomunal, Guarenas', 'Miranda', '2026-10-25 00:03:07'),
(30, 'J-30538388-8', 'Tornillos y Los Andes C.A.', 'Av. Fuerzas Armadas, Caracas', 'Distrito Capital', '2026-10-25 00:03:07'),
(31, 'J-30539759-9', 'Construcciones El Progreso C.A.', 'Av. Baralt, Caracas', 'Distrito Capital', '2026-10-25 00:03:07'),
(32, 'J-30541130-0', 'Suministros La Frontera C.A.', 'Av. Soublette, Maiquetía', 'La Guaira', '2026-10-25 00:03:07'),
(33, 'J-30542501-1', 'Comercial La Cumbre C.A.', 'Av. Intercomunal, Barcelona', 'Anzoátegui', '2026-10-25 00:03:07'),
(34, 'J-30543872-2', 'Ferretería Andina C.A.', 'Av. Municipal, Puerto La Cruz', 'Anzoátegui', '2026-10-25 00:03:07'),
(35, 'J-30545243-3', 'Materiales El Cardón C.A.', 'Av. Bolívar, Maturín', 'Monagas', '2026-10-25 00:03:07'),
(36, 'J-30546614-4', 'Distribuidora Occidente C.A.', 'Av. Guayana, Puerto Ordaz', 'Bolívar', '2026-10-25 00:03:07'),
(37, 'J-30547985-5', 'Ferre El Tornillo C.A.', 'Av. Táchira, Ciudad Bolívar', 'Bolívar', '2026-10-25 00:03:07'),
(38, 'J-30549356-6', 'Tornillos y La Llave C.A.', 'Av. Humboldt, Cumaná', 'Sucre', '2026-10-25 00:03:07'),
(39, 'J-30550727-7', 'Construcciones Nueva Era C.A.', 'Av. Intercomunal, San Fernando de Apure', 'Apure', '2026-10-25 00:03:07'),
(40, 'J-30552098-8', 'Suministros El Faro C.A.', 'Av. Bolívar, San Juan de los Morros', 'Guárico', '2026-10-25 00:03:07'),
(41, 'J-30553469-9', 'Comercial El Llano C.A.', 'Calle 5, Calabozo', 'Guárico', '2026-10-25 00:03:07'),
(42, 'J-30554840-0', 'Ferretería El Puente C.A.', 'Av. Libertador, San Carlos', 'Cojedes', '2026-10-25 00:03:07'),
(43, 'J-30556211-1', 'Materiales Los Samanes C.A.', 'Av. 4 de Mayo, Porlamar', 'Nueva Esparta', '2026-10-25 00:03:07'),
(44, 'J-30557582-2', 'Distribuidora La Unión C.A.', 'Av. Río Negro, Puerto Ayacucho', 'Amazonas', '2026-10-25 00:03:07'),
(45, 'J-30558953-3', 'Ferre Don Pedro C.A.', 'Av. Perimetral, Tucupita', 'Delta Amacuro', '2026-10-25 00:03:07');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `filas`
--

CREATE TABLE `filas` (
  `id_fila` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `estados_referencia` varchar(150) DEFAULT NULL,
  `zona` varchar(50) NOT NULL DEFAULT 'Área de carga',
  `orden` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `filas`
--

INSERT INTO `filas` (`id_fila`, `nombre`, `estados_referencia`, `zona`, `orden`) VALUES
(1, 'Fila 1', 'Caracas, Miranda, Monagas', 'Área de carga', 9),
(2, 'Fila 2', 'Carabobo, Portuguesa', 'Área de carga', 8),
(3, 'Fila 3', 'Merida, Tachira', 'Área de carga', 7),
(4, 'Fila 4', 'Aragua, Guarico, Cojedes', 'Área de carga', 6),
(5, 'Fila 5', 'Lara, Apure', 'Área de carga', 5),
(6, 'Fila 6', 'Anzoategui, Barinas, Amazonas', 'Área de carga', 4),
(7, 'Fila 7', 'Bolivar, Yaracuy', 'Área de carga', 3),
(8, 'Fila 8', 'Falcon, Trujillo', 'Área de carga', 2),
(9, 'Fila 9', 'La guaira, Sucre, Nueva Esparta', 'Área de carga', 1),
(10, 'Maracaibo', NULL, 'Zulia', 10),
(11, 'Newton', NULL, 'Zulia', 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id_pedido` int(11) NOT NULL,
  `codigo` varchar(30) NOT NULL,
  `tipo` varchar(10) NOT NULL DEFAULT 'OUT',
  `id_ferreteria` int(11) NOT NULL,
  `bultos` int(11) NOT NULL,
  `fecha_chequeo` datetime NOT NULL,
  `id_chequeador` int(11) NOT NULL,
  `nota` varchar(255) DEFAULT NULL,
  `situacion` varchar(20) NOT NULL DEFAULT 'CHEQUEADO',
  `fecha_ubicado` datetime DEFAULT NULL,
  `id_ubicador` int(11) DEFAULT NULL,
  `id_estiva` int(11) DEFAULT NULL,
  `fecha_cargado` datetime DEFAULT NULL,
  `id_carga` bigint(20) DEFAULT NULL,
  `id_cargador` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id_pedido`, `codigo`, `tipo`, `id_ferreteria`, `bultos`, `fecha_chequeo`, `id_chequeador`, `nota`, `situacion`, `fecha_ubicado`, `id_ubicador`, `id_estiva`, `fecha_cargado`, `id_carga`, `id_cargador`) VALUES
(1, '00001', 'OUT', 34, 8, '2026-10-25 00:12:08', 4, NULL, 'CARGADO', '2026-10-25 00:54:30', 3, 8, '2026-10-25 00:54:56', 1, 3),
(2, '00002', 'OUT', 1, 9, '2026-10-25 00:52:44', 3, NULL, 'CARGADO', '2026-10-25 00:54:34', 3, 9, '2026-10-25 00:54:55', 1, 3),
(3, '00003', 'OUT', 1, 5, '2026-10-25 01:04:58', 4, NULL, 'CARGADO', '2026-10-25 01:05:05', 4, 91, '2026-10-25 01:05:19', 2, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre_rol`) VALUES
(2, 'Chequeador'),
(1, 'Organizador'),
(4, 'Soporte'),
(3, 'Supervisor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `nombre_completo` varchar(100) NOT NULL,
  `nombre_usuario` varchar(50) NOT NULL,
  `clave_hash` varchar(255) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `estado` enum('Activo','Inactivo') DEFAULT 'Activo',
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `cedula`, `nombre_completo`, `nombre_usuario`, `clave_hash`, `id_rol`, `estado`, `fecha_creacion`) VALUES
(1, 'V-00000000', 'Soporte Técnico', 'soporte', 'admin123', 4, 'Activo', '2026-10-14 01:39:02'),
(2, 'V-11111111', 'Maickel davila\r\n', 'chequeador_01', '123456', 2, 'Activo', '2026-10-14 01:39:02'),
(3, 'V-22222222', 'Encargado Almacén', 'organizador_01', '123456', 1, 'Activo', '2026-10-14 02:46:11'),
(4, 'V-21000002', 'Luis Fernández', 'chequeador_02', '123456', 2, 'Activo', '2026-10-25 00:02:47'),
(5, 'V-21000003', 'María González', 'chequeador_03', '123456', 2, 'Activo', '2026-10-25 00:02:47'),
(6, 'V-21000004', 'José Ramírez', 'chequeador_04', '123456', 2, 'Activo', '2026-10-25 00:02:47'),
(7, 'V-21000005', 'Carla Mendoza', 'chequeador_05', '123456', 2, 'Activo', '2026-10-25 00:02:47'),
(8, 'V-22000002', 'Carlos Salazar', 'organizador_02', '123456', 1, 'Activo', '2026-10-25 00:02:47'),
(9, 'V-22000003', 'Daniela Useche', 'organizador_03', '123456', 1, 'Activo', '2026-10-25 00:02:47'),
(10, 'V-22000004', 'Miguel Briceño', 'organizador_04', '123456', 1, 'Activo', '2026-10-25 00:02:47'),
(11, 'V-22000005', 'Rosa Colmenares', 'organizador_05', '123456', 1, 'Activo', '2026-10-25 00:02:47'),
(12, 'V-23000001', 'Soporte Secundario', 'soporte_02', '123456', 4, 'Activo', '2026-10-25 00:02:47');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cargas`
--
ALTER TABLE `cargas`
  ADD PRIMARY KEY (`id_carga`);

--
-- Indices de la tabla `estivas`
--
ALTER TABLE `estivas`
  ADD PRIMARY KEY (`id_estiva`),
  ADD UNIQUE KEY `uq_estivas_fila_numero` (`id_fila`,`numero_estiva`);

--
-- Indices de la tabla `ferreterias`
--
ALTER TABLE `ferreterias`
  ADD PRIMARY KEY (`id_ferreteria`),
  ADD UNIQUE KEY `uq_ferreterias_rif` (`rif`);

--
-- Indices de la tabla `filas`
--
ALTER TABLE `filas`
  ADD PRIMARY KEY (`id_fila`),
  ADD UNIQUE KEY `uq_filas_nombre` (`nombre`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedido`),
  ADD UNIQUE KEY `uq_pedidos_codigo` (`codigo`),
  ADD KEY `idx_pedidos_chequeador_fecha` (`id_chequeador`,`fecha_chequeo`),
  ADD KEY `idx_pedidos_fecha` (`fecha_chequeo`),
  ADD KEY `idx_pedidos_situacion` (`situacion`),
  ADD KEY `fk_pedidos_ferreteria` (`id_ferreteria`),
  ADD KEY `fk_pedidos_ubicador` (`id_ubicador`),
  ADD KEY `fk_pedidos_estiva` (`id_estiva`),
  ADD KEY `idx_pedidos_carga` (`id_carga`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `uq_roles_nombre` (`nombre_rol`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `uq_usuarios_cedula` (`cedula`),
  ADD UNIQUE KEY `uq_usuarios_nombre_usuario` (`nombre_usuario`),
  ADD KEY `fk_usuarios_rol` (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cargas`
--
ALTER TABLE `cargas`
  MODIFY `id_carga` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `estivas`
--
ALTER TABLE `estivas`
  MODIFY `id_estiva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;

--
-- AUTO_INCREMENT de la tabla `ferreterias`
--
ALTER TABLE `ferreterias`
  MODIFY `id_ferreteria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT de la tabla `filas`
--
ALTER TABLE `filas`
  MODIFY `id_fila` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `estivas`
--
ALTER TABLE `estivas`
  ADD CONSTRAINT `fk_estivas_fila` FOREIGN KEY (`id_fila`) REFERENCES `filas` (`id_fila`) ON DELETE CASCADE;

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `fk_pedidos_chequeador` FOREIGN KEY (`id_chequeador`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `fk_pedidos_estiva` FOREIGN KEY (`id_estiva`) REFERENCES `estivas` (`id_estiva`),
  ADD CONSTRAINT `fk_pedidos_ferreteria` FOREIGN KEY (`id_ferreteria`) REFERENCES `ferreterias` (`id_ferreteria`),
  ADD CONSTRAINT `fk_pedidos_ubicador` FOREIGN KEY (`id_ubicador`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_usuarios_rol` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
