#
# Autogenerated by Thrift Compiler (0.9.3)
#
# DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
#
#  options string: py
#

from thrift.Thrift import TType, TMessageType, TException, TApplicationException

from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol, TProtocol
try:
  from thrift.protocol import fastbinary
except:
  fastbinary = None



class HeartbeatRequest:
  """
  Attributes:
   - version
   - hostname
   - total_cpu
   - memory_usage
   - processes
   - mounted_avail_space
  """

  thrift_spec = (
    None, # 0
    (1, TType.I32, 'version', None, None, ), # 1
    (2, TType.STRING, 'hostname', None, None, ), # 2
    (3, TType.DOUBLE, 'total_cpu', None, None, ), # 3
    (4, TType.DOUBLE, 'memory_usage', None, None, ), # 4
    (5, TType.LIST, 'processes', (TType.STRING,None), None, ), # 5
    (6, TType.MAP, 'mounted_avail_space', (TType.STRING,None,TType.I64,None), None, ), # 6
  )

  def __init__(self, version=None, hostname=None, total_cpu=None, memory_usage=None, processes=None, mounted_avail_space=None,):
    self.version = version
    self.hostname = hostname
    self.total_cpu = total_cpu
    self.memory_usage = memory_usage
    self.processes = processes
    self.mounted_avail_space = mounted_avail_space

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.I32:
          self.version = iprot.readI32()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.hostname = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.DOUBLE:
          self.total_cpu = iprot.readDouble()
        else:
          iprot.skip(ftype)
      elif fid == 4:
        if ftype == TType.DOUBLE:
          self.memory_usage = iprot.readDouble()
        else:
          iprot.skip(ftype)
      elif fid == 5:
        if ftype == TType.LIST:
          self.processes = []
          (_etype3, _size0) = iprot.readListBegin()
          for _i4 in xrange(_size0):
            _elem5 = iprot.readString()
            self.processes.append(_elem5)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 6:
        if ftype == TType.MAP:
          self.mounted_avail_space = {}
          (_ktype7, _vtype8, _size6 ) = iprot.readMapBegin()
          for _i10 in xrange(_size6):
            _key11 = iprot.readString()
            _val12 = iprot.readI64()
            self.mounted_avail_space[_key11] = _val12
          iprot.readMapEnd()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('HeartbeatRequest')
    if self.version is not None:
      oprot.writeFieldBegin('version', TType.I32, 1)
      oprot.writeI32(self.version)
      oprot.writeFieldEnd()
    if self.hostname is not None:
      oprot.writeFieldBegin('hostname', TType.STRING, 2)
      oprot.writeString(self.hostname)
      oprot.writeFieldEnd()
    if self.total_cpu is not None:
      oprot.writeFieldBegin('total_cpu', TType.DOUBLE, 3)
      oprot.writeDouble(self.total_cpu)
      oprot.writeFieldEnd()
    if self.memory_usage is not None:
      oprot.writeFieldBegin('memory_usage', TType.DOUBLE, 4)
      oprot.writeDouble(self.memory_usage)
      oprot.writeFieldEnd()
    if self.processes is not None:
      oprot.writeFieldBegin('processes', TType.LIST, 5)
      oprot.writeListBegin(TType.STRING, len(self.processes))
      for iter13 in self.processes:
        oprot.writeString(iter13)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.mounted_avail_space is not None:
      oprot.writeFieldBegin('mounted_avail_space', TType.MAP, 6)
      oprot.writeMapBegin(TType.STRING, TType.I64, len(self.mounted_avail_space))
      for kiter14,viter15 in self.mounted_avail_space.items():
        oprot.writeString(kiter14)
        oprot.writeI64(viter15)
      oprot.writeMapEnd()
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.version)
    value = (value * 31) ^ hash(self.hostname)
    value = (value * 31) ^ hash(self.total_cpu)
    value = (value * 31) ^ hash(self.memory_usage)
    value = (value * 31) ^ hash(self.processes)
    value = (value * 31) ^ hash(self.mounted_avail_space)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class HeartbeatResponse:
  """
  Attributes:
   - hostname
   - heartbeat_interval
   - processes
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRING, 'hostname', None, None, ), # 1
    (2, TType.I64, 'heartbeat_interval', None, None, ), # 2
    (3, TType.LIST, 'processes', (TType.STRING,None), None, ), # 3
  )

  def __init__(self, hostname=None, heartbeat_interval=None, processes=None,):
    self.hostname = hostname
    self.heartbeat_interval = heartbeat_interval
    self.processes = processes

  def read(self, iprot):
    if iprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and isinstance(iprot.trans, TTransport.CReadableTransport) and self.thrift_spec is not None and fastbinary is not None:
      fastbinary.decode_binary(self, iprot.trans, (self.__class__, self.thrift_spec))
      return
    iprot.readStructBegin()
    while True:
      (fname, ftype, fid) = iprot.readFieldBegin()
      if ftype == TType.STOP:
        break
      if fid == 1:
        if ftype == TType.STRING:
          self.hostname = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.I64:
          self.heartbeat_interval = iprot.readI64()
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.LIST:
          self.processes = []
          (_etype19, _size16) = iprot.readListBegin()
          for _i20 in xrange(_size16):
            _elem21 = iprot.readString()
            self.processes.append(_elem21)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      else:
        iprot.skip(ftype)
      iprot.readFieldEnd()
    iprot.readStructEnd()

  def write(self, oprot):
    if oprot.__class__ == TBinaryProtocol.TBinaryProtocolAccelerated and self.thrift_spec is not None and fastbinary is not None:
      oprot.trans.write(fastbinary.encode_binary(self, (self.__class__, self.thrift_spec)))
      return
    oprot.writeStructBegin('HeartbeatResponse')
    if self.hostname is not None:
      oprot.writeFieldBegin('hostname', TType.STRING, 1)
      oprot.writeString(self.hostname)
      oprot.writeFieldEnd()
    if self.heartbeat_interval is not None:
      oprot.writeFieldBegin('heartbeat_interval', TType.I64, 2)
      oprot.writeI64(self.heartbeat_interval)
      oprot.writeFieldEnd()
    if self.processes is not None:
      oprot.writeFieldBegin('processes', TType.LIST, 3)
      oprot.writeListBegin(TType.STRING, len(self.processes))
      for iter22 in self.processes:
        oprot.writeString(iter22)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.hostname)
    value = (value * 31) ^ hash(self.heartbeat_interval)
    value = (value * 31) ^ hash(self.processes)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)
