/*
 * QDS - Quick Data Signalling Library
 * Copyright (C) 2002-2016 Devexperts LLC
 *
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
 * If a copy of the MPL was not distributed with this file, You can obtain one at
 * http://mozilla.org/MPL/2.0/.
 */
package com.devexperts.qd.tools;

import java.util.ArrayList;
import java.util.List;

import com.devexperts.logging.Logging;
import com.devexperts.qd.QDLog;
import com.devexperts.qd.qtp.*;
import com.devexperts.services.ServiceProvider;

/**
 * Feed tool.
 */
@ToolSummary(
    info = "Multiplexes a connection using wildcard ticker subscription.",
    argString = "<feed-address> <agent-address>",
    arguments = {
        "<feed-address>  -- uplink address for incoming data only (see @link{address})",
        "<agent-address> -- downlink address for incoming subscription and outgoing data (see @link{address})"
    }
)
@ServiceProvider
public class Feed extends AbstractTool {
    private final OptionLog logfile = OptionLog.getInstance();
    private final OptionCollector collector = new OptionCollector("all");
    private final OptionFile file = new OptionFile();
    private final OptionWrite write = new OptionWrite(file);
    private final Option raw = new Option('R', "raw", "Use raw feed connection (do not subscribe to stream '*').");
    private final Option subscribe = new Option('S', "subscribe", "Subscribe in uplink stream for everything " +
        "subscribed in downlink. Use it on raw port ONLY (subscription will grow indefinitely).");
    private final OptionTimePeriod delay = new OptionTimePeriod('d', "delay", "<n>", "Delay incoming records " +
        "by specified period (in seconds by default).");
    private final OptionDouble buffer = new OptionDouble('b', "buffer", "<n>",
        "Maximum number of records in delay buffer.", 0, 1000000000);
    private final OptionName name = new OptionName("feed");
    private final OptionStat stat = new OptionStat();
    private final OptionManagementHtml html = OptionManagementHtml.getInstance();
    private final OptionManagementRmi rmi = OptionManagementRmi.getInstance();

    private final List<MessageConnector> connectors = new ArrayList<>();

    @Override
    protected Option[] getOptions() {
        return new Option[] { logfile, collector, file, write, raw, subscribe, delay, buffer, name, stat, html, rmi };
    }

    @Override
    protected void executeImpl(String[] args) {
        if (args.length == 0) {
            noArguments();
        }
        if (args.length != 2) {
            wrongNumberOfArguments();
        }

        if (delay.isSet() != buffer.isSet()) {
            throw new BadToolParametersException("Options \"" + delay + "\" and \"" + buffer + "\" shall be used only together");
        }

        Logging log = QDLog.log;

        QDEndpoint endpoint = collector.createEndpoint(name.getName());

        FeedFileHandler feedFileHandler = file.initFile(endpoint, true);
        write.initFileWrite(feedFileHandler);

        FeedDelayer delayer = null;
        if (delay.isSet() && buffer.isSet()) {
            log.info("Creating Delayer for delay " + delay.getValue() + " seconds, buffer size " + buffer.getValue() + " records");
            delayer = new FeedDelayer(delay.getValue().getTime(), (long) buffer.getValue());
        }

        String feed_address = args[0];
        String agent_address = args[1];

        log.info("Using feed address " + MessageConnectors.maskAuthorizationData(feed_address));
        connectors.addAll(
            MessageConnectors.createMessageConnectors(
                new FeedAdapter.Factory(endpoint, null, raw.isSet(), subscribe.isSet(), delayer),
                feed_address, endpoint.getRootStats()));

        log.info("Using agent address " + MessageConnectors.maskAuthorizationData(agent_address));
        connectors.addAll(
            MessageConnectors.createMessageConnectors(
                new AgentAdapter.Factory(endpoint, null),
                agent_address, endpoint.getRootStats()));

        endpoint.addConnectors(connectors).startConnectors();
        if (delayer != null)
            endpoint.registerMonitoringTask(new FeedDelayerMonitoringTask(delayer));
    }

    @Override
    public List<MessageConnector> mustWaitWhileActive() {
        return connectors;
    }

    public static void main(String[] args) {
        Tools.executeSingleTool(Feed.class, args);
    }
}