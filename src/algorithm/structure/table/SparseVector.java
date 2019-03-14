package algorithm.structure.table;

import java.util.Map;
import java.util.TreeMap;

/**
 * The {@code SparseVector} class represents a <em>d</em>-dimensional
 * mathematical vector.
 * <p>
 * nonzero coordinates are stored in a symbol table, with key be indices and
 * value be values. This makes it efficient when most of the vector coordindates
 * are zero.
 * <p>
 * 
 * @author Alex
 *
 */
public class SparseVector {
	private Map<Integer, Double> vector;
	private int dimension;

	public SparseVector(int dimension) {
		this.dimension = dimension;
		this.vector = new TreeMap<>();
	}

	private void checkBound(int index) {
		if (index < 0 || index >= dimension) {
			throw new IndexOutOfBoundsException();
		}
	}

	private void checkDimension(SparseVector that) {
		if (this.dimension != that.dimension) {
			throw new IllegalArgumentException();
		}
	}

	public void put(int index, double value) {
		checkBound(index);
		if (value == 0.0) {
			vector.remove(index);
		} else {
			vector.put(index, value);
		}
	}

	public double get(int index) {
		checkBound(index);
		return vector.getOrDefault(index, 0.0);
	}

	public int nonzeroSize() {
		return vector.size();
	}

	public int dimension() {
		return dimension;
	}

	public Iterable<Integer> indices() {
		return vector.keySet();
	}

	public double dot(SparseVector that) {
		checkDimension(that);
		double sum = 0.0;
		for (Integer index : this.indices()) {
			if (that.vector.containsKey(index)) {
				sum += this.get(index) * that.get(index);
			}
		}
		return sum;
	}

	public double dot(double[] that) {
		double sum = 0.0;
		for (Integer index : this.indices()) {
			sum += vector.get(index) * that[index];
		}
		return sum;
	}

	/**
	 * Returns the length of this vector. This is also known as the L2 norm or
	 * the Euclidean norm.
	 * 
	 * @return
	 */
	public double magnitude() {
		return Math.sqrt(this.dot(this));
	}

	public SparseVector scale(double alpha) {
		SparseVector c = new SparseVector(dimension);
		for (Integer index : this.indices()) {
			c.put(index, this.get(index) * alpha);
		}
		return c;
	}

	public SparseVector plus(SparseVector that) {
		checkDimension(that);
		SparseVector c = new SparseVector(dimension);
		// c = this
		for (Integer index : this.indices()) {
			c.put(index, this.get(index));
		}
		// c = c + that
		for (Integer index : that.indices()) {
			c.put(index, this.get(index) + that.get(index));
		}
		return c;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i : this.indices()) {
			stringBuilder.append("(").append(i).append(", ").append(this.get(i)).append(") ");
		}
		return stringBuilder.toString();
	}

	public static void main(String[] args) {
		SparseVector a = new SparseVector(10);
		SparseVector b = new SparseVector(10);
		a.put(3, 0.50);
		a.put(9, 0.75);
		a.put(6, 0.11);
		a.put(6, 0.00);
		b.put(3, 0.60);
		b.put(4, 0.90);
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("a dot b = " + a.dot(b));
		System.out.println("a + b = " + a.plus(b));
	}
}
