package be.howest.junglewars.gameobjects.enemies;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {
    private String name;
    private Sprite sprite;

    private Player target;
    private Vector2 dPosition;

    private int damage;
    private int hitpoints;
    private int speed;

    private int scoreWhenKilled;
    private int experienceWhenKilled;

    private static int rarity;

    public Enemy(String name, int width, int height, int scoreWhenKilled, int experienceWhenKilled,
                 String imgUrl, int baseDamage, int baseSpeed, int baseHitpoints,
                 int rarity, int gameLevel, int gameDifficulty) {
        super(width, height);
        this.name = name;
        this.scoreWhenKilled = scoreWhenKilled;
        this.experienceWhenKilled = experienceWhenKilled;

        generateStats(gameLevel, gameDifficulty, baseDamage, baseHitpoints, baseSpeed);

        Texture texture = new Texture(imgUrl);
        sprite = new Sprite(texture, width, height);
        sprite.setOriginCenter();
    }

    private void generateStats(int level, int diff, int damage, int hitpoints, int speed) {
        // TODO: use algorithm
        this.damage = damage;
        this.hitpoints = hitpoints;
        this.speed = speed;
    }

    @Override
    public void update(float dt){
        float radians = MathUtils.atan2(dPosition.y - position.y, dPosition.x - position.x);
        dPosition.set(
                MathUtils.cos(radians) * speed,
                MathUtils.sin(radians) * speed
        );
        position.add(
                dPosition.x * dt,
                dPosition.y * dt
        );
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    @Override
    protected Vector2 generateSpawnPosition(float width, float height) {
        float x = MathUtils.random(0, Gdx.graphics.getWidth());
        float y = MathUtils.random(0, Gdx.graphics.getHeight());
        int side = MathUtils.random(0, 3);

        switch (side) {
            case 0:
                y = 0 - width;
                break;
            case 1:
                y = Gdx.graphics.getHeight();
                break;
            case 2:
                x = 0 - height;
                break;
            case 3:
                x = Gdx.graphics.getWidth();
                break;
        }

        return new Vector2(x, y);
    }

}
