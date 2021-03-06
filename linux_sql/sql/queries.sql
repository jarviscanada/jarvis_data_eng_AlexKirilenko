-- Test data for host_info
INSERT INTO host_info(hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, l2_cache, total_mem, timestamp)
VALUES
('host1',1,'x86_64',1, 2000, 256, 4000000,now()),
('host2',1,'x86_64',1, 2000, 256, 4000000,now()),
('host3',2,'x86_64',11, 1700, 512, 8000000,now()),
('host4',2,'x86_64',11, 1900, 256, 8000000,now()),
('host5',2,'x86_64',11, 2000, 256, 6000000,now()),
('host6',2,'x86_64',11, 2100, 256, 4000000,now()),
('host7',4,'x86_64',15, 2200, 256, 8000000,now()),
('host8',4,'x86_64',15, 2000, 256, 12000000,now()),
('host9',16,'x86_64',10, 2300, 1024, 64000000,now()),
('host10',16,'x86_64',10, 2200, 1024, 64000000,now()),
('host11',8,'x86_64',15, 2050, 256, 4000000,now()),
('host12',8,'x86_64',15, 2100, 256, 8000000,now()),
('host13',8,'x86_64',16, 2300, 512, 12000000,now()),
('host14',8,'x86_64',17, 2100, 512, 6000000,now()),
('host15',8,'x86_64',13, 2300, 256, 4000000,now()),
('host16',12,'x86_64',10, 2200, 1024, 16000000,now()),
('host17',12,'x86_64',10, 2200, 1024, 12000000,now()),
('host18',12,'x86_64',10, 2200, 1024, 16000000,now());

-- Group hosts by CPU number and sort by their memory size
-- in descending order(within each cpu_number group):
SELECT cpu_number, id AS host_id, total_mem
FROM host_info
ORDER BY cpu_number, total_mem DESC;

-- Test data for host_usage (for hosts 1 and 3)
INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES
('2021-05-12 15:00:01', 1, 1500000, 89, 3, 5000, 34132),
('2021-05-12 15:01:10', 1, 1600000, 85, 7, 6000, 34008),
('2021-05-12 15:02:20', 1, 1200000, 75, 8, 3500, 33131),
('2021-05-12 15:03:10', 1, 1100000, 92, 2, 8200, 33152),
('2021-05-12 15:04:04', 1, 1800000, 68, 12, 9900, 33532),
('2021-05-12 15:05:06', 1, 2000000, 89, 4, 5600, 34238),
('2021-05-12 15:06:30', 1, 1600000, 90, 3, 3800, 34639),
('2021-05-12 15:07:07', 1, 1700000, 92, 3, 3900, 33835),
('2021-05-12 15:08:03', 1, 1200000, 88, 3, 5700, 35131),
('2021-05-12 15:09:20', 1, 1600000, 87, 10, 4700, 32581),
('2021-05-12 15:10:04', 1, 1600000, 85, 3, 4800, 32221),
('2021-05-12 15:11:12', 1, 1180000, 89, 3, 4900, 32653),
('2021-05-12 15:12:10', 1, 1200000, 87, 3, 6500, 32842),
('2021-05-12 15:13:00', 1, 1500000, 89, 3, 6700, 33455),
('2021-05-12 15:14:50', 1, 1600000, 89, 3, 5300, 33650),
('2021-05-12 15:00:01', 3, 6500000, 89, 4, 5000, 84132),
('2021-05-12 15:01:10', 3, 6100000, 88, 7, 8000, 86008),
('2021-05-12 15:02:20', 3, 6200000, 79, 11, 6500,83131),
('2021-05-12 15:03:10', 3, 6300000, 91, 2, 8200, 83152),
('2021-05-12 15:04:04', 3, 6800000, 88, 12, 9900,83332),
('2021-05-12 15:05:06', 3, 6000000, 87, 4, 4600, 85238),
('2021-05-12 15:06:30', 3, 6600000, 91, 3, 7800, 85639),
('2021-05-12 15:07:07', 3, 6700000, 90, 5, 3900, 83835),
('2021-05-12 15:08:03', 3, 6200000, 89, 3, 5700, 85131),
('2021-05-12 15:09:20', 3, 6600000, 87, 10, 4700,82581),
('2021-05-12 15:10:04', 3, 6600000, 88, 6, 5800, 82221),
('2021-05-12 15:11:12', 3, 6180000, 91, 5, 8900, 82653),
('2021-05-12 15:12:10', 3, 6200000, 85, 4, 6500, 82842),
('2021-05-12 15:13:00', 3, 6500000, 88, 2, 6700, 85455),
('2021-05-12 15:14:50', 3, 6600000, 89, 3, 7300, 83650);

--Average used memory in percentage over 5 mins interval for each host:
SELECT host_usage.host_id,
       host_info.hostname,
       date_trunc('hour', host_usage.timestamp) + date_part('minute', host_usage.timestamp):: int / 5 * interval '5 min' AS ts,
       avg(1.0*(host_info.total_mem - host_usage.memory_free)/host_info.total_mem) AS avg_used_mem_percentage
FROM host_usage
         JOIN host_info ON host_usage.host_id = host_info.id
GROUP BY host_usage.host_id,host_info.hostname, ts
ORDER BY host_usage.host_id;


-- Test data for host_usage
--  host 2 has failures
INSERT INTO host_usage(timestamp, host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES
('2021-05-12 15:00:01', 2, 1500000, 89, 3, 5000, 34132),
('2021-05-12 15:01:10', 2, 1600000, 85, 7, 6000, 34008),
('2021-05-12 15:02:20', 2, 1200000, 75, 8, 3500, 33131),
('2021-05-12 15:03:10', 2, 1100000, 92, 2, 8200, 33152),
('2021-05-12 15:04:04', 2, 1800000, 68, 12, 9900, 33532),
('2021-05-12 15:05:06', 2, 2000000, 89, 4, 5600, 34238),
('2021-05-12 15:06:30', 2, 1600000, 90, 3, 3800, 34639),
('2021-05-12 15:07:07', 2, 1700000, 92, 3, 3900, 33835),
('2021-05-12 15:08:03', 2, 1200000, 88, 3, 5700, 35131),
('2021-05-12 15:09:22', 2, 1200000, 88, 3, 5700, 35131),
('2021-05-12 15:10:04', 2, 1600000, 85, 3, 4800, 32221),
('2021-05-12 15:11:12', 2, 1180000, 89, 3, 4900, 32653),
('2021-05-12 15:14:10', 2, 1200000, 87, 3, 6500, 32842),
('2021-05-12 15:15:00', 2, 1500000, 89, 3, 6700, 33455),
('2021-05-12 15:17:50', 2, 1600000, 89, 3, 5300, 33650);

-- Less than three data points within 5-min interval
SELECT host_id,
       count(*) AS n_points,
       date_trunc('hour', host_usage.timestamp) + date_part('minute', host_usage.timestamp):: int / 5 * interval '5 min' AS interval_start
FROM host_usage
GROUP BY interval_start, host_id
HAVING count(*) < 5
ORDER BY host_id,interval_start;