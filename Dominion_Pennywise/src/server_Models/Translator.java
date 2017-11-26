package server_Models;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import main_Class.ServiceLocator;


public class Translator {
    private ServiceLocator sl = ServiceLocator.getServiceLocator();
    
    protected Locale currentLocale;
	private ResourceBundle resourceBundle;

	public Translator(String localeString) {
		Locale locale = Locale.getDefault();
		if(localeString != null) {
			Locale[] availableLocales = sl.getLocales();
			for (int i = 0; i < availableLocales.length; i++) {
				String tmpLang = availableLocales[i].getLanguage();
				if (localeString.subSequence(0, tmpLang.length()).equals(tmpLang)) {
					locale = availableLocales[i];
					break;
				}
			}
		}
        
		resourceBundle = ResourceBundle.getBundle(sl.getDOMINION_CLASS().getName()
				/*"main_Class.Main"*/, locale);
		Locale.setDefault(locale);
		currentLocale = locale;
	}

    public Locale getCurrentLocale() {
        return currentLocale;
    }

	public String getString(String key) {
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return "--";
		}
	}

}
