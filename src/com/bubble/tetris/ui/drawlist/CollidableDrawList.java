package com.bubble.tetris.ui.drawlist;

import java.util.ArrayList;
import java.util.List;
import com.bubble.tetris.models.interfaces.IGameObject;
import com.bubble.tetris.models.level.map.Map;

public class CollidableDrawList extends DrawList {

    private List<IGameObject> objects;

    public CollidableDrawList() {
        objects = new ArrayList<>();
    }

    public CollidableDrawList(List<IGameObject> list) {
        objects = list;
        add(list);
	}

    public boolean collides(Map map) {
        for (IGameObject go : objects) {
            if (go.collides(map)) return true;
        }
        return false;
    }

    public void addTo(Map map) {
        for (IGameObject go : objects) {
            go.addTo(map);
        }
    }
}