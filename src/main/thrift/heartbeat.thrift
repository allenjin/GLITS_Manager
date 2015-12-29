namespace java com.grandland.glits.ms.agent.protocol
namespace py protocol

typedef i32 int
typedef i64 long

struct HeartbeatRequest{
    1:int version,
    2:string hostname,
    3:double total_cpu,
    4:double memory_usage,
    5:list<string> processes,
    6:map<string,long> mounted_avail_space
}

struct HeartbeatResponse{
    1:string hostname,
    2:long heartbeat_interval,
    3:list<string> processes
}

service HeartbeatService{
    HeartbeatResponse heart(1:HeartbeatRequest request);
}