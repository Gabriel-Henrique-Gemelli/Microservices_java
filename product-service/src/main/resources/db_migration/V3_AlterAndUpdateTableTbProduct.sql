-- Remova qualquer espaço antes desta linha
ALTER TABLE tb_product
  ADD COLUMN IF NOT EXISTS image_url TEXT;

-- Veja o que vai casar antes de atualizar
SELECT id, model FROM tb_product
WHERE model IN ('iPhone 15 256GB', 'G85 256GB', 'Redmi 13C 256GB', 'S23 Ultra 256GB');

-- Aplique os updates (ajuste os textos do WHERE se necessário)
UPDATE tb_product
SET image_url = 'https://example.com/images/iphone15.jpg'
WHERE model = 'iPhone 15 256GB';

UPDATE tb_product
SET image_url = 'https://example.com/images/motog85.jpg'
WHERE model = 'G85 256GB';

UPDATE tb_product
SET image_url = 'https://example.com/images/redmi13c.jpg'
WHERE model = 'Redmi 13C 256GB';

UPDATE tb_product
SET image_url = 'https://example.com/images/s23ultra.jpg'
WHERE model = 'S23 Ultra 256GB';
