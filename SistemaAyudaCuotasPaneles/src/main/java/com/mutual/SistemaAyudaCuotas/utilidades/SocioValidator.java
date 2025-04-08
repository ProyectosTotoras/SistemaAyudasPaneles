package com.mutual.SistemaAyudaCuotas.utilidades;


import java.util.Date;

import com.mutual.SistemaAyudaCuotas.entidades.Socio;

public class SocioValidator {

	 public boolean validateSocio(Socio socio) {
	        // Validar APELLIDO_NOMBRE (APENOM)
	        if (isNullOrEmpty(socio.getApellidoNombre(), 25)) {
	            showMessage("Apellido y Nombre está vacío.");
	            return false;
	        }

	        // Validar DOMICILIO
	        if (isNullOrEmpty(socio.getDomicilio(), 25)) {
	            showMessage("Domicilio está vacío.");
	            return false;
	        }

	        // Validar LOCALIDAD
	        if (isNullOrEmpty(socio.getLocalidad(), 20)) {
	            showMessage("Localidad está vacía.");
	            return false;
	        }

	        // Validar CODIGO_POSTAL (CP)
	        if (socio.getCodigoPostal() == null || socio.getCodigoPostal() == 0) {
	            showMessage("Código Postal es inválido.");
	            return false;
	        }

	        // Validar CATEGORIA
	        if (isNullOrEmpty(socio.getCategoria(), 1)) {
	            showMessage("Categoría está vacía.");
	            return false;
	        }

	        // Validar FECHA_INGRESO (FECH_INGRE)
	        if (isInvalidDate(socio.getFechaIngreso())) {
	            showMessage("Fecha de Ingreso está inválida.");
	            return false;
	        }

	        // Validar CUIT
	        if (isNullOrEmpty(socio.getCuit(), 13)) {
	            showMessage("CUIT está vacío.");
	            return false;
	        }

	        // Validar TELEFONO (TEL)
	        if (isNullOrEmpty(socio.getTelefono(), 25)) {
	            showMessage("Teléfono está vacío.");
	            return false;
	        }

	        // Validar TIPPERS diferentes de "J" o "X"
	        String tipper = socio.getTipper();
	        if (!"J".equalsIgnoreCase(tipper) && !"X".equalsIgnoreCase(tipper)) {
	            // Validar NACIONALIDAD
	            if (isNullOrEmpty(socio.getNacionalidad(), 15)) {
	                showMessage("Nacionalidad está vacía.");
	                return false;
	            }

	            // Validar TRABAJO
	            if (isNullOrEmpty(socio.getTrabajo(), 15)) {
	                showMessage("Trabajo está vacío.");
	                return false;
	            }

	            // Validar TIPO_DOCUMENTO (TIPDOC)
	            if (isNullOrEmpty(socio.getTipoDocumento(), 4)) {
	                showMessage("Tipo de Documento está vacío.");
	                return false;
	            }

	            // Validar NRO_DOCUMENTO (NRODOC)
	            if (socio.getNumeroDocumento() == null || socio.getNumeroDocumento() == 0) {
	                showMessage("Número de Documento es inválido.");
	                return false;
	            }

	            // Validar FECHA_NACIMIENTO (FECH_NAC)
	            if (isInvalidDate(socio.getFechaNacimiento())) {
	                showMessage("Fecha de Nacimiento está inválida.");
	                return false;
	            }

	            // Validar ESTADO_CIVIL (ESTADO_CIV)
	            if (isNullOrEmpty(socio.getEstadoCivil(), 1)) {
	                showMessage("Estado Civil está vacío.");
	                return false;
	            }

	            // Validar PROFESION
	            if (isNullOrEmpty(socio.getProfesion(), 15)) {
	                showMessage("Profesión está vacía.");
	                return false;
	            }

	            // Validar APELLIDO_PADRE (APELLPADRE)
	            if (isNullOrEmpty(socio.getApellidoPadre(), 15)) {
	                showMessage("Apellido del Padre está vacío.");
	                return false;
	            }

	            // Validar NOMBRE_PADRE (NOMBRPADRE)
	            if (isNullOrEmpty(socio.getNombrePadre(), 15)) {
	                showMessage("Nombre del Padre está vacío.");
	                return false;
	            }

	            // Validar VIVE_PADRE (VIVEP)
	            if (isNullOrEmpty(socio.getVivePadre(), 1)) {
	                showMessage("Vive el Padre está vacío.");
	                return false;
	            }

	            // Validar APELLIDO_MADRE (APELLMADRE)
	            if (isNullOrEmpty(socio.getApellidoMadre(), 15)) {
	                showMessage("Apellido de la Madre está vacío.");
	                return false;
	            }

	            // Validar NOMBRE_MADRE (NOMBRMADRE)
	            if (isNullOrEmpty(socio.getNombreMadre(), 15)) {
	                showMessage("Nombre de la Madre está vacío.");
	                return false;
	            }

	            // Validar VIVE_MADRE (VIVEM)
	            if (isNullOrEmpty(socio.getViveMadre(), 1)) {
	                showMessage("Vive la Madre está vacío.");
	                return false;
	            }

	            // Validar APELLIDO_BENEFICIARIO (APELLBENEF)
	            if (isNullOrEmpty(socio.getApellidoBeneficiario(), 15)) {
	                showMessage("Apellido del Beneficiario está vacío.");
	                return false;
	            }

	            // Validar NOMBRE_BENEFICIARIO (NOMBRBENEF)
	            if (isNullOrEmpty(socio.getNombreBeneficiario(), 15)) {
	                showMessage("Nombre del Beneficiario está vacío.");
	                return false;
	            }
	        }

	        // Validar DOMICILIO_CONTRAC (DOMCONTRAC)
	        if (isNullOrEmpty(socio.getDomicilioContractual(), 25)) {
	            showMessage("Domicilio Contractual está vacío.");
	            return false;
	        }

	        // Validar TIPPER
	        if (isNullOrEmpty(socio.getTipper(), 1)) {
	            showMessage("Tipper está vacío.");
	            return false;
	        }

	        // Validar CONJUNTA
	        if (isNullOrEmpty(socio.getConjunta(), 1)) {
	            showMessage("Conjunta está vacío.");
	            return false;
	        }

	        // Validar TIPPER nuevamente (según el código original, aparece dos veces)
	        if (isNullOrEmpty(socio.getTipper(), 1)) {
	            showMessage("Tipper está vacío.");
	            return false;
	        }

	        // Si todas las validaciones pasan
//	        showMessage("Validación exitosa.");
	        return true;
	    }

	    /**
	     * Verifica si el valor proporcionado es nulo o está vacío después de eliminar espacios en blanco.
	     *
	     * @param value  La cadena a verificar.
	     * @param length Longitud esperada para la validación específica (puede ser utilizada para mensajes o futuras validaciones).
	     * @return True si la cadena es nula o está vacía tras recortar espacios, false de lo contrario.
	     */
	    private boolean isNullOrEmpty(String value, int length) {
	        return value == null || value.trim().isEmpty();
	    }

	    /**
	     * Verifica si la fecha proporcionada es inválida (nula).
	     *
	     * @param date La fecha a verificar.
	     * @return True si la fecha es nula, false de lo contrario.
	     */
	    private boolean isInvalidDate(Date date) {
	        return date == null;
	    }

	    /**
	     * Muestra un mensaje al usuario.
	     * Personaliza este método según cómo desees manejar los mensajes de validación.
	     *
	     * @param message El mensaje a mostrar.
	     */
	    private void showMessage(String message) {
	        // Ejemplo: Imprimir en la consola
	        System.out.println(message);

	        // Ejemplo: Lanzar una excepción
	        // throw new ValidationException(message);

	        // Ejemplo: Usar un logger
	        // Logger.getLogger(SocioValidator.class.getName()).log(Level.SEVERE, message);

	        // Personaliza según sea necesario
	    }
}

