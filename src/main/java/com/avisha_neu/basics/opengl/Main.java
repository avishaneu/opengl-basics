package com.avisha_neu.basics.opengl;

import com.avisha_neu.basics.opengl.core.LWJGL;
import org.apache.log4j.Logger;

/**
 * Created by avisha.neu on 25.01.2017.
 */
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            new LWJGL().run();
        } catch (RuntimeException e) {
            log.error(e);
        }
    }
}
