/*
 * Copyright (c) 2003 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

package vavi.media.ui.cc;

import vavi.util.Debug;
import vavi.util.event.GenericEvent;
import vavi.util.event.GenericListener;
import vavi.util.event.GenericSupport;


/**
 * ���f���̎��Ԃɏ]���ăC�x���g�𔭐�������X�P�W���[���ł��B
 *
 * �d�l�Ƃ��Đݒ肳�ꂽ EditorListener �ɑ΂���
 * <ol>
 * <li> ClosedCaption#start ���s����������
 * �J�����g�� ClosedCaption#getTimeFrom �̒l�𑫂��������ɁA
 * fireEditorUpdated �𖼑O "show" ���� �J�����g�� ClosedCaption
 * �Ŕ��s���Ă��������B</li>
 * <li> ClosedCaption �����ׂďo�͂��I�����ۂɁA
 * fireEditorUpdated �𖼑O "exit" ���� �Ō�� ClosedCaption
 * �Ŕ��s���Ă��������B</li>
 * </ol>
 *
 * @author <a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version 0.00 030304 nsano initial version <br>
 *          0.01 030306 nsano �d�l���� <br>
 */
public abstract class Scheduler {

    /** model */
    protected ClosedCaption[] ccs;

    /** */
    public Scheduler(ClosedCaption[] ccs) {
        this.ccs = ccs;
    }

    /** */
    public abstract void start();

    /** */
    public abstract void pause();

    /** */
    public abstract void restart();

    /** */
    public abstract void moveTo(long time);

    /** �Đ�����{�� */
    protected int ff = 10;

    /** �Đ�����{����ݒ肵�܂��B��Ƀf�o�b�O�p�ł��B */
    public void setSpeed(int ff) {
        this.ff = ff;
Debug.println("speed: x " + ff);
    }

    //----

    /** */
    private GenericSupport gs = new GenericSupport();

    /** */
    public void addGenericListener(GenericListener listener) {
        gs.addGenericListener(listener);
    }

    /** */
    public void removeGenericListener(GenericListener listener) {
        gs.removeGenericListener(listener);
    }

    /** */
    protected void fireEventHappened(GenericEvent event) {
        gs.fireEventHappened(event);
    }
}

/* */
