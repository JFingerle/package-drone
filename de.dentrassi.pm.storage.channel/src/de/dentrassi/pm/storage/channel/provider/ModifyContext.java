package de.dentrassi.pm.storage.channel.provider;

import java.io.InputStream;
import java.util.Map;

import de.dentrassi.pm.common.MetaKey;
import de.dentrassi.pm.storage.channel.ArtifactInformation;
import de.dentrassi.pm.storage.channel.ChannelDetails;

public interface ModifyContext extends AccessContext
{
    public void setDetails ( ChannelDetails details );

    public void applyMetaData ( Map<MetaKey, String> changes );

    public void lock ();

    public void unlock ();

    public ArtifactInformation createArtifact ( InputStream source, String name, Map<MetaKey, String> providedMetaData );

    public boolean deleteArtifact ( String id );
}
