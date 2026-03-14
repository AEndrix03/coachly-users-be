package it.aredegalli.coachly.user.persistence;

import it.aredegalli.coachly.user.enums.AuthProvider;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Locale;

@Converter(autoApply = true)
public class AuthProviderConverter implements AttributeConverter<AuthProvider, String> {

    @Override
    public String convertToDatabaseColumn(AuthProvider attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public AuthProvider convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isBlank()) {
            return null;
        }

        String normalized = dbData.trim().toUpperCase(Locale.ROOT);
        if ("KEYCLOAK".equals(normalized)) {
            return AuthProvider.KEYCLOAK;
        }

        throw new IllegalArgumentException("Unsupported auth provider: " + dbData);
    }
}
