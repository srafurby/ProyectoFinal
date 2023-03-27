-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-03-2023 a las 00:43:25
-- Versión del servidor: 10.4.22-MariaDB
-- Versión de PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_final`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estacion`
--

CREATE TABLE `estacion` (
  `ID_estacion` bigint(20) UNSIGNED NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `estacion`
--

INSERT INTO `estacion` (`ID_estacion`, `nombre`) VALUES
(1, 'Granada Center'),
(2, 'Albolote'),
(3, 'Loja'),
(4, 'Lecrín'),
(5, 'Salobreña'),
(6, 'Alhama de Granada'),
(7, 'Guadix'),
(9, 'Cuenquilla');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `id_horario` bigint(20) UNSIGNED NOT NULL,
  `salida` decimal(4,2) NOT NULL,
  `llegada` decimal(4,2) NOT NULL,
  `tren` bigint(20) UNSIGNED NOT NULL,
  `id_estacion_salida` bigint(20) UNSIGNED NOT NULL,
  `id_estacion_llegada` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`id_horario`, `salida`, `llegada`, `tren`, `id_estacion_salida`, `id_estacion_llegada`) VALUES
(1, '20.00', '21.00', 1, 1, 2),
(3, '21.00', '22.00', 2, 1, 3),
(4, '21.00', '22.00', 3, 1, 2),
(5, '17.00', '18.00', 16, 1, 4),
(6, '17.00', '19.00', 3, 2, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pasajeros`
--

CREATE TABLE `pasajeros` (
  `dni` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `f_nacimiento` date NOT NULL,
  `admin` int(1) NOT NULL,
  `pass` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `pasajeros`
--

INSERT INTO `pasajeros` (`dni`, `nombre`, `apellidos`, `f_nacimiento`, `admin`, `pass`, `email`) VALUES
('00000000A', 'Admin', 'Admin', '2000-01-01', 1, 'fd33fd0ddd42912e650d73a0451ba04e', 'admin@railwaygranada.es'),
('55222826P', 'maria', 'avellanedas', '2000-07-06', 0, '7d2a5e38a619d336d6efd6c5a41c1883', 'mariaavellanedas@gmail.com'),
('75938441P', 'jose', 'manuel', '1999-09-21', 0, '5364a5347a086c393cf801af3600910b', 'paco@correo.es'),
('77552128S', 'Jose', 'Ramos', '1999-02-09', 0, 'fd33fd0ddd42912e650d73a0451ba04e', 'jose@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE `ticket` (
  `id_ticket` bigint(20) UNSIGNED NOT NULL,
  `pasajero` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `id_tren` bigint(20) UNSIGNED NOT NULL,
  `id_horario` bigint(20) UNSIGNED NOT NULL,
  `precio` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `ticket`
--

INSERT INTO `ticket` (`id_ticket`, `pasajero`, `id_tren`, `id_horario`, `precio`) VALUES
(1, '77552128S', 1, 1, 14),
(2, '77552128S', 2, 3, 21),
(7, '77552128S', 16, 5, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tren`
--

CREATE TABLE `tren` (
  `numero_tren` bigint(20) UNSIGNED NOT NULL,
  `asientos` int(3) NOT NULL,
  `estacion` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `tren`
--

INSERT INTO `tren` (`numero_tren`, `asientos`, `estacion`) VALUES
(1, 50, 1),
(2, 55, 1),
(3, 60, 1),
(16, 2, 1),
(18, 8, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `estacion`
--
ALTER TABLE `estacion`
  ADD PRIMARY KEY (`ID_estacion`),
  ADD UNIQUE KEY `ID_estacion` (`ID_estacion`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id_horario`),
  ADD UNIQUE KEY `id_horario` (`id_horario`),
  ADD KEY `h_l_id` (`id_estacion_llegada`),
  ADD KEY `h_s_id` (`id_estacion_salida`),
  ADD KEY `h_t_id` (`tren`);

--
-- Indices de la tabla `pasajeros`
--
ALTER TABLE `pasajeros`
  ADD PRIMARY KEY (`dni`);

--
-- Indices de la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id_ticket`),
  ADD UNIQUE KEY `id_ticket` (`id_ticket`),
  ADD KEY `id_ticket_tren` (`id_tren`),
  ADD KEY `id_ticket_horario` (`id_horario`),
  ADD KEY `dni_ticket_pasajero` (`pasajero`);

--
-- Indices de la tabla `tren`
--
ALTER TABLE `tren`
  ADD PRIMARY KEY (`numero_tren`),
  ADD UNIQUE KEY `numero_tren` (`numero_tren`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estacion`
--
ALTER TABLE `estacion`
  MODIFY `ID_estacion` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `id_horario` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id_ticket` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `tren`
--
ALTER TABLE `tren`
  MODIFY `numero_tren` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `h_l_id` FOREIGN KEY (`id_estacion_llegada`) REFERENCES `estacion` (`ID_estacion`),
  ADD CONSTRAINT `h_s_id` FOREIGN KEY (`id_estacion_salida`) REFERENCES `estacion` (`ID_estacion`),
  ADD CONSTRAINT `h_t_id` FOREIGN KEY (`tren`) REFERENCES `tren` (`numero_tren`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `dni_ticket_pasajero` FOREIGN KEY (`pasajero`) REFERENCES `pasajeros` (`dni`) ON DELETE CASCADE,
  ADD CONSTRAINT `id_ticket_horario` FOREIGN KEY (`id_horario`) REFERENCES `horario` (`id_horario`),
  ADD CONSTRAINT `id_ticket_tren` FOREIGN KEY (`id_tren`) REFERENCES `tren` (`numero_tren`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
