package org.dl.app.localisation;

import java.util.ResourceBundle;
import java.util.Locale;

public class Localisation {
    private static ResourceBundle resourceBundle;
    static  {
        resourceBundle = ResourceBundle.getBundle("lang", Locale.getDefault());
    }
    public static String getString(String key) {
        return resourceBundle.getString(key);
    }

    public static void changeLocalisation(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("lang", locale);
    }
}
