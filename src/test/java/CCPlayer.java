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
import vavi.util.event.GenericListener;


/**
 * Closed Caption.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 030214 nsano initial version <br>
 *          0.10 030304 nsano complete <br>
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
    private GenericListener listener = ev -> {
        String name = ev.getName();
        if ("show".equals(name)) {
            doShow((ClosedCaption) ev.getArguments()[0]);
        } else if ("exit".equals(name)) {
            doExit((ClosedCaption) ev.getArguments()[0]);
        } else {
Debug.println("unknown command: " + name);
        }
    };

    /** */
    private void doShow(ClosedCaption cc) {
        viewer.showClosedCaption(cc);
    }

    /** */
    private void doExit(ClosedCaption cc) {
        long time = cc.getTimeTo() == -1 ?
            2000L :
            cc.getTimeTo() - cc.getTimeFrom();
        try { Thread.sleep(time); } catch (Exception ignored) {}
        System.exit(0);
    }

    /** */
    private ClosedCaptionReader reader;

    /** */
    public void readClosedCaption(File file) throws IOException {
        reader = ClosedCaptionSpi.Factory.getReader(file);
        setModel(reader.readClosedCaptions());
    }

    /**
     * @param args 0: cc, 1: start
     */
    public static void main(String[] args) throws Exception {

        CCPlayer player = new CCPlayer();

        player.readClosedCaption(new File(args[0]));
        player.setSpeed(Integer.parseInt(args[1]));

        player.setView(Viewer.Factory.getViewer());
new HandSynchronizer(player.scheduler);    // TODO

//      player.start();
    }
}

/* */
