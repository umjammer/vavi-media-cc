/*
 * Copyright (c) 2003 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.media.ui.cc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ServiceLoader;

import vavi.util.Debug;


/**
 * Closed Caption Loader の Service Provider Interface です．
 * 
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 030228 nsano initial version <br>
 */
public interface ClosedCaptionSpi {

    /** ロードできるかどうか調べます． */
    boolean canReadInput(File file) throws IOException;

    /** リーダのインスタンスを作成します。 */
    ClosedCaptionReader createReaderInstance(File file) throws IOException;

    /** */
    boolean canWriteType(String type);

    /** リーダのインスタンスを作成します。 */
    ClosedCaptionWriter createWriterInstance(File file) throws IOException;

    /** */
    class Factory {

        /** */
        public static ClosedCaptionReader getReader(File file) throws IOException {
            for (ClosedCaptionSpi closedCaptionSpi : closedCaptionSpis) {
Debug.println("closedCaptionSpi: " + closedCaptionSpi);
                if (closedCaptionSpi.canReadInput(file)) {
                    ClosedCaptionReader reader = closedCaptionSpi.createReaderInstance(file);
Debug.println("reader: " + reader.getClass());
                    return reader;
                }
            }

            throw new NoSuchElementException(file + " is not supported type");
        }

        /** */
        public static ClosedCaptionWriter getWriter(File file, String type) throws IOException {
            for (ClosedCaptionSpi closedCaptionSpi : closedCaptionSpis) {
Debug.println("closedCaptionSpi: " + closedCaptionSpi);
                if (closedCaptionSpi.canWriteType(type)) {
                    ClosedCaptionWriter writer = closedCaptionSpi.createWriterInstance(file);
Debug.println("writer: " + writer.getClass());
                    return writer;
                }
            }

            throw new NoSuchElementException(file + " is not supported type");
        }

        /** */
        private static List<ClosedCaptionSpi> closedCaptionSpis = new ArrayList<>();

        /** */
        static {
            try {
                ServiceLoader.load(ClosedCaptionSpi.class).forEach(closedCaptionSpis::add);
closedCaptionSpis.forEach(System.err::println);
            } catch (Exception e) {
Debug.printStackTrace(e);
                throw new IllegalStateException(e);
            }
        }
    }
}

/* */
