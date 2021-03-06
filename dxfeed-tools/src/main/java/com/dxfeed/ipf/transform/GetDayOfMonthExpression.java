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
package com.dxfeed.ipf.transform;

import com.dxfeed.schedule.Schedule;

import java.io.IOException;

class GetDayOfMonthExpression extends Expression<Double> {
    private final Object parameter;

    GetDayOfMonthExpression(Compiler compiler) throws IOException {
        super(Double.class);
        compiler.skipToken('(');
        parameter = compiler.readExpression();
        compiler.skipToken(')');
        Compiler.getDate(Compiler.newTestContext(), parameter); // Early check of expression constraints (data types)
    }

    @Override
    Double evaluate(TransformContext ctx) {
        return Compiler.getDouble(Schedule.getInstance(ctx.currentProfile().getTradingHours()).getDayById(Compiler.getDayId(Compiler.getDate(ctx, parameter))).getDayOfMonth());
    }
}
