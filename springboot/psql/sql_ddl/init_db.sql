\c template1;
\set dbname `echo $POSTGRES_DB`
DROP DATABASE IF EXISTS :dbname;
CREATE DATABASE :dbname;
GRANT ALL PRIVILEGES ON DATABASE :dbname TO postgres;
\c :dbname;
