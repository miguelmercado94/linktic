package com.linktic.test.productos_service.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MessageConstants {

    public static final String INVALID_COUNTRY_ALPHA3_CODE = "Código Alpha-3 inválido o no soportado";
    public static final String DIALING_CODE_NOT_FOUND = "No se encontró código de marcación internacional para el país";

    // OTP Messages
    public static final String OTP_NOT_FOUND = "El OTP no fue encontrado.";
    public static final String OTP_EXPIRED = "El OTP ha expirado.";
    public static final String OTP_ALREADY_VERIFIED = "El OTP ya fue verificado.";
    public static final String OTP_ATTEMPTS_EXCEEDED = "Se han superado los intentos máximos de verificación.";
    public static final String OTP_INVALID = "El código OTP ingresado no es válido.";

    // Validation
    public static final String MISSING_CHANNEL = "El canal no puede ser nulo o vacío.";
    public static final String INVALID_CHANNEL = "El canal especificado no es válido.";

    // Country Validation
    public static final String MISSING_COUNTRY_CODE = "El código de país no puede ser nulo o vacío.";
    public static final String INVALID_COUNTRY_CODE = "El código de país proporcionado no es válido (Alpha-3).";

    // Generic
    public static final String INTERNAL_ERROR = "Ha ocurrido un error inesperado.";
    public static final String BAD_REQUEST = "La solicitud es inválida.";

    // Log Labels
    public static final String LOG_VALIDATION_ERROR = "⚠️ Error de validación en";
    public static final String LOG_REQUEST_ERROR = "❌ Error procesando solicitud";

    // OTP - Error Codes (Prefix 1000)
    public static final String ERROR_OTP_SAVE = "1001";
    public static final String ERROR_OTP_GET_BY_ID = "1002";
    public static final String ERROR_OTP_NOT_FOUND = "1003";
    public static final String ERROR_OTP_GET_BY_DEST_CONTEXT = "1004";
    public static final String ERROR_OTP_MAPPING = "1005";
    public static final String ERROR_OTP_UPDATE = "1006";
    public static final String ERROR_OTP_VALIDATION = "1007";
    public static final String ERROR_OTP_UPDATE_NO_ID = "1008";
    public static final String ERROR_OTP_ALREADY_VERIFIED = "1009";

    // OTP - Detailed Error Messages
    public static final String MSG_ERROR_OTP_MAPPING = "Error inesperado al mapear o guardar OTP";
    public static final String MSG_ERROR_OTP_SAVE = "Error al guardar OTP en base de datos";
    public static final String MSG_ERROR_OTP_GET_BY_ID = "Error al obtener OTP por ID";
    public static final String MSG_ERROR_OTP_NOT_FOUND = "OTP no encontrado con ID: ";
    public static final String MSG_ERROR_OTP_GET_BY_DEST_CONTEXT = "Error al obtener OTPs por destino y contexto";
    public static final String MSG_ERROR_OTP_UPDATE = "Error al actualizar OTP";
    public static final String MSG_ERROR_OTP_VALIDATION = "Error al validar OTP";
    public static final String MSG_ERROR_OTP_UPDATE_NO_ID = "El OTP debe tener un ID para ser actualizado";
    public static final String MSG_ERROR_OTP_ALREADY_VERIFIED = "El OTP ya ha sido verificado y no puede modificarse";
}

