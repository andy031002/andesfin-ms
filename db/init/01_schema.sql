CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuarios (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  nombre VARCHAR(120) NOT NULL,
  email VARCHAR(160) NOT NULL UNIQUE,
  capital_disponible DECIMAL(10,2) NOT NULL DEFAULT 0
);

CREATE TABLE productos_financieros (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  nombre VARCHAR(120) NOT NULL,
  descripcion TEXT,
  costo DECIMAL(10,2) NOT NULL,
  porcentaje_retorno DECIMAL(5,2) NOT NULL,
  activo BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE simulaciones (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  usuario_id UUID NOT NULL REFERENCES usuarios(id),
  fecha_simulacion TIMESTAMP NOT NULL DEFAULT NOW(),
  capital_disponible DECIMAL(10,2) NOT NULL,
  costo_total DECIMAL(10,2) NOT NULL DEFAULT 0,
  capital_restante DECIMAL(10,2) NOT NULL DEFAULT 0,
  ganancia_total DECIMAL(10,2) NOT NULL DEFAULT 0,
  retorno_total_porcentaje DECIMAL(10,2) NOT NULL DEFAULT 0
);

CREATE TABLE simulacion_detalle (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  simulacion_id UUID NOT NULL REFERENCES simulaciones(id) ON DELETE CASCADE,
  nombre_producto VARCHAR(120) NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  riesgo DECIMAL(5,2) NOT NULL DEFAULT 0,
  porcentaje_ganancia DECIMAL(5,2) NOT NULL,
  ganancia_esperada DECIMAL(10,2) NOT NULL,
  retorno_porcentaje DECIMAL(10,2) NOT NULL
);

CREATE INDEX idx_sim_usuario ON simulaciones(usuario_id);
CREATE INDEX idx_det_sim ON simulacion_detalle(simulacion_id);
