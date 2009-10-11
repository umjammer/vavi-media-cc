/*
 * Copyright (c) 2003 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.media.ui.cc;


/**
 * Closed Caption �̊�b���f���N���X�ł��B
 * 
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 030218 nsano initial version <br>
 */
public class ClosedCaption {

    /** Closed Caption �̏����A1 ����n�܂�܂��B */
    private int sequenceNo;
    /** Closed Caption �̃e�L�X�g�A���s�� \n ��ݒ肵�Ă��������B */
    private String text;
    /** Closed Caption �̕\������ */
    private long timeFrom;
    /** Closed Caption �̏��������A���݂��Ȃ��ꍇ�� -1 ��ݒ肵�Ă��������B */
    private long timeTo;

    /** */
    public void setSequenceNo(int sequenceNo) {
        this.sequenceNo = sequenceNo;
    }

    /** */
    public int getSequenceNo() {
        return sequenceNo;
    }

    /** */
    public void setText(String text) {
        this.text = text;
    }

    /** */
    public String getText() {
        return text;
    }
 
    /** */
    public void setTimeFrom(long timeFrom) {
        this.timeFrom = timeFrom;
    }

    /** */
    public long getTimeFrom() {
        return timeFrom;
    }

    /** */
    public void setTimeTo(long timeTo) {
        this.timeTo = timeTo;
    }

    /** */
    public long getTimeTo() {
        return timeTo;
    }
}

/* */
