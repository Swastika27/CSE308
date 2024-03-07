public class ShakeBuilder {
    protected boolean lactoseFree;
    protected boolean candy;
    protected boolean cookie;
    public void setLactoseFree() {
        this.lactoseFree = true;
    }
    public void setCandy() {
        this.candy = true;
    }
    public void setCookies() {
        this.cookie = true;
    }

}
class ChocolateShakeBuilder extends ShakeBuilder {
    public ChocolateShake getShake() {
        return new ChocolateShake(lactoseFree, candy, cookie);
    }
}

class CoffeeShakeBuilder extends ShakeBuilder {
    public CoffeeShake getShake() {
        return new CoffeeShake(lactoseFree, candy, cookie);
    }
}

class StrawberryShakeBuilder extends ShakeBuilder {
    public StrawberryShake getShake() {
        return new StrawberryShake(lactoseFree, candy, cookie);
    }
}

class VanillaShakeBuilder extends ShakeBuilder {
       public VanillaShake getShake() {
        return new VanillaShake(lactoseFree, candy, cookie);
    }
}

class ZeroShakeBuilder extends ShakeBuilder {
       public ZeroShake getShake() {
        return new ZeroShake(lactoseFree, candy, cookie);
    }
}