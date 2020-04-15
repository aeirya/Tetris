package models;

import controllers.ICommand;

public interface Animate {
    void playAnimation();
    void setAnimation(ICommand cmd);
}