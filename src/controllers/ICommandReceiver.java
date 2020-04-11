package controllers;

public interface ICommandReceiver {
    void receiveCommand(ICommand cmd);
}