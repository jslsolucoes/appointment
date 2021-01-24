package com.jslsolucoes.appointment.api;

import java.util.Arrays;
import java.util.List;

public class Lists {

	public static <T> boolean isNotEmpty(List<T> list) {
		return list != null && !list.isEmpty();
	}

	public static <T> boolean isEmpty(List<T> list) {
		return list == null || list.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> of(T... elements) {
		return Arrays.asList(elements);
	}
}
