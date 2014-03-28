package com.jayanslow.projection.scene.json;

public abstract class AbstractSerializer<T> implements Serializer<T> {

	private final SerializerFactory	factory;
	private final Class<T>			targetClass;

	public AbstractSerializer(final SerializerFactory factory, final Class<T> targetClass) {
		super();
		this.factory = factory;
		this.targetClass = targetClass;
	}

	protected SerializerFactory getFactory() {
		return factory;
	}

	@Override
	public Class<T> getTargetClass() {
		return targetClass;
	}
}
