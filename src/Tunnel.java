import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore semaphore;
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        semaphore = new Semaphore(MeinClass.CARS_COUNT / 2);
    }
    @Override
    public void go(Car c) {
        try {
            semaphore.acquire();
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}