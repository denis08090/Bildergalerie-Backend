CREATE DATABASE IF NOT EXISTS bildergalerie CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE bildergalerie;

-- 1️⃣ Autoritäten einfügen (Verhindert doppelte Einträge)
INSERT INTO `authority` (id, name) VALUES
                                       ('123e4567-e89b-12d3-a456-426614174000', 'CAN_WATCH_ALBUM'),
                                       ('2f0fc962-9818-414e-9a24-2842e8f7588b', 'CAN_CREATE_ALBUM'),
                                       ('e8ae5316-ad9c-4886-bfe8-e38024ac031c', 'CAN_ADD_PHOTO'),
                                       ('9e809349-04f7-431c-8f16-052c53dd51f0', 'CAN_WATCH_ALL_ALBUMS')
    ON DUPLICATE KEY UPDATE name = VALUES(name);

-- 2️⃣ Rollen einfügen
INSERT INTO `role` (id, name) VALUES
                                  ('111e4567-e89b-12d3-a456-426614174000', 'CLIENT'),
                                  ('222e4567-e89b-12d3-a456-426614174000', 'ADMINISTRATOR')
    ON DUPLICATE KEY UPDATE name = VALUES(name);

-- 3️⃣ Rollen-Berechtigungen zuweisen (Verhindert doppelte Einträge)
INSERT IGNORE INTO `role_authority` (authority_id, role_id) VALUES
    -- CLIENT darf Alben ansehen und erstellen
    ('123e4567-e89b-12d3-a456-426614174000', '111e4567-e89b-12d3-a456-426614174000'), -- CAN_WATCH_ALBUM
    ('2f0fc962-9818-414e-9a24-2842e8f7588b', '111e4567-e89b-12d3-a456-426614174000'), -- CAN_CREATE_ALBUM

    -- ADMINISTRATOR hat alle Rechte
    ('123e4567-e89b-12d3-a456-426614174000', '222e4567-e89b-12d3-a456-426614174000'), -- CAN_WATCH_ALBUM
    ('2f0fc962-9818-414e-9a24-2842e8f7588b', '222e4567-e89b-12d3-a456-426614174000'), -- CAN_CREATE_ALBUM
    ('e8ae5316-ad9c-4886-bfe8-e38024ac031c', '222e4567-e89b-12d3-a456-426614174000'), -- CAN_ADD_PHOTO
    ('9e809349-04f7-431c-8f16-052c53dd51f0', '222e4567-e89b-12d3-a456-426614174000'); -- CAN_WATCH_ALL_ALBUMS
