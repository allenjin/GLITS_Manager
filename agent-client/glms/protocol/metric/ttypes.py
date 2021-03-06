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



class MetricValue:
  """
  Attributes:
   - id
   - value
   - type
  """

  thrift_spec = (
    None, # 0
    (1, TType.I32, 'id', None, None, ), # 1
    (2, TType.STRING, 'value', None, None, ), # 2
    (3, TType.BYTE, 'type', None, None, ), # 3
  )

  def __init__(self, id=None, value=None, type=None,):
    self.id = id
    self.value = value
    self.type = type

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
          self.id = iprot.readI32()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.value = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.BYTE:
          self.type = iprot.readByte()
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
    oprot.writeStructBegin('MetricValue')
    if self.id is not None:
      oprot.writeFieldBegin('id', TType.I32, 1)
      oprot.writeI32(self.id)
      oprot.writeFieldEnd()
    if self.value is not None:
      oprot.writeFieldBegin('value', TType.STRING, 2)
      oprot.writeString(self.value)
      oprot.writeFieldEnd()
    if self.type is not None:
      oprot.writeFieldBegin('type', TType.BYTE, 3)
      oprot.writeByte(self.type)
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.id)
    value = (value * 31) ^ hash(self.value)
    value = (value * 31) ^ hash(self.type)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class NetUpdate:
  """
  Attributes:
   - iface
   - metrics
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRING, 'iface', None, None, ), # 1
    (2, TType.LIST, 'metrics', (TType.STRUCT,(MetricValue, MetricValue.thrift_spec)), None, ), # 2
  )

  def __init__(self, iface=None, metrics=None,):
    self.iface = iface
    self.metrics = metrics

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
          self.iface = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.LIST:
          self.metrics = []
          (_etype3, _size0) = iprot.readListBegin()
          for _i4 in xrange(_size0):
            _elem5 = MetricValue()
            _elem5.read(iprot)
            self.metrics.append(_elem5)
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
    oprot.writeStructBegin('NetUpdate')
    if self.iface is not None:
      oprot.writeFieldBegin('iface', TType.STRING, 1)
      oprot.writeString(self.iface)
      oprot.writeFieldEnd()
    if self.metrics is not None:
      oprot.writeFieldBegin('metrics', TType.LIST, 2)
      oprot.writeListBegin(TType.STRUCT, len(self.metrics))
      for iter6 in self.metrics:
        iter6.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.iface)
    value = (value * 31) ^ hash(self.metrics)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class FsUpdate:
  """
  Attributes:
   - mount_point
   - partition
   - metrics
  """

  thrift_spec = (
    None, # 0
    (1, TType.STRING, 'mount_point', None, None, ), # 1
    (2, TType.STRING, 'partition', None, None, ), # 2
    (3, TType.LIST, 'metrics', (TType.STRUCT,(MetricValue, MetricValue.thrift_spec)), None, ), # 3
  )

  def __init__(self, mount_point=None, partition=None, metrics=None,):
    self.mount_point = mount_point
    self.partition = partition
    self.metrics = metrics

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
          self.mount_point = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.partition = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.LIST:
          self.metrics = []
          (_etype10, _size7) = iprot.readListBegin()
          for _i11 in xrange(_size7):
            _elem12 = MetricValue()
            _elem12.read(iprot)
            self.metrics.append(_elem12)
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
    oprot.writeStructBegin('FsUpdate')
    if self.mount_point is not None:
      oprot.writeFieldBegin('mount_point', TType.STRING, 1)
      oprot.writeString(self.mount_point)
      oprot.writeFieldEnd()
    if self.partition is not None:
      oprot.writeFieldBegin('partition', TType.STRING, 2)
      oprot.writeString(self.partition)
      oprot.writeFieldEnd()
    if self.metrics is not None:
      oprot.writeFieldBegin('metrics', TType.LIST, 3)
      oprot.writeListBegin(TType.STRUCT, len(self.metrics))
      for iter13 in self.metrics:
        iter13.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.mount_point)
    value = (value * 31) ^ hash(self.partition)
    value = (value * 31) ^ hash(self.metrics)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)

class MetricMessage:
  """
  Attributes:
   - ts_secs
   - host_name
   - host_id
   - metrics
   - net_updates
   - fs_updates
  """

  thrift_spec = (
    None, # 0
    (1, TType.I64, 'ts_secs', None, None, ), # 1
    (2, TType.STRING, 'host_name', None, None, ), # 2
    (3, TType.I32, 'host_id', None, None, ), # 3
    (4, TType.LIST, 'metrics', (TType.STRUCT,(MetricValue, MetricValue.thrift_spec)), None, ), # 4
    (5, TType.LIST, 'net_updates', (TType.STRUCT,(NetUpdate, NetUpdate.thrift_spec)), None, ), # 5
    (6, TType.LIST, 'fs_updates', (TType.STRUCT,(FsUpdate, FsUpdate.thrift_spec)), None, ), # 6
  )

  def __init__(self, ts_secs=None, host_name=None, host_id=None, metrics=None, net_updates=None, fs_updates=None,):
    self.ts_secs = ts_secs
    self.host_name = host_name
    self.host_id = host_id
    self.metrics = metrics
    self.net_updates = net_updates
    self.fs_updates = fs_updates

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
        if ftype == TType.I64:
          self.ts_secs = iprot.readI64()
        else:
          iprot.skip(ftype)
      elif fid == 2:
        if ftype == TType.STRING:
          self.host_name = iprot.readString()
        else:
          iprot.skip(ftype)
      elif fid == 3:
        if ftype == TType.I32:
          self.host_id = iprot.readI32()
        else:
          iprot.skip(ftype)
      elif fid == 4:
        if ftype == TType.LIST:
          self.metrics = []
          (_etype17, _size14) = iprot.readListBegin()
          for _i18 in xrange(_size14):
            _elem19 = MetricValue()
            _elem19.read(iprot)
            self.metrics.append(_elem19)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 5:
        if ftype == TType.LIST:
          self.net_updates = []
          (_etype23, _size20) = iprot.readListBegin()
          for _i24 in xrange(_size20):
            _elem25 = NetUpdate()
            _elem25.read(iprot)
            self.net_updates.append(_elem25)
          iprot.readListEnd()
        else:
          iprot.skip(ftype)
      elif fid == 6:
        if ftype == TType.LIST:
          self.fs_updates = []
          (_etype29, _size26) = iprot.readListBegin()
          for _i30 in xrange(_size26):
            _elem31 = FsUpdate()
            _elem31.read(iprot)
            self.fs_updates.append(_elem31)
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
    oprot.writeStructBegin('MetricMessage')
    if self.ts_secs is not None:
      oprot.writeFieldBegin('ts_secs', TType.I64, 1)
      oprot.writeI64(self.ts_secs)
      oprot.writeFieldEnd()
    if self.host_name is not None:
      oprot.writeFieldBegin('host_name', TType.STRING, 2)
      oprot.writeString(self.host_name)
      oprot.writeFieldEnd()
    if self.host_id is not None:
      oprot.writeFieldBegin('host_id', TType.I32, 3)
      oprot.writeI32(self.host_id)
      oprot.writeFieldEnd()
    if self.metrics is not None:
      oprot.writeFieldBegin('metrics', TType.LIST, 4)
      oprot.writeListBegin(TType.STRUCT, len(self.metrics))
      for iter32 in self.metrics:
        iter32.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.net_updates is not None:
      oprot.writeFieldBegin('net_updates', TType.LIST, 5)
      oprot.writeListBegin(TType.STRUCT, len(self.net_updates))
      for iter33 in self.net_updates:
        iter33.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    if self.fs_updates is not None:
      oprot.writeFieldBegin('fs_updates', TType.LIST, 6)
      oprot.writeListBegin(TType.STRUCT, len(self.fs_updates))
      for iter34 in self.fs_updates:
        iter34.write(oprot)
      oprot.writeListEnd()
      oprot.writeFieldEnd()
    oprot.writeFieldStop()
    oprot.writeStructEnd()

  def validate(self):
    return


  def __hash__(self):
    value = 17
    value = (value * 31) ^ hash(self.ts_secs)
    value = (value * 31) ^ hash(self.host_name)
    value = (value * 31) ^ hash(self.host_id)
    value = (value * 31) ^ hash(self.metrics)
    value = (value * 31) ^ hash(self.net_updates)
    value = (value * 31) ^ hash(self.fs_updates)
    return value

  def __repr__(self):
    L = ['%s=%r' % (key, value)
      for key, value in self.__dict__.iteritems()]
    return '%s(%s)' % (self.__class__.__name__, ', '.join(L))

  def __eq__(self, other):
    return isinstance(other, self.__class__) and self.__dict__ == other.__dict__

  def __ne__(self, other):
    return not (self == other)
