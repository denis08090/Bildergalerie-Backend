-- authority Einträge einfügen, falls sie noch nicht existieren
INSERT IGNORE INTO `authority` (`id`, `name`)
VALUES
    (UNHEX('123e4567e89b12d3a456426614174000'), 'CAN_WATCH_ALBUM'),
    (UNHEX('2f0fc9629818414e9a242842e8f7588b'), 'CAN_CREATE_ALBUM'),
    (UNHEX('e8ae5316ad9c4886bfe8e38024ac031c'), 'CAN_ADD_PHOTO'),
    (UNHEX('9e80934904f7431c8f16052c53dd51f0'), 'CAN_WATCH_ALL_ALBUMS');

-- role Einträge einfügen, falls sie noch nicht existieren
INSERT IGNORE INTO `role` (`id`, `name`)
VALUES
    (UNHEX('123e4567e89b12d3a456426614174000'), 'CLIENT'),
    (UNHEX('2f0fc9629818414e9a242842e8f7588b'), 'ADMINISTRATOR');

-- role_authority Einträge einfügen, falls sie noch nicht existieren
INSERT IGNORE INTO `role_authority` (`authority_id`, `role_id`)
VALUES
    (UNHEX('123e4567e89b12d3a456426614174000'), UNHEX('123e4567e89b12d3a456426614174000')),
    (UNHEX('2f0fc9629818414e9a242842e8f7588b'), UNHEX('123e4567e89b12d3a456426614174000')),
    (UNHEX('e8ae5316ad9c4886bfe8e38024ac031c'), UNHEX('123e4567e89b12d3a456426614174000')),
    (UNHEX('123e4567e89b12d3a456426614174000'), UNHEX('2f0fc9629818414e9a242842e8f7588b')),
    (UNHEX('2f0fc9629818414e9a242842e8f7588b'), UNHEX('2f0fc9629818414e9a242842e8f7588b')),
    (UNHEX('e8ae5316ad9c4886bfe8e38024ac031c'), UNHEX('2f0fc9629818414e9a242842e8f7588b')),
    (UNHEX('9e80934904f7431c8f16052c53dd51f0'), UNHEX('2f0fc9629818414e9a242842e8f7588b'));
