package server_Models;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import main_Class.ServiceLocator;

/**
 * * Copyright 2015, FHNW, Prof. Dr. Brad Richards. All rights reserved. This
 * code is licensed under the terms of the BSD 3-clause license (see the file
 * license.txt).
 * 
 * Translator will check for supported language in locales and can load the resource strings
 * 
 * @author Brad Richards
 */
public class Translator {
	private ServiceLocator sl = ServiceLocator.getServiceLocator();

	protected Locale currentLocale;
	private ResourceBundle resourceBundle;

    // Can we find the language in our supported locales?
    // If not, use VM default locale
	public Translator(String localeString) {
		Locale locale = Locale.getDefault();
		if (localeString != null) {
			Locale[] availableLocales = sl.getLocales();
			for (int i = 0; i < availableLocales.length; i++) {
				String tmpLang = availableLocales[i].getLanguage();
				if (localeString.subSequence(0, tmpLang.length()).equals(tmpLang)) {
					locale = availableLocales[i];
					break;
				}
			}
		}
		// Load the resource strings
		resourceBundle = ResourceBundle.getBundle(sl.getDOMINION_CLASS().getName(), locale);
		Locale.setDefault(locale);
		currentLocale = locale;
	}

	/**
	 * Return the current locale; this is useful for formatters, etc.
	 */
	public Locale getCurrentLocale() {
		return currentLocale;
	}

	/**
	 * Public method to get string resources, default to "--" *
	 */
	public String getString(String key) {
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return "--";
		}
	}

}
