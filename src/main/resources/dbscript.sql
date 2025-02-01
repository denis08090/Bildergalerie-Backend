CREATE DATABASE IF NOT EXISTS bildergalerie CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE bildergalerie;

INSERT INTO authority (id, name)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'CAN_PLACE_ORDER'),
    ('2f0fc962-9818-414e-9a24-2842e8f7588b', 'PURCHASE_HISTORY'),
    ('e8ae5316-ad9c-4886-bfe8-e38024ac031c', 'CAN_RETRIEVE_PRODUCT');

INSERT INTO "role" (id, name)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', 'CLIENT');

INSERT INTO role_authority (authority_id, role_id)
VALUES
    ('123e4567-e89b-12d3-a456-426614174000', '123e4567-e89b-12d3-a456-426614174000'),
    ('2f0fc962-9818-414e-9a24-2842e8f7588b', '123e4567-e89b-12d3-a456-426614174000'),
    ('e8ae5316-ad9c-4886-bfe8-e38024ac031c', '123e4567-e89b-12d3-a456-426614174000');