/*
 * Copyright (c) 2003 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

import java.io.File;
import java.io.IOException;

import vavi.media.ui.cc.ClosedCaption;
import vavi.media.ui.cc.ClosedCaptionReader;
import vavi.media.ui.cc.ClosedCaptionSpi;
import vavi.media.ui.cc.Scheduler;
import vavi.media.ui.cc.V2Scheduler;
import vavi.media.ui.cc.Viewer;
import vavi.util.Debug;
import vavi.util.event.GenericEvent;
import vavi.util.event.GenericListener;


/**
 * �����V�X�e���̃N���X�ł��B
 * �V�X�e���͊ȒP�Ȃ̂ł����A�̂�
 * <ol>
 * <li>�X�N���[����ɓ����Ȕw�i�ŕ������������яオ�点�邱��</li>
 * <li>�����ȃt�@�C���`���ɑΉ��ł��邱��</li>
 * <li>�y������</li>
 * </ol>
 * �ł��B1. �� Java ���Ɠ���ł��Bskinlf �Ńg���C���Ă݂܂��B
 * 2. �� Java �̓��ӂƂ���Ƃ���ł��B���[�_�� SPI �`���ɂ��Ă�����
 * �t�H�[�}�b�g�ɑΉ����Ă݂��܂��B��n�߂͎��ԃ^�O�t���̎��t�@�C�����H
 * 3. �� Java ���Ɠ���ł��ˁB�ǂ����悤�B�ň� C# ���H JDirect ���A�����H
 *
 * @author	<a href="mailto:vavivavi@yahoo.co.jp">Naohide Sano</a> (nsano)
 * @version	0.00 030214 nsano initial version <br>
 *          0.10 030304 nsano �ꉞ���� <br>
 *          0.11 030306 nsano fix when lest timeFrom is -1 <br>
 */
public class CCPlayer {

    /** model */
    private Scheduler scheduler;

    /** view */
    private Viewer viewer;

    /** */
    public CCPlayer() {
    }

    /** */
    private void setModel(ClosedCaption[] ccs) {
        scheduler = new V2Scheduler(ccs);
        scheduler.addGenericListener(listener);
    }

    /** */
    public void setSpeed(int speed) {
        scheduler.setSpeed(speed);
    }

    /** */
    public void start() {
        scheduler.start();
    }

    /** */
    public void setView(Viewer viewer) {
        this.viewer = viewer;
    }

    /** */
    private GenericListener listener = new GenericListener() {
        public void eventHappened(GenericEvent ev) {
            String name = ev.getName();
            if ("show".equals(name)) {
                viewer.showClosedCaption((ClosedCaption) ev.getArguments()[0]);
            } else if ("exit".equals(name)) {
                ClosedCaption cc = (ClosedCaption) ev.getArguments()[0];
                long time = cc.getTimeTo() == -1 ?
                    2000L :
                    cc.getTimeTo() - cc.getTimeFrom();
                try { Thread.sleep(time); } catch (Exception e) {}
                System.exit(0);
            } else {
Debug.println("unknown command: " + name);
            }
        }
    };

    /** */
    private ClosedCaptionReader reader;

    /** */
    public void readClosedCaption(File file) throws IOException {
        reader = ClosedCaptionSpi.Factory.getReader(file);
        setModel(reader.readClosedCaptions());
    }

    //----

    /**
     *
     */
    public static void main(String[] args) throws Exception {

        CCPlayer player = new CCPlayer();

        player.readClosedCaption(new File(args[0]));
        player.setSpeed(Integer.parseInt(args[1]));

        player.setView(new SkinLFViewer());
new HandSynchronizer(player.scheduler);	// TODO

//      player.start();
    }
}

/* */
