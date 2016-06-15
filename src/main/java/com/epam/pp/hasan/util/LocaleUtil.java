package com.epam.pp.hasan.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public final class LocaleUtil {
	/**
	 * <p>
	 * Get locale from locale list or returns null if not existed in locale
	 * list.
	 * </p>
	 * 
	 * @param locales
	 *            locale list
	 * @param locale
	 *            locale to find
	 * @return Locale
	 */
	public static Locale getLocale(ArrayList<Locale> locales, Locale locale) {
		Locale ret = null;
		Comparator<Locale> comp = new SearchComparator();
		Collections.sort(locales, comp);
		int index = Collections.binarySearch(locales, locale, comp);
		if (index >= 0) {
			ret = locales.get(index);
		}
		return ret;
	}

	private static class SearchComparator implements Comparator<Locale> {
		@Override
		public int compare(Locale o1, Locale o2) {
			return o1.getLanguage().compareTo(o2.getLanguage());
		}

	}
}