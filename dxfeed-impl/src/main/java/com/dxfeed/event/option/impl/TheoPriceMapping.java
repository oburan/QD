/*
 * !++
 * QDS - Quick Data Signalling Library
 * !-
 * Copyright (C) 2002 - 2020 Devexperts LLC
 * !-
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 * !__
 */
package com.dxfeed.event.option.impl;

import com.devexperts.qd.DataRecord;
import com.devexperts.qd.ng.RecordCursor;
import com.devexperts.qd.ng.RecordMapping;
import com.devexperts.qd.util.MappingUtil;
import com.devexperts.util.TimeUtil;

public class TheoPriceMapping extends RecordMapping {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
    private final int iTime;
    private final int iSequence;
    private final int iPrice;
    private final int iUnderlyingPrice;
    private final int iDelta;
    private final int iGamma;
    private final int iDividend;
    private final int iInterest;

    public TheoPriceMapping(DataRecord record) {
        super(record);
        iTime = MappingUtil.findIntField(record, "Theo.Time", true);
        iSequence = MappingUtil.findIntField(record, "Theo.Sequence", false);
        iPrice = findIntField("Theo.Price", true);
        iUnderlyingPrice = findIntField("Theo.UnderlyingPrice", true);
        iDelta = findIntField("Theo.Delta", true);
        iGamma = findIntField("Theo.Gamma", true);
        iDividend = findIntField("Theo.Dividend", false);
        iInterest = findIntField("Theo.Interest", false);
        putNonDefaultPropertyName("Theo.Time", "Time");
        putNonDefaultPropertyName("Theo.Sequence", "Sequence");
        putNonDefaultPropertyName("Theo.Price", "Price");
        putNonDefaultPropertyName("Theo.UnderlyingPrice", "UnderlyingPrice");
        putNonDefaultPropertyName("Theo.Delta", "Delta");
        putNonDefaultPropertyName("Theo.Gamma", "Gamma");
        putNonDefaultPropertyName("Theo.Dividend", "Dividend");
        putNonDefaultPropertyName("Theo.Interest", "Interest");
    }

    @Deprecated
    public long getTheoTimeMillis(RecordCursor cursor) {
        return getInt(cursor, iTime) * 1000L;
    }

    @Deprecated
    public void setTheoTimeMillis(RecordCursor cursor, long theoTime) {
        setInt(cursor, iTime, TimeUtil.getSecondsFromTime(theoTime));
    }

    @Deprecated
    public int getTheoTimeSeconds(RecordCursor cursor) {
        return getInt(cursor, iTime);
    }

    @Deprecated
    public void setTheoTimeSeconds(RecordCursor cursor, int theoTime) {
        setInt(cursor, iTime, theoTime);
    }

    public long getTimeMillis(RecordCursor cursor) {
        return getInt(cursor, iTime) * 1000L;
    }

    public void setTimeMillis(RecordCursor cursor, long time) {
        setInt(cursor, iTime, TimeUtil.getSecondsFromTime(time));
    }

    public int getTimeSeconds(RecordCursor cursor) {
        return getInt(cursor, iTime);
    }

    public void setTimeSeconds(RecordCursor cursor, int time) {
        setInt(cursor, iTime, time);
    }

    @Deprecated
    public int getTheoSequence(RecordCursor cursor) {
        if (iSequence < 0)
            return 0;
        return getInt(cursor, iSequence);
    }

    @Deprecated
    public void setTheoSequence(RecordCursor cursor, int theoSequence) {
        if (iSequence < 0)
            return;
        setInt(cursor, iSequence, theoSequence);
    }

    public int getSequence(RecordCursor cursor) {
        if (iSequence < 0)
            return 0;
        return getInt(cursor, iSequence);
    }

    public void setSequence(RecordCursor cursor, int sequence) {
        if (iSequence < 0)
            return;
        setInt(cursor, iSequence, sequence);
    }

    @Deprecated
    public double getTheoPrice(RecordCursor cursor) {
        return getAsDouble(cursor, iPrice);
    }

    @Deprecated
    public void setTheoPrice(RecordCursor cursor, double theoPrice) {
        setAsDouble(cursor, iPrice, theoPrice);
    }

    @Deprecated
    public int getTheoPriceDecimal(RecordCursor cursor) {
        return getAsTinyDecimal(cursor, iPrice);
    }

    @Deprecated
    public void setTheoPriceDecimal(RecordCursor cursor, int theoPrice) {
        setAsTinyDecimal(cursor, iPrice, theoPrice);
    }

    @Deprecated
    public long getTheoPriceWideDecimal(RecordCursor cursor) {
        return getAsWideDecimal(cursor, iPrice);
    }

    @Deprecated
    public void setTheoPriceWideDecimal(RecordCursor cursor, long theoPrice) {
        setAsWideDecimal(cursor, iPrice, theoPrice);
    }

    public double getPrice(RecordCursor cursor) {
        return getAsDouble(cursor, iPrice);
    }

    public void setPrice(RecordCursor cursor, double price) {
        setAsDouble(cursor, iPrice, price);
    }

    public int getPriceDecimal(RecordCursor cursor) {
        return getAsTinyDecimal(cursor, iPrice);
    }

    public void setPriceDecimal(RecordCursor cursor, int price) {
        setAsTinyDecimal(cursor, iPrice, price);
    }

    public long getPriceWideDecimal(RecordCursor cursor) {
        return getAsWideDecimal(cursor, iPrice);
    }

    public void setPriceWideDecimal(RecordCursor cursor, long price) {
        setAsWideDecimal(cursor, iPrice, price);
    }

    @Deprecated
    public double getTheoUnderlyingPrice(RecordCursor cursor) {
        return getAsDouble(cursor, iUnderlyingPrice);
    }

    @Deprecated
    public void setTheoUnderlyingPrice(RecordCursor cursor, double theoUnderlyingPrice) {
        setAsDouble(cursor, iUnderlyingPrice, theoUnderlyingPrice);
    }

    @Deprecated
    public int getTheoUnderlyingPriceDecimal(RecordCursor cursor) {
        return getAsTinyDecimal(cursor, iUnderlyingPrice);
    }

    @Deprecated
    public void setTheoUnderlyingPriceDecimal(RecordCursor cursor, int theoUnderlyingPrice) {
        setAsTinyDecimal(cursor, iUnderlyingPrice, theoUnderlyingPrice);
    }

    @Deprecated
    public long getTheoUnderlyingPriceWideDecimal(RecordCursor cursor) {
        return getAsWideDecimal(cursor, iUnderlyingPrice);
    }

    @Deprecated
    public void setTheoUnderlyingPriceWideDecimal(RecordCursor cursor, long theoUnderlyingPrice) {
        setAsWideDecimal(cursor, iUnderlyingPrice, theoUnderlyingPrice);
    }

    public double getUnderlyingPrice(RecordCursor cursor) {
        return getAsDouble(cursor, iUnderlyingPrice);
    }

    public void setUnderlyingPrice(RecordCursor cursor, double underlyingPrice) {
        setAsDouble(cursor, iUnderlyingPrice, underlyingPrice);
    }

    public int getUnderlyingPriceDecimal(RecordCursor cursor) {
        return getAsTinyDecimal(cursor, iUnderlyingPrice);
    }

    public void setUnderlyingPriceDecimal(RecordCursor cursor, int underlyingPrice) {
        setAsTinyDecimal(cursor, iUnderlyingPrice, underlyingPrice);
    }

    public long getUnderlyingPriceWideDecimal(RecordCursor cursor) {
        return getAsWideDecimal(cursor, iUnderlyingPrice);
    }

    public void setUnderlyingPriceWideDecimal(RecordCursor cursor, long underlyingPrice) {
        setAsWideDecimal(cursor, iUnderlyingPrice, underlyingPrice);
    }

    @Deprecated
    public double getTheoDelta(RecordCursor cursor) {
        return getAsDouble(cursor, iDelta);
    }

    @Deprecated
    public void setTheoDelta(RecordCursor cursor, double theoDelta) {
        setAsDouble(cursor, iDelta, theoDelta);
    }

    @Deprecated
    public int getTheoDeltaDecimal(RecordCursor cursor) {
        return getAsTinyDecimal(cursor, iDelta);
    }

    @Deprecated
    public void setTheoDeltaDecimal(RecordCursor cursor, int theoDelta) {
        setAsTinyDecimal(cursor, iDelta, theoDelta);
    }

    @Deprecated
    public long getTheoDeltaWideDecimal(RecordCursor cursor) {
        return getAsWideDecimal(cursor, iDelta);
    }

    @Deprecated
    public void setTheoDeltaWideDecimal(RecordCursor cursor, long theoDelta) {
        setAsWideDecimal(cursor, iDelta, theoDelta);
    }

    public double getDelta(RecordCursor cursor) {
        return getAsDouble(cursor, iDelta);
    }

    public void setDelta(RecordCursor cursor, double delta) {
        setAsDouble(cursor, iDelta, delta);
    }

    public int getDeltaDecimal(RecordCursor cursor) {
        return getAsTinyDecimal(cursor, iDelta);
    }

    public void setDeltaDecimal(RecordCursor cursor, int delta) {
        setAsTinyDecimal(cursor, iDelta, delta);
    }

    public long getDeltaWideDecimal(RecordCursor cursor) {
        return getAsWideDecimal(cursor, iDelta);
    }

    public void setDeltaWideDecimal(RecordCursor cursor, long delta) {
        setAsWideDecimal(cursor, iDelta, delta);
    }

    @Deprecated
    public double getTheoGamma(RecordCursor cursor) {
        return getAsDouble(cursor, iGamma);
    }

    @Deprecated
    public void setTheoGamma(RecordCursor cursor, double theoGamma) {
        setAsDouble(cursor, iGamma, theoGamma);
    }

    @Deprecated
    public int getTheoGammaDecimal(RecordCursor cursor) {
        return getAsTinyDecimal(cursor, iGamma);
    }

    @Deprecated
    public void setTheoGammaDecimal(RecordCursor cursor, int theoGamma) {
        setAsTinyDecimal(cursor, iGamma, theoGamma);
    }

    @Deprecated
    public long getTheoGammaWideDecimal(RecordCursor cursor) {
        return getAsWideDecimal(cursor, iGamma);
    }

    @Deprecated
    public void setTheoGammaWideDecimal(RecordCursor cursor, long theoGamma) {
        setAsWideDecimal(cursor, iGamma, theoGamma);
    }

    public double getGamma(RecordCursor cursor) {
        return getAsDouble(cursor, iGamma);
    }

    public void setGamma(RecordCursor cursor, double gamma) {
        setAsDouble(cursor, iGamma, gamma);
    }

    public int getGammaDecimal(RecordCursor cursor) {
        return getAsTinyDecimal(cursor, iGamma);
    }

    public void setGammaDecimal(RecordCursor cursor, int gamma) {
        setAsTinyDecimal(cursor, iGamma, gamma);
    }

    public long getGammaWideDecimal(RecordCursor cursor) {
        return getAsWideDecimal(cursor, iGamma);
    }

    public void setGammaWideDecimal(RecordCursor cursor, long gamma) {
        setAsWideDecimal(cursor, iGamma, gamma);
    }

    @Deprecated
    public double getTheoDividend(RecordCursor cursor) {
        if (iDividend < 0)
            return Double.NaN;
        return getAsDouble(cursor, iDividend);
    }

    @Deprecated
    public void setTheoDividend(RecordCursor cursor, double theoDividend) {
        if (iDividend < 0)
            return;
        setAsDouble(cursor, iDividend, theoDividend);
    }

    @Deprecated
    public int getTheoDividendDecimal(RecordCursor cursor) {
        if (iDividend < 0)
            return 0;
        return getAsTinyDecimal(cursor, iDividend);
    }

    @Deprecated
    public void setTheoDividendDecimal(RecordCursor cursor, int theoDividend) {
        if (iDividend < 0)
            return;
        setAsTinyDecimal(cursor, iDividend, theoDividend);
    }

    @Deprecated
    public long getTheoDividendWideDecimal(RecordCursor cursor) {
        if (iDividend < 0)
            return 0;
        return getAsWideDecimal(cursor, iDividend);
    }

    @Deprecated
    public void setTheoDividendWideDecimal(RecordCursor cursor, long theoDividend) {
        if (iDividend < 0)
            return;
        setAsWideDecimal(cursor, iDividend, theoDividend);
    }

    public double getDividend(RecordCursor cursor) {
        if (iDividend < 0)
            return Double.NaN;
        return getAsDouble(cursor, iDividend);
    }

    public void setDividend(RecordCursor cursor, double dividend) {
        if (iDividend < 0)
            return;
        setAsDouble(cursor, iDividend, dividend);
    }

    public int getDividendDecimal(RecordCursor cursor) {
        if (iDividend < 0)
            return 0;
        return getAsTinyDecimal(cursor, iDividend);
    }

    public void setDividendDecimal(RecordCursor cursor, int dividend) {
        if (iDividend < 0)
            return;
        setAsTinyDecimal(cursor, iDividend, dividend);
    }

    public long getDividendWideDecimal(RecordCursor cursor) {
        if (iDividend < 0)
            return 0;
        return getAsWideDecimal(cursor, iDividend);
    }

    public void setDividendWideDecimal(RecordCursor cursor, long dividend) {
        if (iDividend < 0)
            return;
        setAsWideDecimal(cursor, iDividend, dividend);
    }

    @Deprecated
    public double getTheoInterest(RecordCursor cursor) {
        if (iInterest < 0)
            return Double.NaN;
        return getAsDouble(cursor, iInterest);
    }

    @Deprecated
    public void setTheoInterest(RecordCursor cursor, double theoInterest) {
        if (iInterest < 0)
            return;
        setAsDouble(cursor, iInterest, theoInterest);
    }

    @Deprecated
    public int getTheoInterestDecimal(RecordCursor cursor) {
        if (iInterest < 0)
            return 0;
        return getAsTinyDecimal(cursor, iInterest);
    }

    @Deprecated
    public void setTheoInterestDecimal(RecordCursor cursor, int theoInterest) {
        if (iInterest < 0)
            return;
        setAsTinyDecimal(cursor, iInterest, theoInterest);
    }

    @Deprecated
    public long getTheoInterestWideDecimal(RecordCursor cursor) {
        if (iInterest < 0)
            return 0;
        return getAsWideDecimal(cursor, iInterest);
    }

    @Deprecated
    public void setTheoInterestWideDecimal(RecordCursor cursor, long theoInterest) {
        if (iInterest < 0)
            return;
        setAsWideDecimal(cursor, iInterest, theoInterest);
    }

    public double getInterest(RecordCursor cursor) {
        if (iInterest < 0)
            return Double.NaN;
        return getAsDouble(cursor, iInterest);
    }

    public void setInterest(RecordCursor cursor, double interest) {
        if (iInterest < 0)
            return;
        setAsDouble(cursor, iInterest, interest);
    }

    public int getInterestDecimal(RecordCursor cursor) {
        if (iInterest < 0)
            return 0;
        return getAsTinyDecimal(cursor, iInterest);
    }

    public void setInterestDecimal(RecordCursor cursor, int interest) {
        if (iInterest < 0)
            return;
        setAsTinyDecimal(cursor, iInterest, interest);
    }

    public long getInterestWideDecimal(RecordCursor cursor) {
        if (iInterest < 0)
            return 0;
        return getAsWideDecimal(cursor, iInterest);
    }

    public void setInterestWideDecimal(RecordCursor cursor, long interest) {
        if (iInterest < 0)
            return;
        setAsWideDecimal(cursor, iInterest, interest);
    }
// END: CODE AUTOMATICALLY GENERATED
}
