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
    public Double3 _Kt=Double3.ZERO;
    public Double3 _Kr=Double3.ZERO;

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
    public Material setShininess(int nShininess) {
        _nShininess = nShininess;
        return this;
    }

    public Material setKt(Double3 kt) {
        _Kt = kt;
        return this;
    }

    public Material setKr(Double3 kr) {
        _Kr = kr;
        return this;
    }

    public Material setKt(double kt) {
        _Kt = new Double3(kt);
        return this;
    }

    public Material setKr(double kr) {
        _Kr = new Double3(kr);
        return this;
    }
}
