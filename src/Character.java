public abstract class Character implements Fighter{
    private String name;
    private int hp;
    private int str;
    private int dext;
    private int xp;
    private int gold;
    private int lvl;

    public Character(String name, int hp, int str, int dext, int xp, int gold, int lvl) {
        this.name = name;
        this.hp = hp;
        this.str = str;
        this.dext = dext;
        this.xp = xp;
        this.gold = gold;
        this.lvl = lvl;
    }

    @Override
    public int attack() {
        if (dext * 3 > getRandomValue())
            return str;
        else return 0;
        //Critical attack logic
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getDext() {
        return dext;
    }

    public void setDext(int dext) {
        this.dext = dext;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {this.xp = xp; }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
    public int getRandomValue(){
        return (int) (Math.random() *100);
    }
    @Override
    public String toString(){
        return String.format("%s HP:%d", name, hp);
    }
}
