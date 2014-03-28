package test.projection.world.json;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AxisAngle4fSerializerTest.class, CuboidScreenSerializerTest.class, FlatScreenSerializerTest.class,
		MapSerializerFactoryTest.class, ProjectorSerializerTest.class, RealObjectSerializerTest.class,
		ScreenSerializerTest.class, UniverseSerializerTest.class, Vector3fSerializerTest.class })
public class AllWorldJsonSerializerTests {

}
