import java.util.HashMap;
import java.util.Scanner;

public class Human extends Land{
    private int[] pos;
    private final HashMap<String, Integer> stats = new HashMap<>(){{
        put("healthMax",100);
        put("health", get("healthMax"));
        put("strength", 15);
        put("agility",1);
    }};
    private String inventory = "";
    private int weaponDamage =0;
    public void spawn(){
        pos = new int[]{rand((mapSize - 1) / 2 + 1, mapSize - 1), rand(0, mapSize - 1)};
        setPos(pos,'H');
    }
    public void move(char dir){
        int[] movePos = getPos().clone();
        switch(dir){
            case 'W': movePos[0] -= 1;
                break;
            case 'S': movePos[0] += 1;
                break;
            case 'A': movePos[1] -= 1;
                break;
            case 'D': movePos[1] += 1;
                break;
        }
        if(getPosChar(movePos) == '0'){
            message.add("Please stay inside the map.");
            playerTurn();
        }else{
            setEmptyPos(pos);
            pos = movePos.clone();
            switch(getPosChar(movePos)){
                case 'E':
                    setPos(pos,'H');
                    break;
                case 'C':
                    setPos(pos,'H');
                    getLoot('C');
                    break;
                case 'G':
                    setPos(pos,fight());
                    break;
            }
        }
    }
    public char fight(){
        System.out.println("You enter combat with a goblin...");
        pause(750);
        while(getHealth() > 0 && getGoblin(pos).getHealth() > 0){
            //humanHitChance: Player attack ratio (+5% per additional agility point)
            double humanHitChance = (double)3 / (double)5 + ((getAgility() - 1) * .05), r = Math.random();
            if(r <= humanHitChance){
                getGoblin(pos).damage(getStrength());
                getGoblin(pos).damage(getWeaponDamage());
                System.out.println("You attack goblin for " + (getStrength() + getWeaponDamage()) + " points."
                        + " \uD83D\uDC7A health: " + getGoblin(pos).getHealth());
            }else{
                damage(getGoblin(pos).getStrength());
                System.out.println("Goblin attacks you for " + getGoblin(pos).getStrength() + " points."
                        + " \uD83E\uDDD1 health: " + getHealth());
            }
            pause(750);
        }
        if(getGoblin(pos).getHealth() <= 0){
            deadGoblins.add(getGoblin(pos));
            goblinCount--;
            removeDeadGoblins();
            generateChest();
            getLoot('G');
            message.add("You won the fight.");
            return 'H';
        }else{
            message.add("You lost the fight.");
            return 'G';
        }
    }
    public char testFight() {
        while (getHealth() > 0 && getGoblin(pos).getHealth() > 0) {
            //humanHitChance: Player attack ratio (+5% per additional agility point)
            double humanHitChance = (double) 3 / (double) 5 + ((getAgility() - 1) * .05), r = Math.random();
            if (r <= humanHitChance) {
                getGoblin(pos).damage(getStrength());
                getGoblin(pos).damage(getWeaponDamage());
            } else {
                damage(getGoblin(pos).getStrength());
            }
        }
        if (getGoblin(pos).getHealth() <= 0) {
            deadGoblins.add(getGoblin(pos));
            goblinCount--;
            removeDeadGoblins();
            getLoot('G');
            return 'H';
        } else {
            return 'G';
        }
    }
    public void getLoot(char c){
        if(c == 'G'){
             final Object[][] loot = {
                    {"\uD83D\uDDE1️\"The Stake\"", 5},
                    {"\uD83D\uDDE1️\"Reaper of the Void\"", 15},
                    {"\uD83D\uDDE1️\"I am become Death\"", 45}
            };
            int lootDrop = 0;
            if((rand(0, 1) == 1) && !inventory.equals(loot[2][0].toString())) {
                if(inventory.equals(loot[0][0].toString())){
                    lootDrop = 1;
                }else{
                    if(inventory.equals(loot[1][0].toString())){
                        lootDrop = 2;
                    }
                }
                message.add("Goblin dropped some loot: " + loot[lootDrop][0].toString());
                inventory = loot[lootDrop][0].toString();
                weaponDamage = (int)loot[lootDrop][1];
            }
        }
        if(c == 'C'){
            Object[][] powerUps = {
                    {"Health Regen", "health", getHealth() + (getHealthMax() / 2) <= getHealthMax() ? getHealthMax() / 2 : getHealthMax()-getHealth()},
                    {"Strength Boost", "strength", 5},
                    {"Agility Boost", "agility", 1},
                    {"Max-Health Boost", "healthMax", 10}
            };
            int rand =  rand(0,powerUps.length-1);
            message.add("You found a power up: " + powerUps[rand][0] + " +" + powerUps[rand][2]);
            stats.put((String)powerUps[rand][1],stats.get(powerUps[rand][1]) + (int)powerUps[rand][2]);
        }
    }
    public int[] getPos(){
        return pos;
    }
    public int getHealth(){
        return stats.get("health");
    }
    public int getHealthMax(){
        return stats.get("healthMax");
    }
    public int getStrength(){
        return stats.get("strength");
    }
    public int getAgility(){return stats.get("agility");}
    public void damage(int damageCounter){
        stats.put("health",Math.max(stats.get("health") - damageCounter, 0));
    }
    public void regen(int healthPoints){
        if(getHealth() + healthPoints <= getHealthMax())
            stats.put("health",stats.get("health") + healthPoints);
        else
            stats.put("health",stats.get("healthMax"));
    }
    public String getInventory(){
        if(!inventory.equals("")){
            return inventory + " +" + weaponDamage + " damage";
        }
        return inventory;
    }
    public int getWeaponDamage(){
        return weaponDamage;
    }
    public void showStats(){
        System.out.println("HP: " + getHealth() +"/" + getHealthMax()
                + " | Strength: " + getStrength() + " | Agility: " + getAgility());
        System.out.print("Inventory:" + getInventory());
        System.out.println();
    }
    public char tryMove() {
        System.out.print("(WASD) Movement: ");
        Scanner input = new Scanner(System.in);
        String check;
        boolean valid;
        do {
            valid = true;
            check = input.nextLine();
            if(check.length() != 1 || !check.matches("[WASDwasd]+")){
                drawLand();
                System.out.print("Please enter a valid direction.\n(W, A, S, D): ");
                valid = false;
            }
        } while (!valid);
        return Character.toUpperCase(check.charAt(0));
    }
    public String toString(){
        return "Health: " + getHealth() + " Strength: " + getStrength() + " Pos: " + this.pos[0] + "," + this.pos[1];
    }
}
