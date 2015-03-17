CREATE TABLE EXT_CHAN_PROPS (
    CHANNEL_ID    VARCHAR(36) NOT NULL,
    NS            VARCHAR(255) NOT NULL,
    "KEY"         VARCHAR(255) NOT NULL,
    "VALUE"       TEXT,
    
    PRIMARY KEY (CHANNEL_ID, NS, "KEY" ),
    
    FOREIGN KEY (CHANNEL_ID) REFERENCES CHANNELS(ID) ON DELETE CASCADE
);

CREATE TABLE PROV_CHAN_PROPS (
    CHANNEL_ID    VARCHAR(36) NOT NULL,
    NS            VARCHAR(255) NOT NULL,
    "KEY"         VARCHAR(255) NOT NULL,
    "VALUE"       TEXT,
    
    PRIMARY KEY ( CHANNEL_ID, NS, "KEY" ),
    
    FOREIGN KEY ( CHANNEL_ID ) REFERENCES CHANNELS(ID) ON DELETE CASCADE
);