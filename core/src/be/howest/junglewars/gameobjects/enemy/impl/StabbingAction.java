package be.howest.junglewars.gameobjects.enemy.impl;

import be.howest.junglewars.gameobjects.Player;
import be.howest.junglewars.gameobjects.enemy.Enemy;
import be.howest.junglewars.gameobjects.enemy.IEnemyActionType;
import com.badlogic.gdx.math.Vector2;


public class StabbingAction implements IEnemyActionType {
    @Override
    public void attack(Enemy enemy, Vector2 v, float spawnX, float spawnY) {

        if(enemy.checkCollision(enemy.game.getData().getPlayers()).size()!=0){
            for(Player p: enemy.game.getData().getPlayers()){
                p.catchDamage(20);
            }
        }

    }
}
