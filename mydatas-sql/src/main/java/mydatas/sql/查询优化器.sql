set optimizer_trace = "enabled=on";
set optimizer_trace = "enabled=off";
select *
from information_schema.OPTIMIZER_TRACE;

-- system > const > eq_ref > ref > range > index > ALL
-- system和const想想就行了
-- eq_ref: 单值查询且索引

-- normal: range, ref: better