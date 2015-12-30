namespace java com.grandland.glits.ms.protocol
namespace py protocol

typedef i32 int
typedef i64 long

struct HeartbeatRequest{
    1:int version,
    2:string host_name,
    3:string ip_address,
    4:double total_cpu,
    5:double memory_usage,
    6:map<string,string> processes,
    7:map<string,long> mounted_avail_space
}

struct HeartbeatResponse{
    1:string host_name,
    2:long heartbeat_interval,
    3:map<string,string> processes,
    4:map<string,string> extra_configs
}

service HeartbeatService{
    HeartbeatResponse heartbeat(1:HeartbeatRequest request);
}