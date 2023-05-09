package com.rathercruel.translit;

import com.rathercruel.translit.programmes.GUI;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

/**
 *
 * @author rathercruel
 */

public class Main {
    public static void main(String[] args) {
        FlatMacLightLaf.setup();
        new GUI("Ukrajinśka Łatynka").setVisible(true);
    }
}
