/*
 * QDS - Quick Data Signalling Library
 * Copyright (C) 2002-2016 Devexperts LLC
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 */
package com.devexperts.qd.tools;

public interface TimeSyncTrackerMBean {
    public void start();

    public void stop();

    public boolean isActive();

    public int getPeerCount();

    public String[] getPeerIds();

    public long[] getPeerDeltas();

    public long[] getPeerDeviations();

    public long getMedianDelta();

    public void sendRequest(boolean verbose);

    public void dumpPeers(boolean verbose);
}