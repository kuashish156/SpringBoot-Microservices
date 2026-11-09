package com.ashish.userservice.encrypt;

import java.util.regex.Pattern;

import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class ProtectDataSerilizer extends ValueSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator gen, SerializationContext context) {

		if (value == null) {
			gen.writeNull();
			return;
		}

		String pii = value.toString();

		// Mobile masking: -> 98******10
		if (pii.matches("\\d{10}")) {
			pii = pii.replaceAll("(?<=\\d{2})\\d(?=\\d{2})", "*");
		}

		// Email masking: -> as****@gmail.com
		else if (pii.matches("^[^@\\s]+@[^@\\s]+$")) {

			String[] parts = pii.split("@", 2);
			String localPart = parts[0];

			if (localPart.length() > 2) {
				localPart = localPart.substring(0, 2) + "*".repeat(localPart.length() - 2);
			}

			pii = localPart + "@" + parts[1];
		}

		gen.writeString(pii);
	}
}