namespace java com.grandland.glits.ms.protocol
namespace py protocol.heartbeat

typedef i32 int
typedef i64 long

struct ProcessStatus{
    1:int id,
    2:string name,
    3:string status,
    4:int pid,
    5:double cpu_percent,
    6:double mem_percent,
    7:long run_time
}

struct Process{
    1:int id,
    2:string name,
    3:string program,
    4:bool running,
    5:bool auto_restart,
    6:string arguments
}

struct HeartbeatRequest{
    1:int version,
    2:string host_name,
    3:string ip_address,
    4:double cpu_usage,
    5:double mem_usage,
    6:list<ProcessStatus> processes_stats,
    7:map<string,long> mounted_avail_space
}

struct HeartbeatResponse{
    1:string host_name,
    2:int heartbeat_interval,
    3:int metric_interval,
    4:list<Process> processes,
    5:map<string,string> extra_configs
}

service HeartbeatService{
    HeartbeatResponse heartbeat(1:HeartbeatRequest request);
}