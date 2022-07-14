package com.example.humansvsgoblinsjavafx;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.util.HashMap;

public class Human extends Entity {
    private final HashMap<String, Integer> stats = new HashMap<>(){{
        put("healthMax",100);
        put("health", get("healthMax"));
        put("strength", 15);
        put("agility",1);
    }};
    private String inventory = "";
    private int weaponDamage =0;
    PopupMessageBox popupMessageBox = new PopupMessageBox();

    Main.rand rand = new Main.rand();
    Human(){
        super(Color.BLUE);
        //System.out.println(new File("").getAbsolutePath() + "/src/main/java/com/example/humansvsgoblinsjavafx/mc.png");
//        File f = new File("program.txt");
//        Image img = new Image("file:/src/main/java/com/example/humansvsgoblinsjavafx/mc.png");
//        this.setFill(new ImagePattern(img));
        x = rand.ints(0, mapSize - 1);
        y = rand.ints(mapSize/2 + 1 , mapSize - 1);
        setTranslateX(x * tileSize);
        setTranslateY(y * tileSize);
    }
    public void checkPos(){
            if(!isEmpty(this)){
                Entity x = getEntity(this);
                if(x.getClass().getSimpleName().equals("Goblin") && !x.isDead())
                    fight((Goblin) x);
                if(x.getClass().getSimpleName().equals("Chest") && !x.isDead()){
                    this.getLoot(x);
                }
            }
            if(this.isDead()){

            }
    }
    public void fight(Goblin goblin){
        try {
            popupMessageBox.newWindow(450,500);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        popupMessageBox.addText("You enter combat with a goblin...");

        while(this.getHealth() > 0 && goblin.getHealth() > 0){
            /*
            humanHitChance: Player attack ratio (+5% per additional agility point)
            */
            double humanHitChance = (double)3 / (double)5 + ((this.getAgility() - 1) * .05), r = Math.random();
            if(r <= humanHitChance){
                goblin.damage(this.getStrength());
                goblin.damage(this.getWeaponDamage());
                popupMessageBox.addText("You attack goblin for " + (getStrength() + getWeaponDamage()) + " points."
                                + " \uD83D\uDC7A health: " + goblin.getHealth());
            }else{
                this.damage(goblin.getStrength());
                        popupMessageBox.addText("Goblin attacks you for " + goblin.getStrength() + " points."
                                + " \uD83E\uDDD1 health: " + this.getHealth());

            }
        }
        if(goblin.getHealth() <= 0){
            goblin.kill();
            if(Land.getGoblinCount() == 0){
                popupMessageBox.addText("\nYou won!");
            }
            this.getLoot(goblin);
            new Chest();
        }else{
            this.kill();
            popupMessageBox.addText("\nYou died.");
        }
    }
    public void getLoot(Entity x){

        if(x.getClass().getSimpleName().equals("Goblin")){
            final Object[][] loot = {
                    {"\uD83D\uDDE1 \"The Stake\"", 5},
                    {"\uD83D\uDDE1 \"Reaper of the Void\"", 15},
                    {"\uD83D\uDDE1 \"I am become Death\"", 45}
            };
            int lootDrop = 0;
            if((rand.ints(0, 1) == 1) && !inventory.equals(loot[2][0].toString())) {
                if(inventory.equals(loot[0][0].toString())){
                    lootDrop = 1;
                }else{
                    if(inventory.equals(loot[1][0].toString())){
                        lootDrop = 2;
                    }
                }
                popupMessageBox.addText("\nGoblin dropped: " + loot[lootDrop][0].toString());
                inventory = loot[lootDrop][0].toString();
                weaponDamage = (int)loot[lootDrop][1];
            }
        }
        if(x.getClass().getSimpleName().equals("Chest")){
            Object[][] powerUps = {
                    {"Health Regen", "health", getHealth() + (getHealthMax() / 2) <= getHealthMax() ? getHealthMax() / 2 : getHealthMax()-getHealth()},
                    {"Strength Boost", "strength", 5},
                    {"Agility Boost", "agility", 1},
                    {"Max-Health Boost", "healthMax", 10}
            };
            int r =  rand.ints(0,powerUps.length-1);

            try {
                popupMessageBox.newWindow(450,100);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            popupMessageBox.addText("You found a power up: " + powerUps[r][0] + " +" + powerUps[r][2]);
            stats.put((String)powerUps[r][1],stats.get(powerUps[r][1]) + (int)powerUps[r][2]);
            x.kill();
        }
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
    public String getStats(){
        return "HP: " + getHealth() +"/" + getHealthMax()
                + " | Strength: " + getStrength() + " | Agility: " + getAgility() + "\n"
                + "Inventory:" + getInventory();
    }
}
