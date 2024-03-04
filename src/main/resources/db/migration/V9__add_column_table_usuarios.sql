-- Adiciona coluna 'ativo'
ALTER TABLE usuarios
ADD COLUMN ativo TINYINT(1) NOT NULL DEFAULT 1;

-- Adiciona coluna 'expirado'
ALTER TABLE usuarios
ADD COLUMN expirado TINYINT(1) NOT NULL DEFAULT 0;

-- Adiciona coluna 'bloqueado'
ALTER TABLE usuarios
ADD COLUMN bloqueado TINYINT(1) NOT NULL DEFAULT 0;

-- Adiciona coluna 'credencial_expirada'
ALTER TABLE usuarios
ADD COLUMN credencial_expirada TINYINT(1) NOT NULL DEFAULT 0;