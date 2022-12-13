import static java.lang.String.format;

public class Battle {

    public void fight(Character hero, Character monster, Realm.FightCallback fightCallback){
            Runnable runnable = () -> {
                int turn = 1;
                boolean isFightEnded = false;
                while (!isFightEnded) {
                    System.out.println("Move" + " " + turn + ".");
                    if (turn++ % 2 != 0){
                        isFightEnded = makeHit(monster,hero,fightCallback);
                    }else {
                        isFightEnded = makeHit(hero,monster,fightCallback);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
    }
    //hit
    private Boolean makeHit(Character defender, Character attacker, Realm.FightCallback fightCallback) {
        int hit = attacker.attack();
        int defenderHP = defender.getHp() - hit;
        int defenderXP = defender.getXp();
        if (hit != 0) {
            System.out.printf(format("%s Нанес удар в %d единиц!%n", attacker.getName(), hit));
            System.out.printf("У %s осталось %d единиц здоровья...%n", defender.getName(), defenderHP);
        } else {
            System.out.printf("%s промахнулся!%n", attacker.getName());
        }
        if (defenderHP <= 0 && defender instanceof Hero) {
            //Если здоровье меньше 0 и если защищающейся был героем, то игра заканчивается
            System.out.println("Извините, вы пали в бою...");
            //Вызываем коллбэк, что мы проиграли
            fightCallback.fightLost();
            return true;
        } else if(defenderHP <= 0) {
            //Если здоровья больше нет и защищающийся – это монстр, то мы забираем от монстра его опыт и золото
            System.out.printf("Враг повержен! Вы получаете %d опыт и %d золота%n", defender.getXp(), defender.getGold());
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            //lvlup
            if(defenderXP > 70){
                attacker.setLvl(defender.getLvl()+1);
                System.out.printf("Вы получили новый уроверень %n");
            }
            //вызываем коллбэк, что мы победили
            fightCallback.fightWin();
            return true;
        } else {
            //если защищающийся не повержен, то мы устанавливаем ему новый уровень здоровья
            defender.setHp(defenderHP);
            return false;
        }
    }
}
