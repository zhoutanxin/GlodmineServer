package com.doadway.framework.util;

import java.util.UUID;

public class UuidGenerator {
	
	public static String generatorUuid(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		return uuid;
	}
}
