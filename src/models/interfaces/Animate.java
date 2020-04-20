package models.interfaces;

import controllers.input.ICommand;

public interface Animate {
    void setAnimation(ICommand cmd);
    void playAnimation();
    void stopAnimation();
}