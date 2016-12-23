/*
 * QDS - Quick Data Signalling Library
 * Copyright (C) 2002-2016 Devexperts LLC
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 */
package com.dxfeed.event.market.impl;

import com.devexperts.qd.DataRecord;
import com.devexperts.qd.ng.RecordCursor;
import com.devexperts.qd.util.*;
import com.devexperts.util.TimeUtil;

public class OrderMapping extends OrderBaseMapping {
// BEGIN: CODE AUTOMATICALLY GENERATED: DO NOT MODIFY. IT IS REGENERATED BY com.dxfeed.api.codegen.ImplCodeGen
    private final int iIndex;
    private final int iTime;
    private final int iTimeNanoPart;
    private final int iSequence;
    private final int iPrice;
    private final int iSize;
    private final int iCount;
    private final int iFlags;
    private final int iMarketMaker;

    public OrderMapping(DataRecord record) {
        super(record);
        iIndex = MappingUtil.findIntField(record, "Index", true);
        iTime = MappingUtil.findIntField(record, "Time", true);
        iTimeNanoPart = MappingUtil.findIntField(record, "TimeNanoPart", false);
        iSequence = MappingUtil.findIntField(record, "Sequence", true);
        iPrice = MappingUtil.findIntField(record, "Price", true);
        iSize = MappingUtil.findIntField(record, "Size", true);
        iCount = MappingUtil.findIntField(record, "Count", false);
        iFlags = MappingUtil.findIntField(record, "Flags", true);
        iMarketMaker = MappingUtil.findIntField(record, "MMID", false);
        putNonDefaultPropertyName("MMID", "MarketMaker");
    }

    public int getIndex(RecordCursor cursor) {
        return getInt(cursor, iIndex);
    }

    public void setIndex(RecordCursor cursor, int index) {
        setInt(cursor, iIndex, index);
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

    public int getTimeNanoPart(RecordCursor cursor) {
        if (iTimeNanoPart < 0)
            return 0;
        return getInt(cursor, iTimeNanoPart);
    }

    public void setTimeNanoPart(RecordCursor cursor, int timeNanoPart) {
        if (iTimeNanoPart < 0)
            return;
        setInt(cursor, iTimeNanoPart, timeNanoPart);
    }

    public int getSequence(RecordCursor cursor) {
        return getInt(cursor, iSequence);
    }

    public void setSequence(RecordCursor cursor, int sequence) {
        setInt(cursor, iSequence, sequence);
    }

    public double getPrice(RecordCursor cursor) {
        return Decimal.toDouble(getInt(cursor, iPrice));
    }

    public void setPrice(RecordCursor cursor, double price) {
        setInt(cursor, iPrice, Decimal.compose(price));
    }

    public int getPriceDecimal(RecordCursor cursor) {
        return getInt(cursor, iPrice);
    }

    public void setPriceDecimal(RecordCursor cursor, int price) {
        setInt(cursor, iPrice, price);
    }

    public int getSize(RecordCursor cursor) {
        return getInt(cursor, iSize);
    }

    public void setSize(RecordCursor cursor, int size) {
        setInt(cursor, iSize, size);
    }

    public int getCount(RecordCursor cursor) {
        if (iCount < 0)
            return 0;
        return getInt(cursor, iCount);
    }

    public void setCount(RecordCursor cursor, int count) {
        if (iCount < 0)
            return;
        setInt(cursor, iCount, count);
    }

    public int getFlags(RecordCursor cursor) {
        return getInt(cursor, iFlags);
    }

    public void setFlags(RecordCursor cursor, int flags) {
        setInt(cursor, iFlags, flags);
    }

    @Deprecated
    public String getMMIDString(RecordCursor cursor) {
        if (iMarketMaker < 0)
            return null;
        return ShortString.decode(getInt(cursor, iMarketMaker));
    }

    @Deprecated
    public void setMMIDString(RecordCursor cursor, String _MMID) {
        if (iMarketMaker < 0)
            return;
        setInt(cursor, iMarketMaker, (int) ShortString.encode(_MMID));
    }

    @Deprecated
    public int getMMID(RecordCursor cursor) {
        if (iMarketMaker < 0)
            return 0;
        return getInt(cursor, iMarketMaker);
    }

    @Deprecated
    public void setMMID(RecordCursor cursor, int _MMID) {
        if (iMarketMaker < 0)
            return;
        setInt(cursor, iMarketMaker, _MMID);
    }

    public String getMarketMakerString(RecordCursor cursor) {
        if (iMarketMaker < 0)
            return null;
        return ShortString.decode(getInt(cursor, iMarketMaker));
    }

    public void setMarketMakerString(RecordCursor cursor, String marketMaker) {
        if (iMarketMaker < 0)
            return;
        setInt(cursor, iMarketMaker, (int) ShortString.encode(marketMaker));
    }

    public int getMarketMaker(RecordCursor cursor) {
        if (iMarketMaker < 0)
            return 0;
        return getInt(cursor, iMarketMaker);
    }

    public void setMarketMaker(RecordCursor cursor, int marketMaker) {
        if (iMarketMaker < 0)
            return;
        setInt(cursor, iMarketMaker, marketMaker);
    }
// END: CODE AUTOMATICALLY GENERATED
}