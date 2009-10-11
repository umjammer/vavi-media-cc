/*
 * Copyright (c) 2003 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.media.ui.cc;

import java.io.File;
import java.io.IOException;


/**
 * VobSub (.idx ? + .sub) Service Provider �ł��D
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 030228 nsano initial version <br>
 */
public class SUBSpi implements ClosedCaptionSpi {

    /** */
    public static final String TYPE = "SUB";

    /** ���[�h�ł��邩�ǂ������ׂ܂��D */
    public boolean canReadInput(File file) throws IOException {
        return file.getName().endsWith(".sub");
    }

    /** ���[�_�̃C���X�^���X���쐬���܂��B */
    public ClosedCaptionReader createReaderInstance(File file) throws IOException {
        return new SUBReader(file);
    }

    /* */
    public boolean canWriteType(String type) {
        return TYPE.equalsIgnoreCase(type);
    }

    /* */
    public ClosedCaptionWriter createWriterInstance(File file) throws IOException {
        throw new UnsupportedOperationException("not implemented yet.");
    }
}

/* */
