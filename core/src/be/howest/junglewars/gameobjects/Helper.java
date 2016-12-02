package be.howest.junglewars.gameobjects;

import be.howest.junglewars.screens.GameScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

// TODO: should be upgradable
public class Helper extends GameObject {
    private Player owner;
    private String name;

    private float shootTime;
    private float shootTimer;

    public Helper(GameScreen game, float width, float height, String name, Player owner, String defaultSpriteUrl) {
        super(game, defaultSpriteUrl, width, height, owner.body.x, owner.body.y);

        this.owner = owner;
        this.name = name;
    }

    @Override
    protected TextureAtlas initAtlas() {
        return new TextureAtlas("atlas/helpers.atlas");
    }

    private Enemy chooseTarget() {
        return getNearest(game.getEnemies());
    }

    private Vector2 leftTopOfOwnerPosition() {
        return new Vector2(owner.body.x - 1.5f * body.width, owner.body.y + 1.5f * body.height);
    }

    @Override
    public void update(float dt) {
        body.x = owner.body.x - 1.5f * body.width;
        body.y = owner.body.y + 1.5f * body.height;
    }

    @Override
    public void draw(SpriteBatch batch) {
        activeSprite.setPosition(body.x, body.y);
        activeSprite.draw(batch);
    }

    public Player getOwner() {
        return owner;
    }

}