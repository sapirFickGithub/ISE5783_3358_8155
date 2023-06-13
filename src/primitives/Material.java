package primitives;

public class Material {
    /**
     * The diffuse attenuation factor of the object material type
     */
    public Double3 _Kd = new Double3(0,0,0);

    /**
     * The specular attenuation factor of the object material type
     */
    public Double3 _Ks = new Double3(0,0,0);

    /**
     * The shininess factor of the object material type
     */
    public int _nShininess = 0;

    /**
     * Set the diffuse attenuation factor
     *
     * @param Kd Attenuation factor (double)
     * @return this (Material)
     */
    public Material setKd(double Kd) {
        _Kd = new Double3(Kd);
        return this;
    }
    /**
     * Set the diffuse attenuation factor
     *
     * @param Kd Attenuation factor (Double3)
     * @return this (Material)
     */
    public Material setKd(Double3 Kd) {
        _Kd = Kd;
        return this;
    }

    /**
     * Set the specular attenuation factor
     *
     * @param Ks Attenuation factor (Double3)
     * @return this (Material)
     */
    public Material setKs(double Ks) {
        _Ks = new Double3(Ks);
        return this;
    }
    /**
     * Set the specular attenuation factor
     *
     * @param Ks Attenuation factor (double)
     * @return this (Material)
     */
    public Material setKs(Double3 Ks) {
        _Ks = Ks;
        return this;
    }

    /**
     * Set the shininess factor of the material
     *
     * @param nShininess shininess factor of the material (int)
     * @return this (Material)
     */
    public Material setnShininess(int nShininess) {
        _nShininess = nShininess;
        return this;
    }
}
