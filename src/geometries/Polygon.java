package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents a two-dimensional polygon in 3D Cartesian coordinate system.
 * A polygon is defined by a list of ordered vertices and lies on an associated plane.
 * The polygon must be convex.
 *
 * @author Dan
 */
public class Polygon extends Geometry {
	/**
	 * List of polygon's vertices
	 */
	protected List<Point> vertices;
	/**
	 * Associated plane in which the polygon lies
	 */
	protected Plane plane;
	private int size;

	/**
	 * Constructs a polygon based on a list of vertices.
	 * The list must be ordered by the edge path of the polygon, and the polygon must be convex.
	 *
	 * @param vertices A list of vertices in the order of the edge path.
	 * @throws IllegalArgumentException If the combination of vertices is illegal:
	 *                                  <ul>
	 *                                  <li>Less than 3 vertices</li>
	 *                                  <li>Consecutive vertices are in the same point</li>
	 *                                  <li>The vertices are not in the same plane</li>
	 *                                  <li>The order of vertices is not according to the edge path</li>
	 *                                  <li>Three consecutive vertices lie on the same line (180&#176; angle between two consecutive edges)</li>
	 *                                  <li>The polygon is concave (not convex)</li>
	 *                                  </ul>
	 */
	public Polygon(Point... vertices) {
		if (vertices.length < 3)
			throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
		this.vertices = List.of(vertices);
		// Generate the plane according to the first three vertices and associate the
		// polygon with this plane.
		// The plane holds the invariant normal (orthogonal unit) vector to the polygon
		plane = new Plane(vertices[0], vertices[1], vertices[2]);
		if (vertices.length == 3)
			return; // no need for more tests for a Triangle

		Vector n = plane.getNormal();

		// Subtracting any subsequent points will throw an IllegalArgumentException
		// because of Zero Vector if they are in the same point
		Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
		Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

		// Cross Product of any subsequent edges will throw an IllegalArgumentException
		// because of Zero Vector if they connect three vertices that lie in the same line.
		// Generate the direction of the polygon according to the angle between the last and
		// first edge being less than 180 deg. It is held by the sign of its dot product with
		// the normal. If all the rest of the consecutive edges generate the same sign, the
		// polygon is convex.
		boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
		for (var i = 1; i < vertices.length; ++i) {
			// Test that the point is in the same plane as calculated originally
			if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
				throw new IllegalArgumentException("All vertices of a polygon must lie in the same plane");
			// Test the consecutive edges
			edge1 = edge2;
			edge2 = vertices[i].subtract(vertices[i - 1]);
			if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
				throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
		}
		size = vertices.length;
	}

	@Override
	public Vector getNormal(Point point) {
		return plane.getNormal();
	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> intersections = plane.findGeoIntersections(ray);

		// Check if the plane of the polygon intersects with the ray. If there's no intersection with the
		// plane, then there's no intersection with the polygon.
		if (intersections == null)
			return null;

		Point p0 = ray.getP0();
		Vector v = ray.getDir();

		Vector v1 = vertices.get(1).subtract(p0);
		Vector v2 = vertices.get(0).subtract(p0);

		double sign = v.dotProduct(v1.crossProduct(v2));

		if (isZero(sign))
			return null;

		boolean positive = sign > 0;

		for (int i = vertices.size() - 1; i > 0; --i) {
			v1 = v2;
			v2 = vertices.get(i).subtract(p0);
			sign = alignZero(v.dotProduct(v1.crossProduct(v2)));

			if (isZero(sign))
				return null;

			if (positive != (sign > 0))
				return null;
		}

		return List.of(new GeoPoint(this, intersections.get(0).point));
	}

	/**
	 * Finds the geometric intersection points between the polygon and a given ray.
	 *
	 * @param ray The ray to intersect with the polygon.
	 * @param maxDistance The maximum distance for intersection points.
	 * @return A list of geometric intersection points represented as {@link GeoPoint}.
	 */
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
		List<GeoPoint> intersections = plane.findGeoIntersections(ray, maxDistance);

		// Check if the plane of the polygon intersects with the ray. If there's no intersection with the
		// plane, then there's no intersection with the polygon.
		if (intersections == null)
			return null;

		Point p0 = ray.getP0();
		Vector v = ray.getDir();

		Vector v1 = vertices.get(1).subtract(p0);
		Vector v2 = vertices.get(0).subtract(p0);

		double sign = v.dotProduct(v1.crossProduct(v2));

		if (isZero(sign))
			return null;

		boolean positive = sign > 0;

		for (int i = vertices.size() - 1; i > 0; --i) {
			v1 = v2;
			v2 = vertices.get(i).subtract(p0);
			sign = alignZero(v.dotProduct(v1.crossProduct(v2)));

			if (isZero(sign))
				return null;

			if (positive != (sign > 0))
				return null;
		}

		return List.of(new GeoPoint(this, intersections.get(0).point));
	}
}
