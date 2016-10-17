/**
 * Calculates and returns the angle of the line defined by the coordinates
 */
public static double getAngle(double x1, double y1, double x2, double y2) {
	double res;
	double x = x2 - x1;
	double y = y2 - y1;
	res = Math.atan(y / x);
	if (x >= 0.0 && y >= 0.0) {
		res += 0.0;
	}
	else if (x < 0.0 && y >= 0.0) {
		res += Math.PI;
	}
	else if (x < 0.0 && y < 0.0) {
		res += Math.PI;
	}
	else if (x >= 0.0 && y < 0.0) {
		res += 2.0 * Math.PI;
	}
	return res;
}