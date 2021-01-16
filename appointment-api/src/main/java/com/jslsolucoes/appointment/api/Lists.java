package com.jslsolucoes.appointment.api;

import java.util.List;

public class Lists {

	public static <T> boolean isNotEmpty(List<T> list) {
		return list != null && !list.isEmpty();
	}

	public static <T> boolean isEmpty(List<T> list) {
		return list == null || list.isEmpty();
	}
}
