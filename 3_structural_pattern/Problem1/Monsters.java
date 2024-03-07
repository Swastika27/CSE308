package Problem1;

public class Monsters implements Passengers {
    private Passengers crew;

    Monsters(Passengers p) {
        crew = p;
    }

    @Override
    public void login() {
        crew.login();
    }

    @Override
    public void logout() {
        crew.logout();
    }

    @Override
    public void repair() {
        crew.repair();
    }

    @Override
    public void work() {
        crew.work();
    }
}
