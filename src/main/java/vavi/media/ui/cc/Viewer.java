/*
 * Copyright (c) 2003 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.media.ui.cc;


/**
 * Viewer.
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 030218 nsano initial version <br>
 */
public interface Viewer {
    void showClosedCaption(ClosedCaption cc);
    
    class Factory {
        public static Viewer getViewer() {
            try {
                String className = System.getProperty("vavi.media.ui.cc.viewClass");
                @SuppressWarnings("unchecked")
                Class<Viewer> clazz = (Class<Viewer>) Class.forName(className);
                return clazz.newInstance();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}

/* */
