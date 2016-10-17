/**
 * returns the intersection of both points [eg: (2,5) intersect (1,4) = (2,4)]
 * @param nanPriority if true then NaN has priority over other values, otherwise other values have priority
 */
public XValues intersect(XValues other, boolean nanPriority) {
	Double maxLeft = left;
	Double minRight = right;
	if (nanPriority) {
		if (other.left.equals(Double.NaN) || other.left > left) {
			maxLeft = other.left;
		}
		if (other.right.equals(Double.NaN) || other.right < right) {
			minRight = other.right;
		}
	}
	else {
		if (left.equals(Double.NaN) || other.left > left) {
			maxLeft = other.left;
		}
		if (right.equals(Double.NaN) || other.right < right) {
			minRight = other.right;
		}
	}
	return new XValues(maxLeft, minRight);
}