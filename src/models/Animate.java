package models;

import controllers.ICommand;

public interface Animate {
    void setAnimation(ICommand cmd);
    void playAnimation();
    void stopAnimation();
}