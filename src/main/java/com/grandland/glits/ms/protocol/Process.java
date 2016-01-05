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
public class Process implements org.apache.thrift.TBase<Process, Process._Fields>, java.io.Serializable, Cloneable, Comparable<Process> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Process");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PROGRAM_FIELD_DESC = new org.apache.thrift.protocol.TField("program", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField RUNNING_FIELD_DESC = new org.apache.thrift.protocol.TField("running", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField AUTO_RESTART_FIELD_DESC = new org.apache.thrift.protocol.TField("auto_restart", org.apache.thrift.protocol.TType.BOOL, (short)5);
  private static final org.apache.thrift.protocol.TField ARGUMENTS_FIELD_DESC = new org.apache.thrift.protocol.TField("arguments", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new ProcessStandardSchemeFactory());
    schemes.put(TupleScheme.class, new ProcessTupleSchemeFactory());
  }

  public int id; // required
  public String name; // required
  public String program; // required
  public boolean running; // required
  public boolean auto_restart; // required
  public String arguments; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    NAME((short)2, "name"),
    PROGRAM((short)3, "program"),
    RUNNING((short)4, "running"),
    AUTO_RESTART((short)5, "auto_restart"),
    ARGUMENTS((short)6, "arguments");

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
        case 3: // PROGRAM
          return PROGRAM;
        case 4: // RUNNING
          return RUNNING;
        case 5: // AUTO_RESTART
          return AUTO_RESTART;
        case 6: // ARGUMENTS
          return ARGUMENTS;
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
  private static final int __RUNNING_ISSET_ID = 1;
  private static final int __AUTO_RESTART_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "int")));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PROGRAM, new org.apache.thrift.meta_data.FieldMetaData("program", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RUNNING, new org.apache.thrift.meta_data.FieldMetaData("running", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.AUTO_RESTART, new org.apache.thrift.meta_data.FieldMetaData("auto_restart", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.ARGUMENTS, new org.apache.thrift.meta_data.FieldMetaData("arguments", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Process.class, metaDataMap);
  }

  public Process() {
  }

  public Process(
    int id,
    String name,
    String program,
    boolean running,
    boolean auto_restart,
    String arguments)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.name = name;
    this.program = program;
    this.running = running;
    setRunningIsSet(true);
    this.auto_restart = auto_restart;
    setAuto_restartIsSet(true);
    this.arguments = arguments;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Process(Process other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetProgram()) {
      this.program = other.program;
    }
    this.running = other.running;
    this.auto_restart = other.auto_restart;
    if (other.isSetArguments()) {
      this.arguments = other.arguments;
    }
  }

  public Process deepCopy() {
    return new Process(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    this.name = null;
    this.program = null;
    setRunningIsSet(false);
    this.running = false;
    setAuto_restartIsSet(false);
    this.auto_restart = false;
    this.arguments = null;
  }

  public int getId() {
    return this.id;
  }

  public Process setId(int id) {
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

  public Process setName(String name) {
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

  public String getProgram() {
    return this.program;
  }

  public Process setProgram(String program) {
    this.program = program;
    return this;
  }

  public void unsetProgram() {
    this.program = null;
  }

  /** Returns true if field program is set (has been assigned a value) and false otherwise */
  public boolean isSetProgram() {
    return this.program != null;
  }

  public void setProgramIsSet(boolean value) {
    if (!value) {
      this.program = null;
    }
  }

  public boolean isRunning() {
    return this.running;
  }

  public Process setRunning(boolean running) {
    this.running = running;
    setRunningIsSet(true);
    return this;
  }

  public void unsetRunning() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RUNNING_ISSET_ID);
  }

  /** Returns true if field running is set (has been assigned a value) and false otherwise */
  public boolean isSetRunning() {
    return EncodingUtils.testBit(__isset_bitfield, __RUNNING_ISSET_ID);
  }

  public void setRunningIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RUNNING_ISSET_ID, value);
  }

  public boolean isAuto_restart() {
    return this.auto_restart;
  }

  public Process setAuto_restart(boolean auto_restart) {
    this.auto_restart = auto_restart;
    setAuto_restartIsSet(true);
    return this;
  }

  public void unsetAuto_restart() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __AUTO_RESTART_ISSET_ID);
  }

  /** Returns true if field auto_restart is set (has been assigned a value) and false otherwise */
  public boolean isSetAuto_restart() {
    return EncodingUtils.testBit(__isset_bitfield, __AUTO_RESTART_ISSET_ID);
  }

  public void setAuto_restartIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __AUTO_RESTART_ISSET_ID, value);
  }

  public String getArguments() {
    return this.arguments;
  }

  public Process setArguments(String arguments) {
    this.arguments = arguments;
    return this;
  }

  public void unsetArguments() {
    this.arguments = null;
  }

  /** Returns true if field arguments is set (has been assigned a value) and false otherwise */
  public boolean isSetArguments() {
    return this.arguments != null;
  }

  public void setArgumentsIsSet(boolean value) {
    if (!value) {
      this.arguments = null;
    }
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

    case PROGRAM:
      if (value == null) {
        unsetProgram();
      } else {
        setProgram((String)value);
      }
      break;

    case RUNNING:
      if (value == null) {
        unsetRunning();
      } else {
        setRunning((Boolean)value);
      }
      break;

    case AUTO_RESTART:
      if (value == null) {
        unsetAuto_restart();
      } else {
        setAuto_restart((Boolean)value);
      }
      break;

    case ARGUMENTS:
      if (value == null) {
        unsetArguments();
      } else {
        setArguments((String)value);
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

    case PROGRAM:
      return getProgram();

    case RUNNING:
      return isRunning();

    case AUTO_RESTART:
      return isAuto_restart();

    case ARGUMENTS:
      return getArguments();

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
    case PROGRAM:
      return isSetProgram();
    case RUNNING:
      return isSetRunning();
    case AUTO_RESTART:
      return isSetAuto_restart();
    case ARGUMENTS:
      return isSetArguments();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Process)
      return this.equals((Process)that);
    return false;
  }

  public boolean equals(Process that) {
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

    boolean this_present_program = true && this.isSetProgram();
    boolean that_present_program = true && that.isSetProgram();
    if (this_present_program || that_present_program) {
      if (!(this_present_program && that_present_program))
        return false;
      if (!this.program.equals(that.program))
        return false;
    }

    boolean this_present_running = true;
    boolean that_present_running = true;
    if (this_present_running || that_present_running) {
      if (!(this_present_running && that_present_running))
        return false;
      if (this.running != that.running)
        return false;
    }

    boolean this_present_auto_restart = true;
    boolean that_present_auto_restart = true;
    if (this_present_auto_restart || that_present_auto_restart) {
      if (!(this_present_auto_restart && that_present_auto_restart))
        return false;
      if (this.auto_restart != that.auto_restart)
        return false;
    }

    boolean this_present_arguments = true && this.isSetArguments();
    boolean that_present_arguments = true && that.isSetArguments();
    if (this_present_arguments || that_present_arguments) {
      if (!(this_present_arguments && that_present_arguments))
        return false;
      if (!this.arguments.equals(that.arguments))
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

    boolean present_program = true && (isSetProgram());
    list.add(present_program);
    if (present_program)
      list.add(program);

    boolean present_running = true;
    list.add(present_running);
    if (present_running)
      list.add(running);

    boolean present_auto_restart = true;
    list.add(present_auto_restart);
    if (present_auto_restart)
      list.add(auto_restart);

    boolean present_arguments = true && (isSetArguments());
    list.add(present_arguments);
    if (present_arguments)
      list.add(arguments);

    return list.hashCode();
  }

  @Override
  public int compareTo(Process other) {
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
    lastComparison = Boolean.valueOf(isSetProgram()).compareTo(other.isSetProgram());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetProgram()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.program, other.program);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetRunning()).compareTo(other.isSetRunning());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRunning()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.running, other.running);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAuto_restart()).compareTo(other.isSetAuto_restart());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAuto_restart()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.auto_restart, other.auto_restart);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetArguments()).compareTo(other.isSetArguments());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetArguments()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.arguments, other.arguments);
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
    StringBuilder sb = new StringBuilder("Process(");
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
    sb.append("program:");
    if (this.program == null) {
      sb.append("null");
    } else {
      sb.append(this.program);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("running:");
    sb.append(this.running);
    first = false;
    if (!first) sb.append(", ");
    sb.append("auto_restart:");
    sb.append(this.auto_restart);
    first = false;
    if (!first) sb.append(", ");
    sb.append("arguments:");
    if (this.arguments == null) {
      sb.append("null");
    } else {
      sb.append(this.arguments);
    }
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

  private static class ProcessStandardSchemeFactory implements SchemeFactory {
    public ProcessStandardScheme getScheme() {
      return new ProcessStandardScheme();
    }
  }

  private static class ProcessStandardScheme extends StandardScheme<Process> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Process struct) throws TException {
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
          case 3: // PROGRAM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.program = iprot.readString();
              struct.setProgramIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // RUNNING
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.running = iprot.readBool();
              struct.setRunningIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // AUTO_RESTART
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.auto_restart = iprot.readBool();
              struct.setAuto_restartIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // ARGUMENTS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.arguments = iprot.readString();
              struct.setArgumentsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Process struct) throws TException {
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
      if (struct.program != null) {
        oprot.writeFieldBegin(PROGRAM_FIELD_DESC);
        oprot.writeString(struct.program);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(RUNNING_FIELD_DESC);
      oprot.writeBool(struct.running);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(AUTO_RESTART_FIELD_DESC);
      oprot.writeBool(struct.auto_restart);
      oprot.writeFieldEnd();
      if (struct.arguments != null) {
        oprot.writeFieldBegin(ARGUMENTS_FIELD_DESC);
        oprot.writeString(struct.arguments);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ProcessTupleSchemeFactory implements SchemeFactory {
    public ProcessTupleScheme getScheme() {
      return new ProcessTupleScheme();
    }
  }

  private static class ProcessTupleScheme extends TupleScheme<Process> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Process struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetName()) {
        optionals.set(1);
      }
      if (struct.isSetProgram()) {
        optionals.set(2);
      }
      if (struct.isSetRunning()) {
        optionals.set(3);
      }
      if (struct.isSetAuto_restart()) {
        optionals.set(4);
      }
      if (struct.isSetArguments()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetProgram()) {
        oprot.writeString(struct.program);
      }
      if (struct.isSetRunning()) {
        oprot.writeBool(struct.running);
      }
      if (struct.isSetAuto_restart()) {
        oprot.writeBool(struct.auto_restart);
      }
      if (struct.isSetArguments()) {
        oprot.writeString(struct.arguments);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Process struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.program = iprot.readString();
        struct.setProgramIsSet(true);
      }
      if (incoming.get(3)) {
        struct.running = iprot.readBool();
        struct.setRunningIsSet(true);
      }
      if (incoming.get(4)) {
        struct.auto_restart = iprot.readBool();
        struct.setAuto_restartIsSet(true);
      }
      if (incoming.get(5)) {
        struct.arguments = iprot.readString();
        struct.setArgumentsIsSet(true);
      }
    }
  }

}
