package de.dentrassi.pm.storage.jpa;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2015-02-23T11:22:30.865+0100")
@StaticMetamodel(DeployGroupEntity.class)
public class DeployGroupEntity_ {
	public static volatile SingularAttribute<DeployGroupEntity, String> id;
	public static volatile SingularAttribute<DeployGroupEntity, String> name;
	public static volatile CollectionAttribute<DeployGroupEntity, DeployKeyEntity> keys;
}
