package be.howest.junglewars.gameobjects.helper;

import be.howest.junglewars.gameobjects.player.Player;
import be.howest.junglewars.gameobjects.GameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Helper extends GameObject {
    private Player owner;
    private String name;
    private HelperMovementType movementType;
    private HelperSpecialActionType specialActionType;

    private final int ANIMATION_WINGS_UP = 0;
    private final int ANIMATION_WINGS_DOWN = 1;

    public Helper(String name, float width, float height, HelperMovementType movementType,
                  HelperSpecialActionType specialActionType, Player owner, String textureUrl) {
        super(textureUrl);
        this.owner = owner;
        this.name = name;
        this.movementType = movementType;
        this.specialActionType = specialActionType;

//        texture = new Texture(Gdx.files.internal(textureUrl));
    }

    @Override
    protected TextureAtlas setAtlas() {
        return null;
    }

    @Override
    protected void update(float dt) {
        specialActionType.doSpecialAction(this);
        movementType.move(this);

    }

    @Override
    protected void draw(SpriteBatch batch) {
        activeSprite.setPosition(position.x, position.y);
    }

}