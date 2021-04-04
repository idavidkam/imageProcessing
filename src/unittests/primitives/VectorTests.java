/**
 * 
 */
package unittests.primitives;

import primitives.*;
import static org.junit.Assert.*;
import static primitives.Util.isZero;
import org.junit.Test;

/**
 * Unit tests for primitives.Vector class
 * 
 * @author David and Matan
 *
 */
public class VectorTests {

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============

		// Test that add is proper
		assertThrows("add() Vector + Vector does not work correctly", IllegalArgumentException.class,
				() -> v.add(new Vector(-1, -2, -3)));
	}

	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		Vector v = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============

		// Test that subtract is proper
		assertThrows("subtract() Vector - Vector does not work correctly", IllegalArgumentException.class,
				() -> v.subtract(new Vector(1, 2, 3)));
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	public void testScale() {
		Vector v = new Vector(1, 2, 3);

		// ============ Equivalence Partitions Tests ==============

		// Test that scale is proper
		assertEquals("scale() ", new Vector(2, 4, 6), v.scale(2));
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);

		// ============ Equivalence Partitions Tests ==============
		Vector v3 = new Vector(0, 3, -2);

		assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
		assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		Vector v1 = new Vector(1, 2, 3);
		Vector v2 = new Vector(-2, -4, -6);

		// ============ Equivalence Partitions Tests ==============
		Vector v3 = new Vector(0, 3, -2);
		Vector vr = v1.crossProduct(v3);

		// Test that length of cross-product is proper (orthogonal vectors taken for
		// simplicity)
		assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

		// Test cross-product result orthogonality to its operands
		assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
		assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

		// =============== Boundary Values Tests ==================
		// test zero vector from cross-product of co-lined vectors
		assertThrows("crossProduct() for parallel vectors does not throw an exception", IllegalArgumentException.class,
				() -> v1.crossProduct(v2));
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		// ============ Equivalence Partitions Tests ==============

		// Test that length squared is proper
		assertEquals("lengthSquared() wrong result length", new Vector(1, 2, 3).lengthSquared(), 14, 0.00001);

	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		// ============ Equivalence Partitions Tests ==============

		// Test that length is proper
		assertEquals("length() wrong result length", new Vector(0, 3, 4).length(), 5, 0.00001);
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		Vector v = new Vector(1, 2, 3);

		// ============ Equivalence Partitions Tests ==============
		Vector vCopy = new Vector(v.getHead());
		Vector vCopyNormalize = vCopy.normalize();

		// Test that normalize don't create a new vector
		assertTrue("normalize() function creates a new vector", vCopy == vCopyNormalize);

		// Test that normalize create a unit vector
		assertTrue("normalize() result is not a unit vector", isZero(vCopyNormalize.length() - 1));

	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		Vector v = new Vector(1, 2, 3);
		// ============ Equivalence Partitions Tests ==============

		Vector u = v.normalized();

		// Test that normalized create a new vector
		assertTrue("normalized() function does not create a new vector", u != v);

		// Test that normalized create a unit vector
		assertTrue("normalized() result is not a unit vector", isZero(u.length() - 1));
	}
}