package be.howest.junglewars.gameobjects.player;

import be.howest.junglewars.gameobjects.GameObject;
import be.howest.junglewars.gameobjects.helper.Helper;
import be.howest.junglewars.gameobjects.missile.Missile;
import be.howest.junglewars.gameobjects.power.Power;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Player extends GameObject {

    private boolean keyUpPressed;
    private boolean keyDownPressed;
    private boolean keyLeftPressed;
    private boolean keyRightPressed;

    private boolean topBorderTouch;
    private boolean bottomBorderTouch;
    private boolean leftBorderTouch;
    private boolean rightBorderTouch;

    private Helper helper;
    private ArrayList<Power> powers;
    private ArrayList<Missile> missiles;

    private String name;
    private int lives;
    private int score;
    private int level;
    private String textureUrl;

    private float shootTime;
    private float shootTimer;
    private boolean canShoot;

    private boolean isLookingLeft;

    private TextureRegion[] shootAnimationFrames;
    private Animation shootAnimation;
    private boolean doShootAnimation;
    private float shootAnimationTimer;

    public Player(String name, float width, float height, String textureUrl) {
        super(width, height, "harambe");
        this.name = name;
        this.textureUrl = textureUrl;

        shootAnimationFrames = new TextureRegion[]{
                atlas.findRegion("harambe-shoot")
        };
        shootAnimation = new Animation(.25f, shootAnimationFrames);
        shootAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        doShootAnimation = false;
        shootAnimationTimer = 0f;


    }

    private void handleInput() {

        System.out.println("handle input");

        keyUpPressed = Gdx.input.isKeyPressed(Input.Keys.UP);
        keyDownPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN);
        keyLeftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT);
        keyRightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT);

        topBorderTouch = position.y >= Gdx.graphics.getHeight() - bounds.getHeight();
        bottomBorderTouch = position.y <= 0;
        leftBorderTouch = position.x <= 0;
        rightBorderTouch = position.x >= Gdx.graphics.getWidth() - bounds.getWidth();

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && canShoot) {
            shoot(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        }

        float currentSpeed = speed;
        float sqrtSpeed = ((float) Math.sqrt((speed * speed) / 2));

        if ((keyUpPressed && (keyLeftPressed || keyRightPressed)) ||
                (keyDownPressed && (keyLeftPressed || keyRightPressed))) {
            currentSpeed = sqrtSpeed;
        }

        if (keyUpPressed) {
            if (leftBorderTouch || rightBorderTouch) currentSpeed = speed;
            position.y = topBorderTouch ? Gdx.graphics.getHeight() - bounds.getHeight() : position.y + currentSpeed;
        }
        if (keyDownPressed) {
            if (leftBorderTouch || rightBorderTouch) currentSpeed = speed;
            position.y = bottomBorderTouch ? 0 : position.y - currentSpeed;
        }
        if (keyLeftPressed) {
            isLookingLeft = true;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            position.x = rightBorderTouch ? 0 : position.x - currentSpeed;
        }
        if (keyRightPressed) {
            isLookingLeft = false;
            if (topBorderTouch || bottomBorderTouch) currentSpeed = speed;
            position.x = rightBorderTouch ? Gdx.graphics.getWidth() - bounds.getWidth() : position.x + currentSpeed;
        }
    }

    private void shoot(float x, float y) {
        doShootAnimation = true;

        canShoot = false;
        shootTimer = 0;
        x -= 16;
        y -= 14;

        float missileX = position.x;
        if (!isLookingLeft) missileX += bounds.getWidth() / 2 + 8;

        float missileY = position.y + bounds.getHeight() - 28;
        float radians = MathUtils.atan2(y - missileY, x - missileX);
        missiles.add(new Missile(this, missileX, missileY, radians, "banana.png", 10, 10, -10, 3));
    }

    @Override
    protected TextureAtlas setAtlas() {
        return new TextureAtlas("atlas/players.atlas");
    }

    @Override
    protected TextureRegion[] setAnimationFrames() {

        return new TextureRegion[0];
    }

    @Override
    protected Vector2 generateSpawnPosition() {
        return new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }

    @Override
    public void update(float dt) {
        handleInput();

        // TODO remove missiles
//        for (int i = 0; i < missiles.size(); i++) {
//            if (missiles.get(i).shouldRemove()) {
//                missiles.remove(i);
//                i--;
//            }
//        }
//        for (int i = 0; i < getHelper().getMissiles().size(); i++) {
//            if (getHelper().getMissiles().get(i).shouldRemove()) {
//                getHelper().getMissiles().remove(i);
//                i--;
//            }
//        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (doShootAnimation) {
            shootAnimationTimer = 0;
            doShootAnimation = false;
        }
        if (!shootAnimation.isAnimationFinished(shootAnimationTimer)) {

            activeSprite.setTexture(shootAnimation.getKeyFrame(shootAnimationTimer, false).getTexture());
        } else {
            activeSprite.setTexture(defaultTexture);
        }

        activeSprite = new Sprite(new Texture(Gdx.files.internal("images/players/harambe-normal.png")));

        activeSprite.flip(isLookingLeft, false);
        activeSprite.setPosition(position.x, position.y);
        activeSprite.draw(batch);


//        activeSprite.setPosition(position.x, position.y);
//        activeSprite.draw(batch);

        // TODO render missiles and helper?
        /*
        for (Missile missile : missiles) {
            missile.draw(batch);
        }
        helper.render(batch);
        for (HelperMissile missile : helper.getMissiles()) {
            missile.draw(batch);
        }
         */
    }
}
