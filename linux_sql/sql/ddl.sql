\c host_agent;

CREATE TABLE IF NOT EXISTS PUBLIC.host_info
(
    id               SERIAL PRIMARY KEY,
    hostname         VARCHAR UNIQUE     NOT NULL,
    cpu_number       INTEGER,
    cpu_architecture VARCHAR,
    cpu_model        VARCHAR,
    cpu_mhz          REAL,
    L2_cache         INTEGER,
    total_mem        INTEGER,
    timestamp      TIMESTAMP
);

CREATE TABLE IF NOT EXISTS PUBLIC.host_usage

    id SERIAL PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    host_id INTEGER NOT NULL,
    memory_free INTEGER,
    cpu_idle INTEGER,
    cpu_kernel INTEGER,
    disk_io INTEGER,
    disk_available INTEGER,

    CONSTRAINT foreign_key_host_id
        FOREIGN KEY(host_id)
            REFERENCES PUBLIC.host_info(id)
);