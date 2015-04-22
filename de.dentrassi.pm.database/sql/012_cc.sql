ALTER TABLE CHANNEL_CACHE ADD COLUMN CREATION_TS TIMESTAMP;

-- required for MySQL

UPDATE CHANNEL_CACHE SET CREATION_TS = CURRENT_TIMESTAMP;
ALTER TABLE CHANNEL_CACHE MODIFY COLUMN CREATION_TS TIMESTAMP NOT NULL;