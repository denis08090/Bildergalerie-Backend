package com.example.Bildergalerie.generic;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Konverterklasse für die Umwandlung von UUIDs in ein Byte-Array und umgekehrt.
 * Diese Klasse wird von JPA verwendet, um UUIDs als Binärdaten in der Datenbank zu speichern.
 */
@Converter(autoApply = true)
public class UUIDBinaryConverter implements AttributeConverter<UUID, byte[]> {

    /**
     * Konvertiert eine UUID in ein Byte-Array für die Speicherung in der Datenbank.
     *
     * @param uuid Die zu konvertierende UUID.
     * @return Ein Byte-Array, das die UUID repräsentiert, oder null, falls die UUID null ist.
     */
    @Override
    public byte[] convertToDatabaseColumn(UUID uuid) {
        if (uuid == null) {
            return null;
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[16]);
        byteBuffer.putLong(uuid.getMostSignificantBits()); // Setzt die höchstsignifikanten Bits der UUID
        byteBuffer.putLong(uuid.getLeastSignificantBits()); // Setzt die niedrigstsignifikanten Bits der UUID
        return byteBuffer.array();
    }

    /**
     * Konvertiert ein Byte-Array aus der Datenbank zurück in eine UUID.
     *
     * @param bytes Das Byte-Array, das die UUID-Daten enthält.
     * @return Die rekonstruierte UUID oder null, falls das Byte-Array ungültig ist.
     */
    @Override
    public UUID convertToEntityAttribute(byte[] bytes) {
        if (bytes == null || bytes.length != 16) {
            return null;
        }
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long mostSigBits = byteBuffer.getLong(); // Extrahiert die höchstsignifikanten Bits
        long leastSigBits = byteBuffer.getLong(); // Extrahiert die niedrigstsignifikanten Bits
        return new UUID(mostSigBits, leastSigBits);
    }
}
