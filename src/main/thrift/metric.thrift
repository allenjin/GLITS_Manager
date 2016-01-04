namespace java com.grandland.glits.ms.protocol
namespace py protocol.metric

typedef i32 int
typedef i64 long

struct MetricValue{
    1:int id,
    2:string value,
    3:byte type
}

struct NetUpdate{
    1:string iface,
    2:list<MetricValue> metrics
}

struct FsUpdate{
    1:string mount_point,
    2:string partition,
    3:list<MetricValue> metrics
}

struct MetricMessage{
    1:long ts_secs,
    2:string host_name,
    3:int host_id,
    4:list<MetricValue> metrics,
    5:list<NetUpdate> net_updates,
    6:list<FsUpdate> fs_updates
}

service MetricService{
    void sendMetricMessage(1:list<MetricMessage> messages);
}