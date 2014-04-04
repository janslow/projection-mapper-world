package com.jayanslow.projection.world.models;

import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3f;

public class Rotation3f extends Tuple3f {
	private static final long	serialVersionUID	= 7372169161188811962L;

	public Rotation3f() {
		super();
	}

	public Rotation3f(float x, float y, float z) {
		super(x, y, z);
	}

	public Rotation3f(float[] t) {
		super(t);
	}

	public Rotation3f(Tuple3d t1) {
		super(t1);
	}

	public Rotation3f(Tuple3f t1) {
		super(t1);
	}

	public Matrix3f getRotationMatrix() {
		Matrix3f m = new Matrix3f(), m2 = new Matrix3f();

		// m = Rz(angleZ)
		m.rotZ(z);

		// m = Rz(angleZ) . Ry(angleX)
		m2.rotX(x);
		m.mul(m2);

		// m = Rz(angleZ) . Ry(angleX) . Rx(angleY)
		m2.rotY(y);
		m.mul(m2);

		return m;
	}

	public void inverseRotate(Vector3f v) {
		Matrix3f m = getRotationMatrix();
		m.invert();
		m.transform(v);
	}

	public void rotate(Vector3f v) {
		Matrix3f m = getRotationMatrix();
		m.transform(v);
	}
}
