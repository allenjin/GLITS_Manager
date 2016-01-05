/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.grandland.glits.ms.protocol;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-01-04")
public class ProcessStatus implements org.apache.thrift.TBase<ProcessStatus, ProcessStatus._Fields>, java.io.Serializable, Cloneable, Comparable<ProcessStatus> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ProcessStatus");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField STATUS_FIELD_DESC = new org.apache.thrift.protocol.TField("status", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PID_FIELD_DESC = new org.apache.thrift.protocol.TField("pid", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField CPU_PERCENT_FIELD_DESC = new org.apache.thrift.protocol.TField("cpu_percent", org.apache.thrift.protocol.TType.DOUBLE, (short)5);
  private static final org.apache.thrift.protocol.TField MEM_PERCENT_FIELD_DESC = new org.apache.thrift.protocol.TField("mem_percent", org.apache.thrift.protocol.TType.DOUBLE, (short)6);
  private static final org.apache.thrift.protocol.TField RUN_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("run_time", org.apache.thrift.protocol.TType.I64, (short)7);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ProcessStatusStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ProcessStatusTupleSchemeFactory());
  }

  public int id; // required
  public String name; // required
  public String status; // required
  public int pid; // required
  public double cpu_percent; // required
  public double mem_percent; // required
  public long run_time; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    NAME((short)2, "name"),
    STATUS((short)3, "status"),
    PID((short)4, "pid"),
    CPU_PERCENT((short)5, "cpu_percent"),
    MEM_PERCENT((short)6, "mem_percent"),
    RUN_TIME((short)7, "run_time");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // ID
          return ID;
        case 2: // NAME
          return NAME;
        case 3: // STATUS
          return STATUS;
        case 4: // PID
          return PID;
        case 5: // CPU_PERCENT
          return CPU_PERCENT;
        case 6: // MEM_PERCENT
          return MEM_PERCENT;
        case 7: // RUN_TIME
          return RUN_TIME;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ID_ISSET_ID = 0;
  private static final int __PID_ISSET_ID = 1;
  private static final int __CPU_PERCENT_ISSET_ID = 2;
  private static final int __MEM_PERCENT_ISSET_ID = 3;
  private static final int __RUN_TIME_ISSET_ID = 4;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "int")));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.STATUS, new org.apache.thrift.meta_data.FieldMetaData("status", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PID, new org.apache.thrift.meta_data.FieldMetaData("pid", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "int")));
    tmpMap.put(_Fields.CPU_PERCENT, new org.apache.thrift.meta_data.FieldMetaData("cpu_percent", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.MEM_PERCENT, new org.apache.thrift.meta_data.FieldMetaData("mem_percent", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.RUN_TIME, new org.apache.thrift.meta_data.FieldMetaData("run_time", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64        , "long")));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ProcessStatus.class, metaDataMap);
  }

  public ProcessStatus() {
  }

  public ProcessStatus(
    int id,
    String name,
    String status,
    int pid,
    double cpu_percent,
    double mem_percent,
    long run_time)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.name = name;
    this.status = status;
    this.pid = pid;
    setPidIsSet(true);
    this.cpu_percent = cpu_percent;
    setCpu_percentIsSet(true);
    this.mem_percent = mem_percent;
    setMem_percentIsSet(true);
    this.run_time = run_time;
    setRun_timeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ProcessStatus(ProcessStatus other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetStatus()) {
      this.status = other.status;
    }
    this.pid = other.pid;
    this.cpu_percent = other.cpu_percent;
    this.mem_percent = other.mem_percent;
    this.run_time = other.run_time;
  }

  public ProcessStatus deepCopy() {
    return new ProcessStatus(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.name = null;
    this.status = null;
    setPidIsSet(false);
    this.pid = 0;
    setCpu_percentIsSet(false);
    this.cpu_percent = 0.0;
    setMem_percentIsSet(false);
    this.mem_percent = 0.0;
    setRun_timeIsSet(false);
    this.run_time = 0;
  }

  public int getId() {
    return this.id;
  }

  public ProcessStatus setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public String getName() {
    return this.name;
  }

  public ProcessStatus setName(String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public String getStatus() {
    return this.status;
  }

  public ProcessStatus setStatus(String status) {
    this.status = status;
    return this;
  }

  public void unsetStatus() {
    this.status = null;
  }

  /** Returns true if field status is set (has been assigned a value) and false otherwise */
  public boolean isSetStatus() {
    return this.status != null;
  }

  public void setStatusIsSet(boolean value) {
    if (!value) {
      this.status = null;
    }
  }

  public int getPid() {
    return this.pid;
  }

  public ProcessStatus setPid(int pid) {
    this.pid = pid;
    setPidIsSet(true);
    return this;
  }

  public void unsetPid() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __PID_ISSET_ID);
  }

  /** Returns true if field pid is set (has been assigned a value) and false otherwise */
  public boolean isSetPid() {
    return EncodingUtils.testBit(__isset_bitfield, __PID_ISSET_ID);
  }

  public void setPidIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __PID_ISSET_ID, value);
  }

  public double getCpu_percent() {
    return this.cpu_percent;
  }

  public ProcessStatus setCpu_percent(double cpu_percent) {
    this.cpu_percent = cpu_percent;
    setCpu_percentIsSet(true);
    return this;
  }

  public void unsetCpu_percent() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __CPU_PERCENT_ISSET_ID);
  }

  /** Returns true if field cpu_percent is set (has been assigned a value) and false otherwise */
  public boolean isSetCpu_percent() {
    return EncodingUtils.testBit(__isset_bitfield, __CPU_PERCENT_ISSET_ID);
  }

  public void setCpu_percentIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __CPU_PERCENT_ISSET_ID, value);
  }

  public double getMem_percent() {
    return this.mem_percent;
  }

  public ProcessStatus setMem_percent(double mem_percent) {
    this.mem_percent = mem_percent;
    setMem_percentIsSet(true);
    return this;
  }

  public void unsetMem_percent() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MEM_PERCENT_ISSET_ID);
  }

  /** Returns true if field mem_percent is set (has been assigned a value) and false otherwise */
  public boolean isSetMem_percent() {
    return EncodingUtils.testBit(__isset_bitfield, __MEM_PERCENT_ISSET_ID);
  }

  public void setMem_percentIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MEM_PERCENT_ISSET_ID, value);
  }

  public long getRun_time() {
    return this.run_time;
  }

  public ProcessStatus setRun_time(long run_time) {
    this.run_time = run_time;
    setRun_timeIsSet(true);
    return this;
  }

  public void unsetRun_time() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RUN_TIME_ISSET_ID);
  }

  /** Returns true if field run_time is set (has been assigned a value) and false otherwise */
  public boolean isSetRun_time() {
    return EncodingUtils.testBit(__isset_bitfield, __RUN_TIME_ISSET_ID);
  }

  public void setRun_timeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RUN_TIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((String)value);
      }
      break;

    case STATUS:
      if (value == null) {
        unsetStatus();
      } else {
        setStatus((String)value);
      }
      break;

    case PID:
      if (value == null) {
        unsetPid();
      } else {
        setPid((Integer)value);
      }
      break;

    case CPU_PERCENT:
      if (value == null) {
        unsetCpu_percent();
      } else {
        setCpu_percent((Double)value);
      }
      break;

    case MEM_PERCENT:
      if (value == null) {
        unsetMem_percent();
      } else {
        setMem_percent((Double)value);
      }
      break;

    case RUN_TIME:
      if (value == null) {
        unsetRun_time();
      } else {
        setRun_time((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return getId();

    case NAME:
      return getName();

    case STATUS:
      return getStatus();

    case PID:
      return getPid();

    case CPU_PERCENT:
      return getCpu_percent();

    case MEM_PERCENT:
      return getMem_percent();

    case RUN_TIME:
      return getRun_time();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case NAME:
      return isSetName();
    case STATUS:
      return isSetStatus();
    case PID:
      return isSetPid();
    case CPU_PERCENT:
      return isSetCpu_percent();
    case MEM_PERCENT:
      return isSetMem_percent();
    case RUN_TIME:
      return isSetRun_time();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof ProcessStatus)
      return this.equals((ProcessStatus)that);
    return false;
  }

  public boolean equals(ProcessStatus that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_status = true && this.isSetStatus();
    boolean that_present_status = true && that.isSetStatus();
    if (this_present_status || that_present_status) {
      if (!(this_present_status && that_present_status))
        return false;
      if (!this.status.equals(that.status))
        return false;
    }

    boolean this_present_pid = true;
    boolean that_present_pid = true;
    if (this_present_pid || that_present_pid) {
      if (!(this_present_pid && that_present_pid))
        return false;
      if (this.pid != that.pid)
        return false;
    }

    boolean this_present_cpu_percent = true;
    boolean that_present_cpu_percent = true;
    if (this_present_cpu_percent || that_present_cpu_percent) {
      if (!(this_present_cpu_percent && that_present_cpu_percent))
        return false;
      if (this.cpu_percent != that.cpu_percent)
        return false;
    }

    boolean this_present_mem_percent = true;
    boolean that_present_mem_percent = true;
    if (this_present_mem_percent || that_present_mem_percent) {
      if (!(this_present_mem_percent && that_present_mem_percent))
        return false;
      if (this.mem_percent != that.mem_percent)
        return false;
    }

    boolean this_present_run_time = true;
    boolean that_present_run_time = true;
    if (this_present_run_time || that_present_run_time) {
      if (!(this_present_run_time && that_present_run_time))
        return false;
      if (this.run_time != that.run_time)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_id = true;
    list.add(present_id);
    if (present_id)
      list.add(id);

    boolean present_name = true && (isSetName());
    list.add(present_name);
    if (present_name)
      list.add(name);

    boolean present_status = true && (isSetStatus());
    list.add(present_status);
    if (present_status)
      list.add(status);

    boolean present_pid = true;
    list.add(present_pid);
    if (present_pid)
      list.add(pid);

    boolean present_cpu_percent = true;
    list.add(present_cpu_percent);
    if (present_cpu_percent)
      list.add(cpu_percent);

    boolean present_mem_percent = true;
    list.add(present_mem_percent);
    if (present_mem_percent)
      list.add(mem_percent);

    boolean present_run_time = true;
    list.add(present_run_time);
    if (present_run_time)
      list.add(run_time);

    return list.hashCode();
  }

  @Override
  public int compareTo(ProcessStatus other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetStatus()).compareTo(other.isSetStatus());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStatus()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.status, other.status);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetPid()).compareTo(other.isSetPid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pid, other.pid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCpu_percent()).compareTo(other.isSetCpu_percent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCpu_percent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cpu_percent, other.cpu_percent);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMem_percent()).compareTo(other.isSetMem_percent());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMem_percent()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mem_percent, other.mem_percent);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRun_time()).compareTo(other.isSetRun_time());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRun_time()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.run_time, other.run_time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("ProcessStatus(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("status:");
    if (this.status == null) {
      sb.append("null");
    } else {
      sb.append(this.status);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("pid:");
    sb.append(this.pid);
    first = false;
    if (!first) sb.append(", ");
    sb.append("cpu_percent:");
    sb.append(this.cpu_percent);
    first = false;
    if (!first) sb.append(", ");
    sb.append("mem_percent:");
    sb.append(this.mem_percent);
    first = false;
    if (!first) sb.append(", ");
    sb.append("run_time:");
    sb.append(this.run_time);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ProcessStatusStandardSchemeFactory implements SchemeFactory {
    public ProcessStatusStandardScheme getScheme() {
      return new ProcessStatusStandardScheme();
    }
  }

  private static class ProcessStatusStandardScheme extends StandardScheme<ProcessStatus> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ProcessStatus struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // STATUS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.status = iprot.readString();
              struct.setStatusIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.pid = iprot.readI32();
              struct.setPidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CPU_PERCENT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.cpu_percent = iprot.readDouble();
              struct.setCpu_percentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // MEM_PERCENT
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.mem_percent = iprot.readDouble();
              struct.setMem_percentIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // RUN_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.run_time = iprot.readI64();
              struct.setRun_timeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ProcessStatus struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.status != null) {
        oprot.writeFieldBegin(STATUS_FIELD_DESC);
        oprot.writeString(struct.status);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PID_FIELD_DESC);
      oprot.writeI32(struct.pid);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(CPU_PERCENT_FIELD_DESC);
      oprot.writeDouble(struct.cpu_percent);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MEM_PERCENT_FIELD_DESC);
      oprot.writeDouble(struct.mem_percent);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(RUN_TIME_FIELD_DESC);
      oprot.writeI64(struct.run_time);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ProcessStatusTupleSchemeFactory implements SchemeFactory {
    public ProcessStatusTupleScheme getScheme() {
      return new ProcessStatusTupleScheme();
    }
  }

  private static class ProcessStatusTupleScheme extends TupleScheme<ProcessStatus> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ProcessStatus struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetName()) {
        optionals.set(1);
      }
      if (struct.isSetStatus()) {
        optionals.set(2);
      }
      if (struct.isSetPid()) {
        optionals.set(3);
      }
      if (struct.isSetCpu_percent()) {
        optionals.set(4);
      }
      if (struct.isSetMem_percent()) {
        optionals.set(5);
      }
      if (struct.isSetRun_time()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetStatus()) {
        oprot.writeString(struct.status);
      }
      if (struct.isSetPid()) {
        oprot.writeI32(struct.pid);
      }
      if (struct.isSetCpu_percent()) {
        oprot.writeDouble(struct.cpu_percent);
      }
      if (struct.isSetMem_percent()) {
        oprot.writeDouble(struct.mem_percent);
      }
      if (struct.isSetRun_time()) {
        oprot.writeI64(struct.run_time);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ProcessStatus struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.status = iprot.readString();
        struct.setStatusIsSet(true);
      }
      if (incoming.get(3)) {
        struct.pid = iprot.readI32();
        struct.setPidIsSet(true);
      }
      if (incoming.get(4)) {
        struct.cpu_percent = iprot.readDouble();
        struct.setCpu_percentIsSet(true);
      }
      if (incoming.get(5)) {
        struct.mem_percent = iprot.readDouble();
        struct.setMem_percentIsSet(true);
      }
      if (incoming.get(6)) {
        struct.run_time = iprot.readI64();
        struct.setRun_timeIsSet(true);
      }
    }
  }

}
