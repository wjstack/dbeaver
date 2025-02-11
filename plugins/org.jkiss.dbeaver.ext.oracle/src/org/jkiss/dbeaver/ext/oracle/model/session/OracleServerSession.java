/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ext.oracle.model.session;

import org.jkiss.dbeaver.ext.oracle.model.OracleConstants;
import org.jkiss.dbeaver.model.admin.sessions.AbstractServerSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCUtils;
import org.jkiss.dbeaver.model.meta.Property;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Session
 */
public class OracleServerSession extends AbstractServerSession {

    public static final String CAT_SESSION = "Session";
    public static final String CAT_SQL = "SQL";
    public static final String CAT_PROCESS = "Process";
    public static final String CAT_IO = "IO";
    public static final String CAT_WAIT = "Wait";

    private long instId;
    private final long sid;
    private long serial;
    private long sqlChildNumber; // SergDzh: to show in list
    private String user;
    private String schema;
    private String type;
    private String status;
    private String state;
    private String sqlId;
    private String sql;
    private final String event;
    private long secondsInWait;
    private long elapsedTime;
    private Timestamp logonTime;
    private String serviceName;

    private String server;
    private String remoteHost;
    private String remoteUser;
    private String remoteProgram;
    private String module;
    private final String action;
    private final String clientInfo;
    private final String process;

    private final long blockGets;
    private final long consistentGets;
    private final long physicalReads;
    private final long blockChanges;
    private final long consistentChanges;

    //private final long statCPU;


    public OracleServerSession(ResultSet dbResult) {
        this.instId = JDBCUtils.safeGetLong(dbResult, "INST_ID");
        this.sid = JDBCUtils.safeGetLong(dbResult, "SID");
        this.serial = JDBCUtils.safeGetLong(dbResult, "SERIAL#");
        this.user = JDBCUtils.safeGetString(dbResult, "USERNAME");
        this.schema = JDBCUtils.safeGetString(dbResult, "SCHEMANAME");
        this.type = JDBCUtils.safeGetString(dbResult, "TYPE");
        this.status = JDBCUtils.safeGetString(dbResult, OracleConstants.COLUMN_STATUS);
        this.state = JDBCUtils.safeGetString(dbResult, "STATE");
        this.sqlId = JDBCUtils.safeGetString(dbResult, "SQL_ID");
        this.sqlChildNumber = JDBCUtils.safeGetLong(dbResult, "SQL_CHILD_NUMBER");
        this.sql = JDBCUtils.safeGetString(dbResult, "SQL_FULLTEXT");
        this.elapsedTime = JDBCUtils.safeGetLong(dbResult, "LAST_CALL_ET");
        this.logonTime = JDBCUtils.safeGetTimestamp(dbResult, "LOGON_TIME");
        this.serviceName = JDBCUtils.safeGetString(dbResult, "SERVICE_NAME");

        this.server = JDBCUtils.safeGetString(dbResult, "SERVER");
        this.remoteHost = JDBCUtils.safeGetString(dbResult, "MACHINE");
        this.remoteUser = JDBCUtils.safeGetString(dbResult, "OSUSER");
        this.remoteProgram = JDBCUtils.safeGetString(dbResult, "PROGRAM");
        this.module = JDBCUtils.safeGetString(dbResult, "MODULE");
        this.action = JDBCUtils.safeGetString(dbResult, "ACTION");
        this.clientInfo = JDBCUtils.safeGetString(dbResult, "CLIENT_INFO");
        this.process = JDBCUtils.safeGetString(dbResult, "PROCESS");

        this.blockGets = JDBCUtils.safeGetLong(dbResult, "BLOCK_GETS");
        this.consistentGets = JDBCUtils.safeGetLong(dbResult, "CONSISTENT_GETS");
        this.physicalReads = JDBCUtils.safeGetLong(dbResult, "PHYSICAL_READS");
        this.blockChanges = JDBCUtils.safeGetLong(dbResult, "BLOCK_CHANGES");
        this.consistentChanges = JDBCUtils.safeGetLong(dbResult, "CONSISTENT_CHANGES");

        this.event = JDBCUtils.safeGetString(dbResult, "EVENT");
        this.secondsInWait = JDBCUtils.safeGetLong(dbResult, "SECONDS_IN_WAIT");

        //this.statCPU = JDBCUtils.safeGetLong(dbResult, "STAT_CPU") * 10;

    }

    @Property(category = CAT_SESSION, viewable = false, order = 1)
    public long getInstId() {
        return instId;
    }

    @Property(category = CAT_SESSION, viewable = true, order = 2)
    public long getSid() {
        return sid;
    }

    @Property(category = CAT_SESSION, viewable = false, order = 3)
    public long getSerial() {
        return serial;
    }

    @Property(category = CAT_SESSION, viewable = true, order = 4)
    public String getUser() {
        return user;
    }

    @Property(category = CAT_SESSION, viewable = true, order = 5)
    public String getSchema() {
        return schema;
    }

    @Property(category = CAT_SESSION, viewable = true, order = 6)
    public String getType() {
        return type;
    }

    @Property(category = CAT_SESSION, viewable = true, order = 7)
    public String getStatus() {
        return status;
    }

    @Property(category = CAT_SESSION, viewable = true, order = 8)
    public String getState() {
        return state;
    }

    @Property(category = CAT_SESSION, viewable = true, order = 9)
    public long getElapsedTime() {
        return elapsedTime;
    }

    @Property(category = CAT_SESSION, order = 10)
    public Timestamp getLogonTime() {
        return logonTime;
    }

    @Property(category = CAT_SESSION, order = 11)
    public String getServiceName() {
        return serviceName;
    }

    @Property(category = CAT_SQL, order = 20)
    public String getSql() {
        return sql;
    }

    @Property(category = CAT_SQL, order = 21)
    public String getSqlId() {
        return sqlId;
    }

    @Property(category = CAT_SQL, order = 22) // SergDzh: to show in list
    public long getSqlChildNumber() {
        return sqlChildNumber;
    }

    @Property(category = CAT_PROCESS, viewable = true, order = 30)
    public String getServer() {
        return server;
    }

    @Property(category = CAT_PROCESS, viewable = true, order = 30)
    public String getRemoteHost() {
        return remoteHost;
    }

    @Property(category = CAT_PROCESS, viewable = true, order = 31)
    public String getRemoteUser() {
        return remoteUser;
    }

    @Property(category = CAT_PROCESS, viewable = true, order = 32)
    public String getRemoteProgram() {
        return remoteProgram;
    }

    @Property(category = CAT_PROCESS, viewable = false, order = 32)
    public String getModule() {
        return module;
    }

    @Property(category = CAT_PROCESS, viewable = false, order = 32)
    public String getAction() {
        return action;
    }

    @Property(category = CAT_PROCESS, viewable = false, order = 32)
    public String getClientInfo() {
        return clientInfo;
    }

    @Property(category = CAT_PROCESS, viewable = false, order = 32)
    public String getProcess() {
        return process;
    }

    @Property(category = CAT_IO, viewable = false, order = 70)
    public long getBlockGets() {
        return blockGets;
    }

    @Property(category = CAT_IO, viewable = false, order = 70)
    public long getConsistentGets() {
        return consistentGets;
    }

    @Property(category = CAT_IO, viewable = false, order = 70)
    public long getPhysicalReads() {
        return physicalReads;
    }

    @Property(category = CAT_IO, viewable = false, order = 70)
    public long getBlockChanges() {
        return blockChanges;
    }

    @Property(category = CAT_IO, viewable = false, order = 70)
    public long getConsistentChanges() {
        return consistentChanges;
    }

    @Property(category = CAT_WAIT, viewable = true, order = 41)
    public String getEvent() {
        return event;
    }

    @Property(category = CAT_WAIT, viewable = true, order = 42)
    public long getSecondsInWait() {
        return secondsInWait;
    }

    //    @Property(category = CAT_STAT, viewable = false, order = 80)
//    public long getStatCPU() {
//        return statCPU;
//    }

    @Override
    public String getActiveQuery() {
        return sql;
    }

    @Override
    public Object getActiveQueryId() {
        return sqlId;
    }

    @Override
    public String toString() {
        return sid + " - " + event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OracleServerSession that = (OracleServerSession) o;
        return sid == that.sid && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, event);
    }
}
