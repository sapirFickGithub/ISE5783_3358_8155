package primitives;

public class Material {
    /**
     * The diffuse attenuation factor of the object material type.
     * Determines how much light is diffusely reflected by the material.
     */
    public Double3 _Kd = new Double3(0, 0, 0);

    /**
     * The specular attenuation factor of the object material type.
     * Determines how much light is specularly reflected by the material.
     */
    public Double3 _Ks = new Double3(0, 0, 0);

    /**
     * The shininess factor of the object material type.
     * Determines the shininess/glossiness of the material's specular reflection.
     */
    public int _nShininess = 0;

    /**
     * The transmission attenuation factor of the object material type.
     * Determines how much light is transmitted through the material.
     */
    public Double3 _Kt = Double3.ZERO;

    /**
     * The reflection attenuation factor of the object material type.
     * Determines how much light is reflected by the material.
     */
    public Double3 _Kr = Double3.ZERO;

    /**
     * Sets the diffuse attenuation factor of the material.
     *
     * @param Kd Attenuation factor (double)
     * @return This Material object with the updated diffuse attenuation factor (Material)
     */
    public Material setKd(double Kd) {
        _Kd = new Double3(Kd);
        return this;
    }

    /**
     * Sets the diffuse attenuation factor of the material.
     *
     * @param Kd Attenuation factor (Double3)
     * @return This Material object with the updated diffuse attenuation factor (Material)
     */
    public Material setKd(Double3 Kd) {
        _Kd = Kd;
        return this;
    }

    /**
     * Sets the specular attenuation factor of the material.
     *
     * @param Ks Attenuation factor (Double3)
     * @return This Material object with the updated specular attenuation factor (Material)
     */
    public Material setKs(double Ks) {
        _Ks = new Double3(Ks);
        return this;
    }

    /**
     * Sets the specular attenuation factor of the material.
     *
     * @param Ks Attenuation factor (double)
     * @return This Material object with the updated specular attenuation factor (Material)
     */
    public Material setKs(Double3 Ks) {
        _Ks = Ks;
        return this;
    }

    /**
     * Sets the shininess factor of the material.
     *
     * @param nShininess Shininess factor of the material (int)
     * @return This Material object with the updated shininess factor (Material)
     */
    public Material setShininess(int nShininess) {
        _nShininess = nShininess;
        return this;
    }

    /**
     * Sets the transmission attenuation factor of the material.
     *
     * @param kt Attenuation factor (Double3)
     * @return This Material object with the updated transmission attenuation factor (Material)
     */
    public Material setKt(Double3 kt) {
        _Kt = kt;
        return this;
    }

    /**
     * Sets the reflection attenuation factor of the material.
     *
     * @param kr Attenuation factor (Double3)
     * @return This Material object with the updated reflection attenuation factor (Material)
     */
    public Material setKr(Double3 kr) {
        _Kr = kr;
        return this;
    }

    /**
     * Sets the transmission attenuation factor of the material.
     *
     * @param kt Attenuation factor (double)
     * @return This Material object with the updated transmission attenuation factor (Material)
     */
    public Material setKt(double kt) {
        _Kt = new Double3(kt);
        return this;
    }

    /**
     * Sets the reflection attenuation factor of the material.
     *
     * @param kr Attenuation factor (double)
     * @return This Material object with the updated reflection attenuation factor (Material)
     */
    public Material setKr(double kr) {
        _Kr = new Double3(kr);
        return this;
    }
}
