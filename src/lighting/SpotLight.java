package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Util;
import primitives.Vector;

public class SpotLight extends PointLight{

    protected Vector _dir;

    public SpotLight(Color c, Point position,Vector direction) {
        super(c, position);
        _dir = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        double projection = _dir.dotProduct(getL(point));

        if (Util.isZero(projection)) {
            return Color.BLACK;
        }

        double factor = Math.max(0, projection);
        Color pointLightIntensity = super.getIntensity(point);


        return (pointLightIntensity.scale(factor));
    }
}
