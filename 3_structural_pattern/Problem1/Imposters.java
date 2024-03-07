package Problem1;

public class Imposters extends Monsters{

    Imposters(Passengers crewmate) {
        super(crewmate);
    }

    @Override
    public void login() {
        super.login();
        System.out.println("We won't tell anyone; you are an imposter");
    }

    @Override
    public void logout() {
        super.logout();
        System.out.println("See you again Comrage Imposter");
    }

    @Override
    public void repair() {
        super.repair();
        System.out.println("Damaging the spaceship");
    }

    @Override
    public void work() {
        super.work();
        System.out.println("Trying to kill a crewmate.\nSuccessfully killed a crewmate");
    }
}
